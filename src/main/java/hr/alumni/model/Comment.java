package hr.alumni.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@Column
	@Type(type="uuid-char")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID commentId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	@CreationTimestamp
	@Column
	private Date date;

	@NotEmpty
	@Column
	private String message;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	public Comment() {
	}

	public UUID getCommentId() {
		return commentId;
	}

	public void setCommentId(UUID commentId) {
		this.commentId = commentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
