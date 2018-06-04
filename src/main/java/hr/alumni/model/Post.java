package hr.alumni.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;


@Table(name = "posts")
@Entity
public class Post {

	@Id
	@Column
	@Type(type="uuid-char")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID postId;
	
	@NotEmpty
	@Column
	private String title;
	
	@NotEmpty
	@Column(length = 10000)
	private String shortDescription;
	
	@Column(length = 100000)
	private String longDescription;
	
	@Column
	private String address;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifyDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinTable(name = "posts_categories", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "postId"), inverseJoinColumns = @JoinColumn(name = "post_category_id", referencedColumnName = "postCategoryId"))
	private List<PostCategory> postCategories;
	
	@OneToMany(
        mappedBy = "post", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Comment> comments;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean archived = false;
	
	public Post() {
	}

	public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }
 
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
	
	public UUID getPostId() {
		return postId;
	}

	public void setPostId(UUID postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PostCategory> getPostCategories() {
		return postCategories;
	}

	public void setPostCategories(List<PostCategory> postCategories) {
		this.postCategories = postCategories;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean getArchived() {
		return this.archived;
	}
	
	public void setArchived(boolean archived) {
		this.archived = archived;
	}

}
