package hr.alumni.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hr.alumni.model.Comment;
import hr.alumni.model.File;
import hr.alumni.model.Link;
import hr.alumni.model.Picture;
import hr.alumni.model.Post;
import hr.alumni.model.PostCategory;
import hr.alumni.model.Role;
import hr.alumni.model.User;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.form.CategoryForm;
import hr.alumni.model.form.CommentForm;
import hr.alumni.model.form.EditFileForm;
import hr.alumni.model.form.EditUserForm;
import hr.alumni.model.form.FileUploadForm;
import hr.alumni.model.form.LinkForm;
import hr.alumni.model.form.PostForm;
import hr.alumni.model.form.RegistrationForm;
import hr.alumni.repository.PostCategoryRepository;
import hr.alumni.repository.RoleRepository;
import hr.alumni.repository.UserRepository;
@Service
public class FormFactory {
	
	private final RoleRepository rr;
	private final PasswordEncoder pe;
	private final UserRepository ur;
	private final PostCategoryRepository pcr;


	@Autowired
	public FormFactory(RoleRepository rr, PasswordEncoder pe, UserRepository ur, PostCategoryRepository pcr) {
		this.rr = rr;
		this.pe = pe;
		this.ur = ur;
		this.pcr = pcr;
	}

	public User createUserFromForm(RegistrationForm rf){
		User u = new User();

		u.setFirstName(rf.getFirstName());
		u.setLastName(rf.getLastName());
		u.setPhone(rf.getPhone());
		u.setEmail(rf.getEmail());
		u.setAddress(rf.getAddress());
		u.setPassword(pe.encode(rf.getPassword()));
		u.setRoles(Arrays.asList(rr.findByName("ROLE_KORISNIK")));
		u.setBirthday(Date.valueOf(rf.getBirthday()));
		u.setGraduation(Date.valueOf(rf.getGraduation()));
		

		List<Role> roles = new ArrayList<>();
		Role r = rr.findByNameIgnoreCase("ROLE_KORISNIK");
		roles.add(r);
		u.setRoles(roles);
		return u;
	}

	public void editUserFromForm(User user, EditUserForm ef) {

		user.setFirstName(ef.getFirstName());
		user.setLastName(ef.getLastName());
		user.setPhone(ef.getPhone());
		user.setEmail(ef.getEmail());
		user.setAddress(ef.getAddress());
		user.setBirthday(Date.valueOf(ef.getBirthday()));
		user.setGraduation(Date.valueOf(ef.getGraduation()));

		user.getSubscriptions().clear();
		if(ef.getSubscriptions() != null){
			for (String postCategoryName : ef.getSubscriptions()) {
				user.getSubscriptions().add(pcr.findByName(postCategoryName));
			}
		} else {
			user.getSubscriptions().clear();
		}

	}

	public Comment createCommentFromForm(CommentForm cForm, CustomUserDetails user) {
		Comment comment = new Comment();

		User u = ur.findOne(user.getUserId());

		comment.setMessage(cForm.getMessage());
		comment.setUsername(u.getFirstName() + " " + u.getLastName());

		return comment;
	}

	public Post createPostFromForm(PostForm postForm, CustomUserDetails userInSession) throws IOException{
		Post post = new Post();
		Picture picture = new Picture();

		if(postForm.getPicture() != null) {
			picture.setType(postForm.getPicture().getContentType());
			picture.setContent(postForm.getPicture().getBytes());
			post.setPicture(picture);
		}

		post.setAddress(postForm.getAddress());
		post.setLongDescription(postForm.getLongDescription());
		post.setShortDescription(postForm.getShortDescription());
		post.setTitle(postForm.getTitle());
		List<PostCategory> postCategories = new ArrayList<>();
		for (String postCategoryName : postForm.getPostCategories()) {
			postCategories.add(pcr.findByName(postCategoryName));
		}
		post.setPostCategories(postCategories);

		return post;
	}

	public void editPostFromForm(PostForm postForm, Post post) {

		post.setAddress(postForm.getAddress());
		post.setLongDescription(postForm.getLongDescription());
		post.setShortDescription(postForm.getShortDescription());
		post.setTitle(postForm.getTitle());
		post.getPostCategories().clear();
		for (String postCategoryName : postForm.getPostCategories()) {
			post.getPostCategories().add(pcr.findByName(postCategoryName));
		}

	}

	public PostForm createFormFromPost(Post post) {
		PostForm form = new PostForm();

		form.setPostId(post.getPostId());
		form.setAddress(post.getAddress());
		form.setLongDescription(post.getLongDescription());
		form.setShortDescription(post.getShortDescription());
		form.setTitle(post.getTitle());

		int size = post.getPostCategories().size();
		String[] postCategoryNames = new String[size];
		for (int i = 0; i < size; i++) {
			postCategoryNames[i] = post.getPostCategories().get(i).getName();
		}
		

		form.setPostCategories(postCategoryNames);

		return form;
	}

	public CategoryForm createFormFormCategory(PostCategory category) {
		CategoryForm cf = new CategoryForm();

		cf.setCategoryId(category.getPostCategoryId());
		cf.setName(category.getName());

		return cf;
	}

	public PostCategory createCategoryFromForm(CategoryForm cf) {
		PostCategory pc = new PostCategory();

		pc.setName(cf.getName());

		return pc;
	}

	public void editCategoryFromForm(PostCategory pc, CategoryForm cf) {
		pc.setName(cf.getName());
	}

	public LinkForm createFormFromLink(Link l) {
		LinkForm lf = new LinkForm();

		lf.setLinkId(l.getLinkId());
		lf.setTitle(l.getTitle());
		lf.setUrl(l.getUrl());

		return lf;
	}

	public Link createLinkFromForm(LinkForm lf) {
		Link l = new Link();

		l.setTitle(lf.getTitle());
		l.setUrl(lf.getUrl());

		return l;
	}

	public void editLinkFromForm(Link l, LinkForm lf) {
		l.setTitle(lf.getTitle());
		l.setUrl(lf.getUrl());
	}

	public File createFileFromForm(FileUploadForm fuform) throws IOException{
         
        File file = new File();
         
		MultipartFile multipartFile = fuform.getFile();
		
		file.setName(multipartFile.getOriginalFilename());
        file.setTitle(fuform.getTitle());
        file.setDescription(fuform.getDescription());
        file.setType(multipartFile.getContentType());
        file.setContent(multipartFile.getBytes());
	   
		return file;
	}

	public EditFileForm createFormFromFile(File file) {
		 
		EditFileForm fuform = new EditFileForm();

		fuform.setFileId(file.getFileId());
        fuform.setTitle(file.getTitle());
		fuform.setDescription(file.getDescription());
	   
		return fuform;
	}
	
	public void editFileFromForm(EditFileForm fuform, File file) {
		 
        file.setTitle(fuform.getTitle());
		file.setDescription(fuform.getDescription());
	   
	}


}
