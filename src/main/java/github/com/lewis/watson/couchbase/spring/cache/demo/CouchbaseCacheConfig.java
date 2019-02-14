package github.com.lewis.watson.couchbase.spring.cache.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.spring.cache.CouchbaseCache;

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
  public CouchbaseCache couchbaseCache(Bucket bucket, DemoProperties properties) {
    return new CouchbaseCache(properties.getCache().getName(), bucket,
        properties.getCache().getTimeToLiveSeconds());
  }

}
