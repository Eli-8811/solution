package com.core.solution;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.core.solution")
@MapperScan("com.core.solution.mapper")
@SpringBootApplication()
public class SolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}//https://www.youtube.com/watch?v=T2cdL-KvVaY&list=PLqhnP4YYLcb4X3AgmW699wyAhoP2SYf5j

}
 