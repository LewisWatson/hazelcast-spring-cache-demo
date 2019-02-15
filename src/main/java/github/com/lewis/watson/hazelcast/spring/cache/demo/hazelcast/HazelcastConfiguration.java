package github.com.lewis.watson.hazelcast.spring.cache.demo.hazelcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import github.com.lewis.watson.hazelcast.spring.cache.demo.service.DogCacheProperties;

@Configuration
public class HazelcastConfiguration {

  @Bean
  public Config hazelCastConfig(DogCacheProperties properties) {
    return new Config().addMapConfig(new MapConfig().setName(properties.getName())
        .setTimeToLiveSeconds(properties.getTimeToLiveSeconds()));
  }

}

