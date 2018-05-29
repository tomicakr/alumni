package hr.alumni.model.form;

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

@Component
public class PostForm {

    private UUID postId;

    @NotBlank(message = "{post.title.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String title;

    @NotBlank(message = "{post.short-description.blank}")
    @SafeHtml(whitelistType = WhiteListType.RELAXED)
    private String shortDescription;

    @NotBlank(message = "{post.type.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String postType;
    
    @SafeHtml(whitelistType = WhiteListType.RELAXED)
    private String longDescription;

    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String address;


    public PostForm() {
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

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

}
