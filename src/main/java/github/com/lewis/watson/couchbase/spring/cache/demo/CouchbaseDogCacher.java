package github.com.lewis.watson.couchbase.spring.cache.demo;

import org.springframework.stereotype.Component;
import com.couchbase.client.spring.cache.CouchbaseCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CouchbaseDogCacher implements DogCacher {

  private final CouchbaseCache cache;

  @Override
  public void put(String key, Dog dog) {
    log.debug("caching {}:{}", key, dog);
    cache.put(key, dog);
  }

}
