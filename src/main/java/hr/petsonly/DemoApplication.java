package hr.petsonly;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			System.out.println(UUID.randomUUID());
		}
		SpringApplication.run(DemoApplication.class, args);
	}
}
