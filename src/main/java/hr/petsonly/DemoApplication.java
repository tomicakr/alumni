package hr.petsonly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		PasswordEncoder pe = new BCryptPasswordEncoder(9);
		String s = pe.encode("sifrasifra");
		System.out.println(s.length());
		System.out.println(s);
		System.out.println(pe.matches("sifrasifra", s));
		SpringApplication.run(DemoApplication.class, args);
	}
}

