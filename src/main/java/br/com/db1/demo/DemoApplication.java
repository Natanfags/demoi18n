package br.com.db1.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt","BR"));
		SpringApplication.run(DemoApplication.class, args);
	}
}