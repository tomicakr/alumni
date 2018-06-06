package hr.alumni.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Picture;
import hr.alumni.repository.PictureRepository;

@Controller
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureRepository pr;

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> getPicture(@PathVariable int id, HttpServletResponse response)
            throws IOException {
        Picture pic = pr.findOne(id);
        response.setContentType(pic.getType());
        response.setContentLength(pic.getContent().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"pic\"");

        FileCopyUtils.copy(pic.getContent(), response.getOutputStream());

        return ResponseEntity.ok(null);
    }
}
