package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

@Component
public class CommentForm {

    @NotBlank(message = "{comment.message.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String message;

    public CommentForm() {
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}