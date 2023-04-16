package com.usc.repository;


import java.util.List;

//import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.usc.datos.Baches;

public interface BachesRepository extends CrudRepository<Baches, Integer> {
	
	@Query("SELECT MAX(b.id) FROM Baches b")
    public Integer findMaxId();
	
	@Query("SELECT b.id, b.idTraza, b.algoritmoNombre, b.parametros FROM Baches b WHERE b.id = (SELECT MAX(t.id) FROM Baches t WHERE t.idTraza = b.idTraza AND t.algoritmoNombre = b.algoritmoNombre AND t.parametros = b.parametros) ORDER BY b.id DESC")
	public List<Object[]> AlgoritmosComparacion();
	
	@Query("SELECT b FROM Baches b WHERE b.id = :id1")
	public Baches AlgoritmosDatosComparacion(@Param("id1") Integer id);
	
}