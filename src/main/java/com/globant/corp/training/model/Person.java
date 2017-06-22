package com.globant.corp.training.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class Person {

   private final long id;
   private final String firstName;
   private final String lastName;
   private final int age;
}
