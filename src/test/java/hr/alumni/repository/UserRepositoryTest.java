package hr.alumni.repository;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import hr.alumni.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@SqlConfig(dataSource = "/data.sql")
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository ur;
	
	@Test
	@Rollback(false)
	public void test() {
		User u = new User();
		
		u.setFirstName("Tomislav");
		u.setLastName("Kravaršćan");
		u.setEmail("kravarscan.tomica@gmail.com");
		u.setAddress("Klinovec 36");
		u.setBirthday(Date.valueOf("1996-07-18"));
		u.setGraduation(Date.valueOf("2022-07-16"));
		u.setPhone("0096569888");
		ur.save(u);
	}

}
