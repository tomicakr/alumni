package hr.alumni.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class CommentForm {

    @NotNull
    @NotEmpty(message = "{comment.message.empty}")
    @NotBlank
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