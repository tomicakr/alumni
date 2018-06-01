package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

@Component
public class CategoryForm {

    private Long categoryId;

    @NotBlank(message = "{category.name.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String name;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public Long getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
