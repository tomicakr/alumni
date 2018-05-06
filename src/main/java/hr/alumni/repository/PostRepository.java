package hr.alumni.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Post;

public interface PostRepository extends JpaRepository<Post, UUID>{

}