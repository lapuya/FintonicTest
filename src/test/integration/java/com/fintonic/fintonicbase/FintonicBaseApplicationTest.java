package com.fintonic.fintonicbase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class FintonicBaseApplicationTests {

  @Autowired
  private ApplicationContext context;

  @Test
  void load_context(){
    assertThat(context).isNotNull();
  }

}
