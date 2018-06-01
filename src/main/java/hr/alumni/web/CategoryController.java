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

import hr.alumni.model.PostCategory;
import hr.alumni.model.form.CategoryForm;
import hr.alumni.repository.PostCategoryRepository;
import hr.alumni.service.FormFactory;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private PostCategoryRepository pcr;

	@Autowired
	private FormFactory factory;

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<PostCategory> getAllJson(Model model) {
		return pcr.findAll();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String showAll(Model model) {
		List<PostCategory> all = pcr.findAll();

		model.addAttribute("categories", all);

		return "categories";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String getForm(Model model) {

		model.addAttribute("categoryForm", new CategoryForm());
		return "newCategory";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String create(Model model, @Valid CategoryForm cf, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("categoryForm", cf);
			return "newCategory";
		}

		PostCategory newCategory = factory.createCategoryFromForm(cf);
		pcr.save(newCategory);

		return "redirect:/categories";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String edit(Model model, @PathVariable Long id) {
		
		model.addAttribute("categoryForm", factory.createFormFormCategory(pcr.findOne(id)));
		return "editCategory";

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String update(Model model, @PathVariable Long id, @Valid CategoryForm cf, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("categoryForm", cf);
			return "editCategory";
		}

		PostCategory pc = pcr.getOne(id);
		pc.setName(cf.getName());

		pcr.save(pc);

		return "redirect:/categories";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String delete(Model model, @PathVariable Long id, RedirectAttributes redirAttrs) {
		try{
			pcr.delete(id);
		} catch (Exception e) {
			return "redirect:/categories";
		}

		return "redirect:/categories";
	}
}