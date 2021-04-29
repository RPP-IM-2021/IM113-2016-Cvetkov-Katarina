package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Smer;

public interface SmerRepository extends JpaRepository<Smer, Integer> {

	Collection<Smer> findByNazivContainingIgnoreCase(String naziv);
	
}
