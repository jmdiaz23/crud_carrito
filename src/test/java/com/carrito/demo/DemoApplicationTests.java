package com.carrito.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = "com.carrito")
@EntityScan(basePackages = "com.carrito.model")
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
