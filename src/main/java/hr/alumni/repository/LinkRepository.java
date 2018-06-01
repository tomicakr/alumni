package hr.alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long>{

}