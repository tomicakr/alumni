package hr.alumni.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import hr.alumni.model.Post;
import hr.alumni.model.PostType;

@RunWith(SpringRunner.class)
@DataJpaTest
@SqlConfig(dataSource = "/data.sql")
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PostRepositoryTest {

	@Autowired
	private PostRepository pr;
	
	@Test
	@Rollback(false)
	public void test() {
		Post p = new Post();
		
		p.setAddress("Unska 3");
		p.setPostType(PostType.EVENT);
		p.setTitle("Post koji se testira");
		p.setShortDescription("ovaj se post trenutno testira");

		pr.save(p);
	}

}
