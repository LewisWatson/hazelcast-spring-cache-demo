package com.github.lewiswatson.hazelcast.spring.cache.demo.hazelcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.lewiswatson.hazelcast.spring.cache.demo.service.DogCacheProperties;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class HazelcastConfiguration {

  @Bean
  public Config hazelCastConfig(DogCacheProperties properties) {
    return new Config().addMapConfig(new MapConfig().setName(properties.getName())
        .setTimeToLiveSeconds(properties.getTimeToLiveSeconds()));
  }

}

