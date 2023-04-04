package com.usc.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.usc.datos.Algoritmo;

@Service
public class AlgoritmosServicio {
	public List<Algoritmo> getAllEntities() {
		List<Algoritmo> basicEntities = new ArrayList<Algoritmo>();
		basicEntities.add(new Algoritmo(1, "Umbralización", "Ecuentra los baches cuya aceleración verticale está por encima del umbral superior o por debajo del umbral inferior"));
		basicEntities.add(new Algoritmo(2, "Variabilidad", "Ecuentra los baches que están fuera del rango normal de aceleraciones utilizando un criterio basado en la media, la varianza y la desviación típica de las aceleraciones"));
		return basicEntities;
	}
}