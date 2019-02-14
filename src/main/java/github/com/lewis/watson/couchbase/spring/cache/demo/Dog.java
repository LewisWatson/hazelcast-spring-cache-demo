package github.com.lewis.watson.couchbase.spring.cache.demo;

import java.io.Serializable;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Dog implements Serializable {

  private static final long serialVersionUID = -2320063941577184748L;

  String name;
  String breed;

}
