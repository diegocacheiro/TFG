package com.usc.datos;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.List;
import java.util.Map;

import javax.persistence.*;


@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "baches")
public class Baches {
	@Id
	private Integer id;
	
    @Column(name = "id_traza", nullable = false)
	private String idTraza;
    
    @Column(name = "algoritmo", nullable = false)
    private String algoritmoNombre; 
	
    @Type(type = "jsonb")
    @Column(name = "traza", columnDefinition = "jsonb")
    private List<Traza> puntosTraza;

    @Type(type = "jsonb")
    @Column(name = "resultado_algoritmo", columnDefinition = "jsonb")
    private List<Traza> resultadoAlgoritmo;
    
    @Type(type = "jsonb")
    @Column(name = "parametros", columnDefinition = "jsonb")
    private Map<String, Object> parametros;
    
    public Baches() {
        super();
    }

	public Baches(Integer id, String idTraza, String algoritmoNombre, List<Traza> puntosTraza,
			List<Traza> resultadoAlgoritmo, Map<String, Object> parametros) {
		super();
		this.id = id;
		this.idTraza = idTraza;
		this.algoritmoNombre = algoritmoNombre;
		this.puntosTraza = puntosTraza;
		this.resultadoAlgoritmo = resultadoAlgoritmo;
		this.parametros = parametros;
	}

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

	public List<Traza> getPuntosTraza() {
		return puntosTraza;
	}

	public void setPuntosTraza(List<Traza> puntosTraza) {
		this.puntosTraza = puntosTraza;
	}

	public List<Traza> getResultadoAlgoritmo() {
		return resultadoAlgoritmo;
	}

	public void setResultadoAlgoritmo(List<Traza> resultadoAlgoritmo) {
		this.resultadoAlgoritmo = resultadoAlgoritmo;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}
    
	
}
