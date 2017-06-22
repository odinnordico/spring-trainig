package com.globant.corp.training.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
public class PersonHit {
   private final Person person;
   private final LocalDateTime dateTime;

}
