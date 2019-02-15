package github.com.lewis.watson.hazelcast.spring.cache.demo.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "dog-cache")
@Data
public class DogCacheProperties {

  /**
   * Name of cache, for use in spring cache abstraction
   */
  private String name;

  /**
   * Seconds before the value should be evicted from the cache
   */
  private int timeToLiveSeconds;

}
