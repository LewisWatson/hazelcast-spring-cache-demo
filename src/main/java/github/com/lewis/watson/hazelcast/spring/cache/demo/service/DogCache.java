package github.com.lewis.watson.hazelcast.spring.cache.demo.service;

import github.com.lewis.watson.hazelcast.spring.cache.demo.model.Dog;

public interface DogCache {

  Dog put(String key, Dog dog);
 
}
