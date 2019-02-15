package github.com.lewis.watson.hazelcast.spring.cache.demo.service;

import static github.com.lewis.watson.hazelcast.spring.cache.demo.CacheConstants.DOGS;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import github.com.lewis.watson.hazelcast.spring.cache.demo.model.Dog;

@Component
@CacheConfig(cacheNames = DOGS) // cache name has to be static, so must be hard coded
public class DogCacheImpl implements DogCache {

  @Override
  @CachePut(key = "#key")
  public Dog put(String key, Dog dog) {
    return dog;
  }

}
