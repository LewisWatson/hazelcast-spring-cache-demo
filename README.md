# Hazelcast Spring Cache Demo

Example Spring Boot app demonstrating how use [Hazelcast Data Grid] via the Spring cache abstraction.

## Run Test

An integration test is provided that runs with test instance of Hazelcast provided by `com.hazelcast.test.TestHazelcastInstanceFactory.TestHazelcastInstanceFactory()`

Use gradle to run the test:

```bash
./gradlew test
```

[Hazelcast Data Grid]: https://hazelcast.org/