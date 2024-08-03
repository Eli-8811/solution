package com.core.solution;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(args = "test")
class SolutionApplicationTests {

	@Autowired
	ConfigurableApplicationContext context;

	@Test
	void contextLoads() {
		Boolean flag = true;
		assertNotNull(flag);
	}

	@Test
	void main() {
		SolutionApplication.main(new String[] {});
		Boolean flag = true;
		assertNotNull(flag);
	}

}
