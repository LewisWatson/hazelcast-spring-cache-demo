package github.com.lewis.watson.couchbase.spring.cache.demo;

import static github.com.lewis.watson.couchbase.spring.cache.demo.CacheConstants.DOGS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import github.com.lewis.watson.couchbase.spring.cache.demo.model.Dog;
import github.com.lewis.watson.couchbase.spring.cache.demo.service.DogService;
import github.com.lewis.watson.couchbase.spring.cache.demo.service.DogServiceProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogCacheIT {

  @Autowired
  private DogService dogService;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private DogServiceProperties properties;

  @Test
  public void testDogObjectsCanBeCachedAndOnlyRetrievableWithinTTL() throws IOException {

    /*
     * Given
     */

    String key = "bonnie-dog";
    final Dog givenValue = Dog.builder().name("Bonnie").breed("English Springer Spaniel").build();

    /*
     * When
     */

    dogService.putDog(givenValue);

    /*
     * Then
     */

    Dog actualValue = dogService.getDog(givenValue.toString());

    assertThat(actualValue)
        .as(String.format("cache should contain expected value for key: %s", key))
        .isEqualTo(givenValue);

    Cache cache = cacheManager.getCache(DOGS);

    await().atMost(properties.getCache().getTimeToLiveSeconds() + 1l, SECONDS)
        .until(keyNoLongerExistsInCache(cache, key));

  }

  private Callable<Boolean> keyNoLongerExistsInCache(Cache cache, String key) {
    return () -> cache.get(key) == null;
  }

}
