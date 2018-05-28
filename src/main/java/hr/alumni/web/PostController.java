package hr.alumni.web;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Comment;
import hr.alumni.model.Post;
import hr.alumni.model.PostType;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.form.CommentForm;
import hr.alumni.model.form.PostForm;
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
	public List<Post> allPosts(Model model, @RequestParam(value = "type", required = false) String type) {
		List<Post> allPosts = pr.findAll();

		allPosts.sort(new Comparator<Post>() {
			public int compare(Post p1, Post p2){
				return 1;
				//return -p1.getCreateDate().compareTo(p2.getCreateDate());
			}
		});

		allPosts.forEach(post -> {
			post.getComments().sort(new Comparator<Comment>() {
				public int compare(Comment c1, Comment c2){
					return -c1.getDate().compareTo(c2.getDate());
				}
			});
		});

		if(type != null){
			List<Post> posts = allPosts.stream().filter((p) -> p.getPostType() == PostType.valueOf(type)).collect(Collectors.toList());

			System.out.println(Arrays.toString(posts.toArray()));
			return posts;
		}

		return allPosts;
	}

	@RequestMapping(value = "/newPost", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String newPost(Model model) {
		
		model.addAttribute("postForm", new PostForm());
		return "newPost";
	}

	@RequestMapping(value = "/newPost", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String createPost(Model model, @Valid PostForm postForm, BindingResult result) {
		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (result.hasErrors()) {
			model.addAttribute("postForm", postForm);
			return "newPost";
		}

		Post post = factory.createPostFromForm(postForm, userInSession);

		pr.save(post);
		return "index";
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

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String deleteUser(Model model, @PathVariable UUID id) {

		pr.delete(pr.findOne(id));

		return "index";
	}
}