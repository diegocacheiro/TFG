package com.usc.datos;

import java.util.List;

public class Algoritmo {

	private int id;
	private String name;
	private String description;
	private List<String> parametros;
	
	public Algoritmo() {
		super();
	}
	
	public Algoritmo(int id, String name, String description, List<String> parametros) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parametros = parametros;
	}
	
	public List<String> getParametros() {
		return parametros;
	}

	public void setParamertros(List<String> parametros) {
		this.parametros = parametros;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}


}
