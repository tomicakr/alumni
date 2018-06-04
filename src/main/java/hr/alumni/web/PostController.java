package hr.alumni.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Comment;
import hr.alumni.model.Post;
import hr.alumni.model.PostCategory;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.form.CommentForm;
import hr.alumni.model.form.PostForm;
import hr.alumni.repository.CommentRepository;
import hr.alumni.repository.PostCategoryRepository;
import hr.alumni.repository.PostRepository;
import hr.alumni.service.FormFactory;
import hr.alumni.service.email.EmailServiceImpl;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository pr;

	@Autowired
	private CommentRepository cr;

	@Autowired
	private PostCategoryRepository pcr;

	@Autowired
	private FormFactory factory;

	@Autowired
	private EmailServiceImpl emailService;

	@ResponseBody
	@GetMapping(value = "/archived")
	public List<Post> getArchived() {
		List<Post> allPosts = pr.findAll();

		List<Post> archived = new ArrayList<>();

		allPosts.forEach(post -> {
			if (post.getArchived()) {
				archived.add(post);
			}
		});

		return archived;
	}

	@GetMapping(value="/archive")
	public String archive() {
		return "archive";
	}
	

	@ResponseBody
	@PostMapping(value = "/{id}/archive")
	public String archive(@PathVariable UUID id) {
		Post post = pr.getOne(id);

		post.setArchived(true);

		pr.save(post);

		return "/";
	}

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Post> allPosts(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "archived", required = false) Integer archived) {
		List<Post> all = pr.findAll();

		List<Post> allPosts = new ArrayList<>();

		if(archived != null && archived.equals(1)) {
			allPosts = all.stream().filter((p) -> p.getArchived())
			.collect(Collectors.toList());
		} else {
			allPosts = all.stream().filter((p) -> !p.getArchived())
			.collect(Collectors.toList());
		}
	

		allPosts.sort(new Comparator<Post>() {
			public int compare(Post p1, Post p2) {
				return -p1.getCreateDate().compareTo(p2.getCreateDate());
			}
		});

		allPosts.forEach(post -> {
			post.getComments().sort(new Comparator<Comment>() {
				public int compare(Comment c1, Comment c2) {
					return -c1.getDate().compareTo(c2.getDate());
				}
			});
		});

		if (type != null) {
			PostCategory pc = pcr.findByName(type);
			System.out.println(pc.getName());
			List<Post> posts = allPosts.stream().filter((p) -> p.getPostCategories().contains(pc))
					.collect(Collectors.toList());

			return posts;
		}

		return allPosts;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getPost(Model model, @PathVariable UUID id) {

		Post post = pr.getOne(id);

		post.getComments().sort(new Comparator<Comment>() {
			public int compare(Comment c1, Comment c2) {
				return -c1.getDate().compareTo(c2.getDate());
			}
		});

		model.addAttribute("post", post);
		model.addAttribute("comments", post.getComments());

		return "post";
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

		Set<String> mailsToSend = new HashSet<>();
		post.getPostCategories().forEach(category -> {
			category.getUsers().forEach((user) -> {
				mailsToSend.add(user.getEmail());
			});
		});
		mailsToSend.forEach((mail) -> {
			emailService.sendSimpleMessage(mail, "[OBAVIJEST] " + post.getTitle(), post);
		});

		pr.save(post);
		return "index";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String editPost(Model model, @PathVariable UUID id) {

		model.addAttribute("postForm", factory.createFormFromPost(pr.getOne(id)));
		return "editPost";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String updatePost(Model model, @PathVariable UUID id, @Valid PostForm pform, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("postForm", pform);
			return "editPost";
		}

		Post post = pr.getOne(id);
		factory.editPostFromForm(pform, post);
		pr.save(post);

		model.addAttribute("post", post);
		model.addAttribute("comments", post.getComments());

		return "post";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public String deletePost(Model model, @PathVariable UUID id) {

		pr.delete(pr.findOne(id));

		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/comment", method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("isAuthenticated()")
	public Comment createComment(Model model, @PathVariable UUID id, @RequestBody @Valid CommentForm cForm, BindingResult result) {
		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		Post post = pr.findOne(id);

		if (result.hasErrors()) {
			return null;
		}

		Comment comment = factory.createCommentFromForm(cForm, userInSession);
		post.addComment(comment);
		Comment saved = cr.save(comment);
		
		return saved;
	}

	@DeleteMapping(value="/comment/{commentId}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") UUID commentId) {
		System.out.println("Tu smo");
		try {
			Comment comment = cr.findOne(commentId);
			cr.delete(comment);
		} catch(Exception e) {
			System.out.println("nije obrisan");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}
	
}