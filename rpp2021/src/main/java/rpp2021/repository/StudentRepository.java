package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Grupa;
import rpp2021.model.Projekat;
import rpp2021.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Collection<Student> findByImeContainingIgnoreCase(String ime);
	Collection<Student> findByProjekat(Projekat projekat);
	Collection<Student> findByGrupa(Grupa grupa);
	
}
