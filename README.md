# Couchbase Spring Cache Demo

Example Spring Boot app using [couchbase-spring-cache] library, which is an "An implementation for Spring Cache based on Couchbase Java SDK 2.x".

This particular demo annotates a simple `put` method on a `DogService` that thats nothing other than return a value that has been passed to it in order to induce caching.

Observations:

- [Java Serialization] is used to store values as a binary document. Therefore they will only be readable by similar JVM's on the other end.
- The generated document key is constructed using a `cache` prefix, followed by the name of a cache (in this case `dogs`), followed by the output of `.toString()` of the value being cached.
  - Since I happen to be storing an object enhanced by lombok, the key ends up with the entire stringified version of the document value.

![screenshot](screenshot.png)

## Integration Tests


```bash
./gradlew test
```

[Spring Cache]: https://spring.io/guides/gs/caching/