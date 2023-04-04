package com.usc.datos;

import java.io.Serializable;

public class Traza implements Serializable{
	
	private Integer x;
    private Double y;
    private Double[] coordenadas;
    private Double velocidad;
    private Integer baches;
    
	public Traza(Integer x, Double y, Double[] coordenadas, Double velocidad, Integer baches) {
		super();
		this.x = x;
		this.y = y;
		this.coordenadas = coordenadas;
		this.velocidad = velocidad;
		this.baches = baches;
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double[] getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(Double[] coordenadas) {
		this.coordenadas = coordenadas;
	}
	public Double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}
	public Integer getBaches() {
		return baches;
	}
	public void setBaches(Integer baches) {
		this.baches = baches;
	} 
	
}