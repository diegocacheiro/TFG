package com.usc.rest;

import com.usc.algoritmos.*;
import com.usc.datos.*;
import com.usc.repository.BachesRepository;
import com.usc.service.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

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
	//Acceso a Magistela
	private static final String URLlistaTraza = "https://menuaffinity.es/citius/webapi/api/travels";
	private static final String URLTraza = "https://menuaffinity.es/citius/webapi/api/download/exportdata/";
	private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiam9zdGFiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjpbIlVzZXIiLCJBZG1pbmlzdHJhdG9yIl19.2RlhK-yeeMWETzMLm7_ACsZHRvfY10deMVEUKgzBu3I";

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
		/*
		try {
			insertarDatosMagistela();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return ResponseEntity.ok(repository.AlgoritmosComparacion());
    }
	
	@GetMapping("/algoritmos/ComparacionDatos")
    public ResponseEntity<List <Baches>> obtenerDatosAlgoritmosParaComparar( @RequestParam("id1") Integer id1,  @RequestParam("id2") Integer id2) {
		List <Baches> resultado = new ArrayList<>();
		resultado.add(repository.AlgoritmosDatosComparacion(id1));
		resultado.add(repository.AlgoritmosDatosComparacion(id2));
        return ResponseEntity.ok(resultado);
    }
	
	@GetMapping("/algoritmos/RecuperarDatos")
    public ResponseEntity<Baches> obtenerDatosAlgoritmosRecuperar( @RequestParam("id1") Integer id1) {
        return ResponseEntity.ok(repository.AlgoritmosDatosComparacion(id1));
    }
	
	@GetMapping("/algoritmos")
    public ResponseEntity<List<Algoritmo>> obtenerNombresAlgoritmos() {
        return ResponseEntity.ok(algoritmos.getAllEntities());
    }
    
    //@RequestParam String nombre, @RequestParam HashMap<List<Double>, DatosHash> data
    @PostMapping("/algoritmos/")
    public ResponseEntity<List <Traza>> ejecutarAlgoritmo(@RequestBody DatosAlgoritmo datos, @RequestParam("id") Integer id) {
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
    public ResponseEntity<String> eliminarAlgoritmoBache(@RequestParam("id1") Integer id1) {
    	try {
    		repository.deleteById(id1);
            //repository.EliminarAlgoritmoBache(id1);
            return ResponseEntity.ok("El objeto se eliminó correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar el objeto");
        }
    }
 /*
    public void insertarDatosMagistela() throws UnirestException{
    	int contador = 0;
    	int contadorParcial = 0;
    	int flag = 0;
    	
    	Unirest.setTimeouts(0, 0);
    	//Sacamos la lista de trazas existentes
		HttpResponse<String> response = Unirest.get(URLlistaTraza).header("authorization", "bearer " + TOKEN).asString();
		String json = response.getBody();
		
		//Lo convertimos en un objeto y creamos una lista de objetos
		Type listType = new TypeToken<List<Travel>>() {}.getType();
		List<Travel> travels = new Gson().fromJson(json, listType);
		Type t = new TypeToken<List<TravelDatos>>() {}.getType();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		//Recorremos cada objeto y usamos el id para acceder a la traza
		for (Iterator<Travel> iterator = travels.iterator(); iterator.hasNext();) {
			Travel travel = (Travel) iterator.next();
			
			//accedemos a la traza y guardamos su json
			response = Unirest.get(URLTraza + travel.getId()).header("authorization", "bearer " + TOKEN).asString();
			json = response.getBody();
			
			if (travel.getId() == 31) {
				List<TravelDatos> travelsCompleto = new Gson().fromJson(json, t);
				System.out.println(travelsCompleto.get(0).getId());
				for (int i = 0; i < travelsCompleto.get(0).getLocations().size(); i++) {
					System.out.println("________________________________________");
					contadorParcial += contador;
					contador = 0;
					for (int z = 0; z < travelsCompleto.get(0).getBumps().size(); z++) {
						if (travelsCompleto.get(0).getLocations().get(i).getTime() == travelsCompleto.get(0).getBumps().get(z).getTime()) 
							flag = 1;
					}
					if (i < travelsCompleto.get(0).getLocations().size() - 1) {
						System.out.println("Track: " + travelsCompleto.get(0).getLocations().get(i).getTravelId() + 
								", Point_id: " + travelsCompleto.get(0).getLocations().get(i).getId() +
								", Point_geo: " + travelsCompleto.get(0).getLocations().get(i).getLatitude() + ',' + travelsCompleto.get(0).getLocations().get(i).getLongitude() +
								", Speed: " + travelsCompleto.get(0).getLocations().get(i).getSpeed() +
								", Elevation: " + travelsCompleto.get(0).getLocations().get(i).getAltitude() +
								", start_time: " +  travelsCompleto.get(0).getLocations().get(i).getTime() +
								", end_time: " + travelsCompleto.get(0).getLocations().get(i+1).getTime());
						if (flag == 1) {
							flag = 0;
							System.out.println("time: " + travelsCompleto.get(0).getLocations().get(i).getTime() + ", va: " + travelsCompleto.get(0).getAcceletarions().get(j).getZ() + ", bm: " + 1);
						} else {
							System.out.println("time: " + travelsCompleto.get(0).getLocations().get(i).getTime() + ", va: " + travelsCompleto.get(0).getAcceletarions().get(j).getZ() + ", bm: " + 0);
						}
						for (int j = 0; j < travelsCompleto.get(0).getAcceletarions().size(); j++) {
							if (Long.parseLong(travelsCompleto.get(0).getAcceletarions().get(j).getTime()) >=  Long.parseLong(travelsCompleto.get(0).getLocations().get(i).getTime())
									&& Long.parseLong(travelsCompleto.get(0).getAcceletarions().get(j).getTime()) < Long.parseLong(travelsCompleto.get(0).getLocations().get(i+1).getTime())) {
								if (travelsCompleto.get(0).getLocations().get(i).getId() == 80) {
									System.out.println("time: " + travelsCompleto.get(0).getAcceletarions().get(j).getTime() + ", va: " + travelsCompleto.get(0).getAcceletarions().get(j).getZ() + ", bm: " + 0);
								}
								contador+=1;
							}
						}
						
					} else {
						System.out.println("Track: " + travelsCompleto.get(0).getLocations().get(i).getTravelId() + 
								", Point_id: " + travelsCompleto.get(0).getLocations().get(i).getId() +
								", Point_geo: " + travelsCompleto.get(0).getLocations().get(i).getLatitude() + ',' + travelsCompleto.get(0).getLocations().get(i).getLongitude() +
								", Speed: " + travelsCompleto.get(0).getLocations().get(i).getSpeed() +
								", Elevation: " + travelsCompleto.get(0).getLocations().get(i).getAltitude() +
								", start_time: " +  travelsCompleto.get(0).getLocations().get(i).getTime() +
								", end_time: " + travelsCompleto.get(0).getAcceletarions().get(travelsCompleto.get(0).getAcceletarions().size()-1).getTime());
						for (int j = 0; j < travelsCompleto.get(0).getAcceletarions().size(); j++) {
							if (Long.parseLong(travelsCompleto.get(0).getAcceletarions().get(j).getTime()) >=  Long.parseLong(travelsCompleto.get(0).getLocations().get(i).getTime())) {
								for (int z = 0; z < travelsCompleto.get(0).getBumps().size(); z++) {
									if (travelsCompleto.get(0).getAcceletarions().get(j).getTime() == travelsCompleto.get(0).getBumps().get(z).getTime()) 
										flag = 1;
								}
								if (flag == 1) {
									flag = 0;
									System.out.println("time: " + travelsCompleto.get(0).getLocations().get(i).getTime() + ", va: " + travelsCompleto.get(0).getAcceletarions().get(j).getZ() + ", bm: " + 1);
								} else {
									//System.out.println("time: " + travelsCompleto.get(0).getLocations().get(i).getTime() + ", va: " + travelsCompleto.get(0).getAcceletarions().get(j).getZ() + ", bm: " + 0);
								}
								contador+=1;
							}
						}
					}
				}
				contadorParcial += contador;
				contador = 0;
				System.out.println(contadorParcial);
				System.out.println(travelsCompleto.get(0).getAcceletarions().size());
			}
		}
		
    }*/
}
