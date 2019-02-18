package com.github.lewiswatson.hazelcast.spring.cache.demo.service;

import com.github.lewiswatson.hazelcast.spring.cache.demo.model.Dog;

public interface DogCache {

  Dog put(String key, Dog dog);
 
}
