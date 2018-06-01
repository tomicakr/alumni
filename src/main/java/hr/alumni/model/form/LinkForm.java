package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

@Component
public class LinkForm {

    private Long linkId;

    @NotBlank(message = "{link.title.blank}")
    @SafeHtml(whitelistType = WhiteListType.RELAXED)
    private String title;

    @NotBlank(message = "{link.url.blank}")
    @URL
    private String url;

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
