package hr.alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.PostCategory;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long>{

    PostCategory findByName(String name);
}