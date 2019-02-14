package github.com.lewis.watson.couchbase.spring.cache.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import static github.com.lewis.watson.couchbase.spring.cache.demo.CacheConstants.DOGS;
import github.com.lewis.watson.couchbase.spring.cache.demo.model.Dog;

@Component
public class CachedDogService implements DogService {

  @Autowired
  private CacheManager cacheManager;

  @Override
  @CachePut(DOGS)
  public Dog putDog(Dog dog) {
    return dog;
  }

  @Override
  public Dog getDog(String key) {
    return cacheManager.getCache(DOGS).get(key, Dog.class);
  }

}
