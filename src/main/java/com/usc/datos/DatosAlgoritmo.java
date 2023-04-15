package com.usc.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatosAlgoritmo {
	private List<Traza> trazas;
    private Map<String, Object> parametros;
    
    
	public DatosAlgoritmo() {
		super();
	}
	
	public DatosAlgoritmo(List<Traza> trazas, Map<String, Object> parametros) {
		super();
		this.trazas = trazas != null ? trazas : new ArrayList<>();
		this.parametros = parametros;
	}
	public List<Traza> getTrazas() {
		return trazas;
	}
	public void setTrazas(List<Traza> trazas) {
		this.trazas = trazas;
	}
	public Map<String, Object> getParametros() {
		return parametros;
	}
	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "DatosAlgoritmo [trazas=" + trazas + ", parametros=" + parametros + ", getTrazas()=" + getTrazas()
				+ ", getParametros()=" + getParametros() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
	
    
}
