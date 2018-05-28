package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class CommentForm {

    @NotBlank(message = "{comment.message.blank}")
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