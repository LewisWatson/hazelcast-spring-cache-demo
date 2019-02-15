package github.com.lewis.watson.hazelcast.spring.cache.demo;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import java.util.concurrent.Callable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.test.TestHazelcastInstanceFactory;
import github.com.lewis.watson.hazelcast.spring.cache.demo.model.Dog;
import github.com.lewis.watson.hazelcast.spring.cache.demo.service.DogCache;
import github.com.lewis.watson.hazelcast.spring.cache.demo.service.DogCacheProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogCacheIT {

  @TestConfiguration
  static class DogCacheITMockHazelcastConfig {

    @Bean
    public HazelcastInstance testHazelcastInstance(Config config) {
      return new TestHazelcastInstanceFactory().newHazelcastInstance(config);
    }

  }

  @Autowired
  private DogCache dogCache;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private DogCacheProperties properties;

  @Test
  public void testDogsCanBeCachedAndOnlyRetrievableWithinTTL() {

    /*
     * Given
     */

    String key = "bonnie-dog";
    final Dog givenValue = Dog.builder().name("Bonnie").breed("English Springer Spaniel").build();

    /*
     * When
     */

    dogCache.put(key, givenValue);

    /*
     * Then
     */

    Cache cache = cacheManager.getCache(properties.getName());

    Dog actualValue = cache.get(key, Dog.class);

    assertThat(actualValue)
        .as(String.format("cache should contain expected value for key: %s", key))
        .isEqualTo(givenValue);

    await().atMost(properties.getTimeToLiveSeconds() + 1l, SECONDS)
        .until(keyNoLongerExistsInCache(cache, key));

  }

  private Callable<Boolean> keyNoLongerExistsInCache(Cache cache, String key) {
    return () -> cache.get(key) == null;
  }

}
