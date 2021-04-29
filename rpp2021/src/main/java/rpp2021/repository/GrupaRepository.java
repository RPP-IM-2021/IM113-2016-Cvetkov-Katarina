package rpp2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp2021.model.Grupa;

public interface GrupaRepository extends JpaRepository<Grupa, Integer> {
	
	Collection<Grupa> findByOznakaContainingIgnoreCase(String oznaka);

}
