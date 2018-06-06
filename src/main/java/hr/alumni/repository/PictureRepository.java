package hr.alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer>{

}