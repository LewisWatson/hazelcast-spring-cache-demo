package github.com.lewis.watson.couchbase.spring.cache.demo.couchbase;

import static github.com.lewis.watson.couchbase.spring.cache.demo.CacheConstants.DOGS;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import github.com.lewis.watson.couchbase.spring.cache.demo.service.DogServiceProperties;

@Configuration
public class CouchbaseCacheConfig {

  @Bean(destroyMethod = "disconnect")
  public Cluster cluster(CouchbaseProperties properties) {
    return CouchbaseCluster.create(properties.getBootstrapHosts())
        .authenticate(properties.getBucket().getUsername(), properties.getBucket().getPassword());
  }

  @Bean(destroyMethod = "close")
  public Bucket bucket(Cluster cluster, CouchbaseProperties properties) {
    return cluster.openBucket(properties.getBucket().getName());
  }

  @Bean
  public CacheManagerCustomizer<CouchbaseCacheManager> cacheManagerCustomizer(Bucket bucket,
      DogServiceProperties properties) {
    return c -> c.prepareCache(DOGS, CacheBuilder.newInstance(bucket)
        .withExpiration(properties.getCache().getTimeToLiveSeconds()));
  }
}
