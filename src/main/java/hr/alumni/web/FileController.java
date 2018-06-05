package hr.alumni.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.alumni.model.File;
import hr.alumni.model.form.EditFileForm;
import hr.alumni.model.form.FileUploadForm;
import hr.alumni.repository.FileRepository;
import hr.alumni.service.FormFactory;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fr;

    @Autowired
    private FormFactory formFactory;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allFiles(Model model) {

        List<File> files = fr.findAll();
        System.out.println(Arrays.toString(files.toArray()));
        model.addAttribute("files", files);

        return "files";
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int id, HttpServletResponse response)
            throws IOException {
        File file = fr.findOne(id);
        response.setContentType(file.getType());
        response.setContentLength(file.getContent().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        FileCopyUtils.copy(file.getContent(), response.getOutputStream());

        return "files";
    }

    @RequestMapping(value = { "/{id}/delete" }, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String deleteFile(@PathVariable int id) {
        fr.delete(id);
        return "files";
    }

    @RequestMapping(value = { "/newFile" }, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String createFile(Model model) {
        model.addAttribute("fuform", new FileUploadForm());

        return "newFile";
    }

    @RequestMapping(value = { "/{id}/editFile" }, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String editFile(Model model, @PathVariable int id) {
        File file = fr.findOne(id);
        EditFileForm f = formFactory.createFormFromFile(file);

        model.addAttribute("fuform", f);

        return "editFile";
    }

    @RequestMapping(value = { "/{id}/editFile" }, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String updateFile(@Valid EditFileForm fuform, BindingResult result, Model model, @PathVariable int id) throws IOException {
        
        if (result.hasErrors()) {
            model.addAttribute("fuform", fuform);
            return "editFile";
        }
        File file = fr.findOne(id);

        formFactory.editFileFromForm(fuform, file);
        fr.save(file);
        return "redirect:/files";
    }

    @RequestMapping(value = { "/newFile" }, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public String uploadFile(@Valid FileUploadForm fuform, BindingResult result, Model model) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("fuform", fuform);
            System.out.println(Arrays.toString(result.getAllErrors().toArray()));
            return "newFile";
        }

        File file = formFactory.createFileFromForm(fuform);
        fr.save(file);
        return "redirect:/files";
    }

}
