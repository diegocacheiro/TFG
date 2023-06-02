package com.usc.rest;

import com.usc.algoritmos.*;
import com.usc.datos.*;
import com.usc.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private BachesService bachesService;
	/*
	@Autowired
	private  TrackPointService trackPointService; */
	@Autowired
	private AlgoritmosServicio algoritmos;
	
	@PostMapping("/algoritmos/Guardar")
	public ResponseEntity<String> saveid(@RequestBody Baches b) {
		Integer id = bachesService.findMaxId();
		try {
			if (id == null) {
				id = 0;
			} else {
				id ++;
			}
			b.setId(id);
			bachesService.save(b);
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
        return ResponseEntity.ok(bachesService.AlgoritmosComparacion());
    }
	
	@GetMapping("/algoritmos/ComparacionDatos")
    public ResponseEntity<List <Baches>> obtenerDatosAlgoritmosParaComparar( @RequestParam Integer id1,  @RequestParam Integer id2) {
		List <Baches> resultado = new ArrayList<>();
		resultado.add(bachesService.AlgoritmosDatosComparacion(id1));
		resultado.add(bachesService.AlgoritmosDatosComparacion(id2));
        return ResponseEntity.ok(resultado);
    }
	
	@GetMapping("/algoritmos/RecuperarDatos")
    public ResponseEntity<Baches> obtenerDatosAlgoritmosRecuperar( @RequestParam Integer id1) {
        return ResponseEntity.ok(bachesService.AlgoritmosDatosComparacion(id1));
    }
	
	@GetMapping("/algoritmos")
    public ResponseEntity<List<Algoritmo>> obtenerNombresAlgoritmos() {
        return ResponseEntity.ok(algoritmos.getAllEntities());
    }
    
    //@RequestParam String nombre, @RequestParam HashMap<List<Double>, DatosHash> data
    @PostMapping("/algoritmos/")
    public ResponseEntity<List <Traza>> ejecutarAlgoritmo(@RequestBody DatosAlgoritmo datos, @RequestParam Integer id) {
    	List<Traza> array = datos.getTrazas();
        Map<String, Object> parametros = datos.getParametros();
    	List <Traza> resultado = new ArrayList<>();
        switch (id) {
            case 1:
                resultado = Algoritmos.Umbralización(array, parametros);
                break;
            case 2:
                resultado = Algoritmos.Variabilidad(array, parametros);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo desconocido: " + id);
        }
        return ResponseEntity.ok(resultado);
    }
    
    @GetMapping("/algoritmos/EliminarDatos")
    public ResponseEntity<String> eliminarAlgoritmoBache(@RequestParam Integer id1) {
    	try {
    		bachesService.deleteById(id1);
            return ResponseEntity.ok("El objeto se eliminó correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar el objeto");
        }
    }
/*
    @GetMapping("/trackPoints")
    public ResponseEntity<List<Object[]>> DatosTrack_points() {
    	return ResponseEntity.ok(trackPointService.findProperties());
    }
 */
    
}
