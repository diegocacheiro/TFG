package com.usc.repository;


import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.usc.datos.Baches;

public interface BachesRepository extends CrudRepository<Baches, Integer> {
	
	@Query("SELECT MAX(b.id) FROM Baches b")
    public Integer findMaxId();
	
	@Query("SELECT b.id, b.idTraza, b.algoritmoNombre FROM Baches b WHERE b.id = (SELECT MAX(t.id) FROM Baches t WHERE t.idTraza = b.idTraza AND t.algoritmoNombre = b.algoritmoNombre) ORDER BY b.id DESC")
	public List<Object[]> AlgoritmosComparacion();


	
}