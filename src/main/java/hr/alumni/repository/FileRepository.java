package hr.alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.File;

public interface FileRepository extends JpaRepository<File, Integer>{

}