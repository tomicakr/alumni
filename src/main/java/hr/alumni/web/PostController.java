package hr.alumni.web;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Comment;
import hr.alumni.model.Post;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.form.CommentForm;
import hr.alumni.repository.PostRepository;
import hr.alumni.service.FormFactory;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository pr;

	@Autowired
	private FormFactory factory;

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Post> allPosts(Model model) {
		List<Post> allPosts = pr.findAll();

		allPosts.forEach(post -> {
			System.out.println("NASLOV" + post.getTitle());
			post.getComments().forEach(com -> System.out.println(com.getMessage()));
		});

		return allPosts;
	}

	@RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
	@PreAuthorize("hasRole('KORISNIK')")
	public String createComment(Model model, @PathVariable UUID id, @Valid CommentForm cForm, BindingResult result) {
		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (result.hasErrors()) {
			return "index";
		}

		Comment comment = factory.createCommentFromForm(cForm, userInSession);
		Post post = pr.findOne(id);
		post.getComments().add(comment);
		comment.setPost(post);

		pr.save(post);
		return "index";
	}
}