package hr.alumni.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.NotBlank;

@Table(name = "post_categories")
@Entity
@JsonIgnoreProperties({"users", "posts"})
public class PostCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postCategoryId;

	@Column
	@NotBlank
	private String name;

	@ManyToMany(mappedBy = "subscriptions")
	private List<User> users;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "postCategories", cascade = CascadeType.ALL)
	private List<Post> posts;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPostCategoryId() {
		return postCategoryId;
	}

	public void setPostCategoryId(Long postCategoryId) {
		this.postCategoryId = postCategoryId;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}