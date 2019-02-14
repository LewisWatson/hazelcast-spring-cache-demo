package github.com.lewis.watson.couchbase.spring.cache.demo.service;

import github.com.lewis.watson.couchbase.spring.cache.demo.model.Dog;

public interface DogService {

  Dog putDog(Dog dog);
  
  Dog getDog(String key);
}
