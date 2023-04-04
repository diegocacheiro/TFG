package com.usc.rest;

import com.usc.algoritmos.*;
import com.usc.datos.*;
import com.usc.repository.BachesRepository;
import com.usc.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/TFG")
public class BasicRestController{
	
	@Autowired
	private BachesRepository repository;
	@Autowired
	private AlgoritmosServicio algoritmos;
	
	@PostMapping("/algoritmos/Guardar")
	public ResponseEntity<String> saveid(@RequestBody Baches b) {
		Integer id = repository.findMaxId();
		try {
			if (id == null) {
				id = 0;
			} else {
				id ++;
			}
			b.setId(id);
			repository.save(b);
			
			System.out.println("hola");
			return ResponseEntity.ok().body("{\"message\": \"ok\"}");
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping("/")
	public String lcs() {
		return "index";
	}
	
	@GetMapping("/algoritmos/Comparacion")
    public ResponseEntity<List<Object[]>> obtenerAlgoritmosParaComparar() {
        return ResponseEntity.ok(repository.AlgoritmosComparacion());
    }
	
	@GetMapping("/algoritmos")
    public ResponseEntity<List<Algoritmo>> obtenerNombresAlgoritmos() {
        return ResponseEntity.ok(algoritmos.getAllEntities());
    }
    
    //@RequestParam String nombre, @RequestParam HashMap<List<Double>, DatosHash> data
    @PostMapping("/algoritmos/")
    public ResponseEntity<List <Traza>> ejecutarAlgoritmo(@RequestBody List<Traza> array, @RequestParam("id") Integer id) {
    	
    	List <Traza> resultado = new ArrayList<>();
        switch (id) {
            case 1:
                resultado = Algoritmos.Umbralización(array);
                break;
            case 2:
                resultado = Algoritmos.Variabilidad(array);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + id);
        }
        return ResponseEntity.ok(resultado);
    }
}
