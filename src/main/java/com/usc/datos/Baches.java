package com.usc.datos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Baches")
public class Baches {
	@Id
    @Column(name = "Id")
	private Integer id;
	
	@Id
    @Column(name = "Id_traza")
	private String idTraza;
	
	@Id
    @Column(name = "Algoritmo")
    private String algoritmoNombre;
	
	@Column(name = "Traza")
    private String puntosTraza;
	
	@Column(name = "Resultado_algoritmo")
    private String resultadoAlgoritmo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdTraza() {
		return idTraza;
	}

	public void setIdTraza(String idTraza) {
		this.idTraza = idTraza;
	}

	public String getAlgoritmoNombre() {
		return algoritmoNombre;
	}

	public void setAlgoritmoNombre(String algoritmoNombre) {
		this.algoritmoNombre = algoritmoNombre;
	}

	public String getPuntosTraza() {
		return puntosTraza;
	}

	public void setPuntosTraza(String puntosTraza) {
		this.puntosTraza = puntosTraza;
	}

	public String getResultadoAlgoritmo() {
		return resultadoAlgoritmo;
	}

	public void setResultadoAlgoritmo(String resultadoAlgoritmo) {
		this.resultadoAlgoritmo = resultadoAlgoritmo;
	}
    
	
}
