package github.com.lewis.watson.couchbase.spring.cache.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.*;
import java.io.IOException;
import java.util.concurrent.Callable;
import static java.util.concurrent.TimeUnit.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.couchbase.client.spring.cache.CouchbaseCache;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouchbaseDogCacherIT {

  @Autowired
  private CouchbaseDogCacher dogCacher;

  @Autowired
  private CouchbaseCache couchbaseCache;

  @Autowired
  private DemoProperties dogServiceProperties;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void flushBucket() {
    couchbaseCache.setAlwaysFlush(true);
    couchbaseCache.clear();
  }

  @Test
  public void ttlTest() {
    assertThat(couchbaseCache.getTtl()).as("couchbase cache should have expected TTL")
        .isEqualTo(dogServiceProperties.getCache().getTimeToLiveSeconds());
  }

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

    dogCacher.put(key, givenValue);

    /*
     * Then
     */

    Dog actualValue = objectMapper.convertValue(couchbaseCache.get(key, String.class), Dog.class);
    assertThat(actualValue)
        .as(String.format("cache should contain expected value for key: %s", key))
        .isEqualTo(givenValue);

    await().atMost(couchbaseCache.getTtl() + 1l, SECONDS).until(keyNoLongerExistsInCache(key));

  }

  private Callable<Boolean> keyNoLongerExistsInCache(String key) {
    return () -> couchbaseCache.get(key) == null;
  }

}
