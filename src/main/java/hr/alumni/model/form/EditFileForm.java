package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

@Component
public class EditFileForm {

    private int fileId;

    @NotBlank(message = "{file.title.blank}")
    @SafeHtml(whitelistType = WhiteListType.NONE)
    private String title;

    @NotBlank(message = "{file.description.blank}")
    @SafeHtml(whitelistType = WhiteListType.RELAXED)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the fileId
     */
    public int getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
}
