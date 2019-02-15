# Hazelcast Spring Cache Demo

[![Build Status](https://travis-ci.org/LewisWatson/hazelcast-spring-cache-demo.svg?branch=develop)](https://travis-ci.org/LewisWatson/hazelcast-spring-cache-demo)

Example Spring Boot app demonstrating how use [Hazelcast Data Grid] via the Spring cache abstraction.

## Run Test

An integration test is provided that runs with test instance of Hazelcast provided by `com.hazelcast.test.TestHazelcastInstanceFactory.TestHazelcastInstanceFactory()`

Use gradle to run the test:

```bash
./gradlew test
```

[Hazelcast Data Grid]: https://hazelcast.org/
