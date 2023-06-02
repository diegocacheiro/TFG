package com.usc.algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.usc.datos.Traza;

@Component
public class UmbralizacionAlgoritmo implements AlgoritmoInterfaz {
	@Override
	public List <Traza> ejecutar(List<Traza> array, Map<String, Object> parametros) {
		Double umbralArriba = Double.valueOf(parametros.get("Umbral superior").toString());
		Double umbralAbajo = Double.valueOf(parametros.get("Umbral inferior").toString());
		//Detecta baches a parti de dos umbrales
		List <Traza> baches = new ArrayList<>();
		
		for (Traza punto: array) {
			if (punto.getY() >= umbralArriba || punto.getY() <= umbralAbajo) {
				baches.add(punto);
			}
		}
        return baches;
    }
}
