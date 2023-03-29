package com.usc.algoritmos;

import java.util.ArrayList;
import java.util.List;

import com.usc.datos.Traza;

public class Algoritmos {
	
	public static List <Traza> Umbralización (List<Traza> array) {
		//Detecta baches a parti de dos umbrales
		Double umbralArriba = 11.0;
		Double umbralAbajo = 8.0;
		List <Traza> baches = new ArrayList<>();
		
		for (Traza punto: array) {
			if (punto.getY() >= umbralArriba || punto.getY() <= umbralAbajo) {
				baches.add(punto);
			}
		}
        return baches;
    }
	
    public static List<Traza> Variabilidad(List<Traza> array) {
    	//Parámetro que determina si es un bache o no
    	Double sigma = 4.0;
    	List <Traza> baches = new ArrayList<>();
    	
    	//Creamos el ArrayList de aceleraciones
        ArrayList<Double> aceleracion = new ArrayList<>();
        for (Traza punto: array) {
        	aceleracion.add(punto.getY());
        }
        
    	//Calculamos la media
        double media = aceleracion.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    	//Calculamos la varianza
        double varianza = aceleracion.stream().mapToDouble(a -> Math.pow(a - media, 2)).sum() / aceleracion.size();
        //Calculamos la desviación típica
        double desviacion_típica = Math.sqrt(varianza);
        //Umbralizar por encima o debajo de sigma
        double umbralArriba = media + sigma * desviacion_típica;
        double umbralAbajo = media - sigma * desviacion_típica;
        
        for (Traza punto: array) {
			if (punto.getY() >= umbralArriba || punto.getY() <= umbralAbajo) {
				baches.add(punto);
			}
		}
        
        return baches;
    }
}