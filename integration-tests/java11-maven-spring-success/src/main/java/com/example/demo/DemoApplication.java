package com.example.demo;

import lombok.Data;
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

	@Data
	public static class TestResponse {
		private String score;
	}

	@RestController
	public static class DemoController {

		@GetMapping("/hello")
		@SwitchExhaustive
		public String hello() {
			TestEnum testEnum = TestEnum.Val1;
			switch (testEnum) {
				case Val1:
					TestResponse data = new TestResponse();
					data.setScore("return from switch");
					return data.getScore();
				case Val2:
					return "return from switch";
			}

			TestResponse data = new TestResponse();
			data.setScore("return from controller body");
			return data.getScore();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
