package com.globant.corp.training.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.corp.training.model.Person;
import com.globant.corp.training.model.PersonHit;
import com.globant.corp.training.repo.Repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class PersonHandler {

   @Autowired
   private Repo<Person> personRepo;

   public Flux<PersonHit> getPersonHits(final Person person) {
      final Flux<Long> interval = Flux.interval(Duration.ofSeconds(2L));
      final Flux<PersonHit> hits = Flux.fromStream(Stream.generate(() -> PersonHit.builder().person(person).dateTime(LocalDateTime.now()).build()));
      return Flux.zip(interval, hits).map(Tuple2::getT2);
   }

   public Flux<Person> getAll() {
      return Flux.fromStream(personRepo.getAll().stream());
   }

   public Mono<Person> getById(final long id) {
      return Mono.justOrEmpty(Optional.ofNullable(personRepo.getById(id))) ;
   }

   public void addPerson(final Person person) {
      personRepo.add(person);
   }

   public void removePerson(final long id) {
      personRepo.removeById(id);
   }

   public void updatePerson(final Person person) {
      personRepo.update(person);
   }
}
