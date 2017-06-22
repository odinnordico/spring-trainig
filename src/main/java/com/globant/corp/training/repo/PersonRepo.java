package com.globant.corp.training.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.LongStream;

import org.springframework.stereotype.Component;

import com.globant.corp.training.model.Person;

@Component
public class PersonRepo implements Repo<Person> {

   private static final Map<Long, Person> PERSONS = new HashMap<>();
   static {
      LongStream.range(0L, 10L).forEach(counter -> {
         PERSONS.putIfAbsent(counter, createPerson(counter));
      });
   }

   private static Person createPerson(final long id) {
      return Person.builder().id(id).firstName(String.format("First Name %s", id)).lastName(String.format("Last Name %s", id))
            .age(new Random().nextInt(80)).build();
   }

   @Override
   public List<Person> getAll() {
      return new ArrayList<>(PERSONS.values());
   }

   @Override
   public Person getById(final long id) {
      return PERSONS.get(id);
   }

   @Override
   public void add(final Person value) {
      PERSONS.putIfAbsent(value.getId(), value);
   }

   @Override
   public void update(final Person value) {
      PERSONS.remove(value.getId());
      PERSONS.put(value.getId(), value);
   }

   @Override
   public void removeById(final long id) {
      PERSONS.remove(id);
   }

}
