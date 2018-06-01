package hr.alumni.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.alumni.model.Link;
import hr.alumni.model.form.LinkForm;
import hr.alumni.repository.LinkRepository;
import hr.alumni.service.FormFactory;

@Controller
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkRepository lr;

	@Autowired
    private FormFactory factory;
    
    @ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Link> getAllJson(Model model) {
		return lr.findAll();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String showAll(Model model) {
		List<Link> all = lr.findAll();

		model.addAttribute("links", all);

		return "links";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String getForm(Model model) {

		model.addAttribute("linkForm", new LinkForm());
		return "newLink";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String create(Model model, @Valid LinkForm lf, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("linkForm", lf);
			return "newLink";
		}

		Link newLink = factory.createLinkFromForm(lf);
		lr.save(newLink);

		return "redirect:/links";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String edit(Model model, @PathVariable Long id) {
		
		model.addAttribute("linkForm", factory.createFormFromLink(lr.findOne(id)));
		return "editLink";

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String update(Model model, @PathVariable Long id, @Valid LinkForm lf, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("linkForm", lf);
			return "editLink";
		}

        Link l = lr.findOne(id);
		factory.editLinkFromForm(l, lf);   
		lr.save(l);

		return "redirect:/links";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String delete(Model model, @PathVariable Long id, RedirectAttributes redirAttrs) {
		try{
			lr.delete(id);
		} catch (Exception e) {
			return "redirect:/links";
		}

		return "redirect:/links";
	}
}