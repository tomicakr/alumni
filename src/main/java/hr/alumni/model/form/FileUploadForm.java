package hr.alumni.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadForm {

    @NotBlank(message = "{file.title.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String title;

    @NotBlank(message = "{file.description.blank}")
    @SafeHtml(whitelistType = WhiteListType.RELAXED)
    private String description;

    @NotNull
    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
