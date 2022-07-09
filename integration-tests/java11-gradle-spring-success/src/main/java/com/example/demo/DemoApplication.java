package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hixon.switchexhaustivenesschecker.SwitchExhaustive;

@SpringBootApplication
public class DemoApplication {

	public enum TestEnum {
		Val1,
		Val2
	}

	@RestController
	public static class DemoController {

		@GetMapping("/hello")
		@SwitchExhaustive
		public String hello() {
			TestEnum testEnum = TestEnum.Val1;
			switch (testEnum) {
				case Val1:
					return "return from switch";
				case Val2:
					return "return from switch";
			}
			return "return from controller body";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
