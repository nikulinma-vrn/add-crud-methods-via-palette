package com.example.crudresttest;

import com.example.crudresttest.utils.UserGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CrudresttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudresttestApplication.class, args);

		UserGenerator.bulkUserAdd(100);
	}
}
