package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Projekat;

public interface ProjekatRepository extends JpaRepository<Projekat, Integer> {

	Collection<Projekat> findByNazivContainingIgnoreCase(String naziv);
	
}
