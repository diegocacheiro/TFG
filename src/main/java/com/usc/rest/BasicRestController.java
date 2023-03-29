package com.usc.rest;

import com.usc.algoritmos.Algoritmo;
import com.usc.datos.Traza;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/TFG")
public class BasicRestController{
	
	@GetMapping("/")
	public String lcs() {
		return "index";
	}
	
	@GetMapping("/algoritmos")
    public ResponseEntity<String[]> obtenerNombresAlgoritmos() {
    	//Insertar el nombre de la función de algoritmos
    	String[] nombres = {"Umbralización", "Variabilidad"};
        return ResponseEntity.ok(nombres);
    }
    
    //@RequestParam String nombre, @RequestParam HashMap<List<Double>, DatosHash> data
    @PostMapping("/algoritmos/")
    public ResponseEntity<List <Traza>> ejecutarAlgoritmo(@RequestBody List<Traza> array, @RequestParam("nombre") String nombre) {
    	
    	List <Traza> resultado = new ArrayList<>();
    	
        switch (nombre) {
            case "Umbralización":
                resultado = Algoritmo.Umbralización(array);
                break;
            case "Variabilidad":
                resultado = Algoritmo.Variabilidad(array);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + nombre);
        }
        return ResponseEntity.ok(resultado);
    }
}
