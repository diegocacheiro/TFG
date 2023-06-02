package com.usc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usc.datos.Baches;
import com.usc.repository.BachesRepository;

@Service
public class BachesService {
	
	@Autowired
	private BachesRepository bachesRepository;

	public Baches save(Baches b) {
		return bachesRepository.save(b);
	}
	
	public Integer findMaxId() {
		return bachesRepository.findMaxId();
	}
	
	public List<Object[]> AlgoritmosComparacion() {
		return bachesRepository.AlgoritmosComparacion();
	}
	
	public Baches AlgoritmosDatosComparacion(Integer id1) {
		return bachesRepository.AlgoritmosDatosComparacion(id1);
	}
	
	public void deleteById (Integer id) {
		bachesRepository.deleteById(id);
	}
}

