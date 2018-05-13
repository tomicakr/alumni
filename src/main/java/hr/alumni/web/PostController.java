package hr.alumni.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.Post;
import hr.alumni.repository.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostRepository pr;

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Post> allPosts(Model model) {
		List<Post> allPosts = pr.findAll();
		
		return allPosts;
	}

}