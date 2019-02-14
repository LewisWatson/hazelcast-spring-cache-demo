package github.com.lewis.watson.couchbase.spring.cache.demo;

import java.io.IOException;

public interface DogCacher {

  void put(String id, Dog dog) throws IOException;

}
