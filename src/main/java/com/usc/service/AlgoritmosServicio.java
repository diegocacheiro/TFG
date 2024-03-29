package com.usc.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.usc.datos.Algoritmo;

@Service
public class AlgoritmosServicio {
	public List<Algoritmo> getAllEntities() {
		List<Algoritmo> basicEntities = new ArrayList<Algoritmo>();
		List<String> parametros1 = new ArrayList<>();
		parametros1.add("Umbral superior");
		parametros1.add("Umbral inferior");
		basicEntities.add(new Algoritmo(1, "Umbralización", "Ecuentra los baches cuya aceleración vertical está entre el umbral superior e inferior especificado.", parametros1));
		
		List<String> parametros2 = new ArrayList<>();
		parametros2.clear();
		parametros2.add("Sigma");
		basicEntities.add(new Algoritmo(2, "Variabilidad", "Ecuentra los baches que están fuera del rango normal de aceleraciones utilizando un criterio basado en la media, la varianza y la desviación típica de las aceleraciones.", parametros2));
		return basicEntities;
	}
}
