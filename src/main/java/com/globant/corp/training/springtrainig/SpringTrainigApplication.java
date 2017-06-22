package com.globant.corp.training.springtrainig;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.globant.corp.training.handler.PersonHandler;
import com.globant.corp.training.model.Person;
import com.globant.corp.training.model.PersonHit;

@SpringBootApplication(scanBasePackages = "com.globant.corp.training")
public class SpringTrainigApplication {

   public static void main(String[] args) {
      SpringApplication.run(SpringTrainigApplication.class, args);
   }

   @Bean
   RouterFunction<ServerResponse> routes(final PersonHandler personHandler) {
      return route(GET("/person"), request -> ok().body(personHandler.getAll(), Person.class))
            .andRoute(GET("/person/{id}"), request -> ok().body(personHandler.getById(Long.parseLong(request.pathVariable("id"))), Person.class))
            .andRoute(GET("/person/{id}/hits"), request -> {
               final long id = Long.parseLong(request.pathVariable("id"));
               return ok().contentType(MediaType.TEXT_EVENT_STREAM)
                     .body(personHandler.getById(id).flatMap(p -> personHandler.getPersonHits(p).next()), PersonHit.class);
            });
   }

}
