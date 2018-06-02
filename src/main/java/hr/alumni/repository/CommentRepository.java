package hr.alumni.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID>{

}