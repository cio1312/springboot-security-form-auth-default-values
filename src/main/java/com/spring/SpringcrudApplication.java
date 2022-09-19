package com.spring;


import org.springframework.beans.factory.annotation.Autowired;
//use postman to run this
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
//@ComponentScan(basePackageClasses = DepartmentController.class)
public class SpringcrudApplication {

	public static void main(String[] args) {
		System.out.println("insdie mail");
		SpringApplication.run(SpringcrudApplication.class, args);
	}
	


}
