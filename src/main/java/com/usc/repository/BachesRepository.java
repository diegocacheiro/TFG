package com.usc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usc.datos.Baches;

@Repository
public interface BachesRepository extends JpaRepository<Baches, Integer> {
}