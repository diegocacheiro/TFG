package com.usc.datos;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.databind.JsonNode;
import com.usc.bd.JsonNodeConverter;


@Entity
@Table(name = "pruebas")
public class Baches {
	@Id
    @Column(name = "id")
	private Integer id;
	
    @Column(name = "id_traza")
	private String idTraza;
	
    @Column(name = "algoritmo")
    private String algoritmoNombre;
	
    @Column(name = "traza", columnDefinition = "jsonb")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode   puntosTraza;
	
    @Column(name = "resultado_algoritmo", columnDefinition = "jsonb")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode   resultadoAlgoritmo;

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

	public JsonNode getPuntosTraza() {
		return puntosTraza;
	}

	public void setPuntosTraza(JsonNode puntosTraza) {
		this.puntosTraza = puntosTraza;
	}

	public JsonNode getResultadoAlgoritmo() {
		return resultadoAlgoritmo;
	}

	public void setResultadoAlgoritmo(JsonNode resultadoAlgoritmo) {
		this.resultadoAlgoritmo = resultadoAlgoritmo;
	}

	
	
}
