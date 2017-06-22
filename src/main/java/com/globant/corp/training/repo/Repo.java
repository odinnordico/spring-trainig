package com.globant.corp.training.repo;

import java.util.List;

public interface Repo<T> {
   List<T> getAll();

   T getById(long id);

   void add(T value);

   void update(T value);

   void removeById(long id);
}
