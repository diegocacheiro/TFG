package com.usc.rest;

import com.usc.algoritmos.*;
import com.usc.datos.*;
import com.usc.repository.BachesRepository;
import com.usc.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
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
		
		try {
			insertarDatosMagistela();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
 
    public void insertarDatosMagistela() throws UnirestException{
    	Unirest.setTimeouts(0, 0);
    	//Sacamos la lista de trazas existentes
		HttpResponse<String> response = Unirest.get(URLlistaTraza).header("authorization", "bearer " + TOKEN).asString();
		String json = response.getBody();
		
		//Lo convertimos en un objeto y creamos una lista de objetos
		Type listType = new TypeToken<List<Travel>>() {}.getType();
		List<Travel> travels = new Gson().fromJson(json, listType);
		
		Type t = new TypeToken<List<TravelDatos>>() {}.getType();
		
		//Recorremos cada objeto y usamos el id para acceder a la traza
		for (Iterator<Travel> iterator = travels.iterator(); iterator.hasNext();) {
			Travel travel = (Travel) iterator.next();
			
			//accedemos a la traza y guardamos su json
			response = Unirest.get(URLTraza + travel.getId()).header("authorization", "bearer " + TOKEN).asString();
			json = response.getBody();
			
			
			//Accedemos a la traza 31
			if (travel.getId() == 31) {
				List<TravelDatos> travelsCompleto = new Gson().fromJson(json, t);
				//System.out.println(travelsCompleto.get(0).getId());
				     
				List<Acceletarions> acceletarions = travelsCompleto.get(0).getAcceletarions();
				List<Locations> locations = travelsCompleto.get(0).getLocations();
				List<Bumps> bumps = travelsCompleto.get(0).getBumps();
				List <Track_points> tp = new ArrayList<>();
				
				System.out.println("Acceleraciones:" + acceletarions.size());
				System.out.println("Localizaciones:" + locations.size());
				
				//Para cada localización
				for (int i = 0; i < locations.size(); i++) {
					List<Measures> measures = new ArrayList<>();
					//Vemos si localización, esta en la primera localización o está en la última
					if (i == 0) {
						//Recorremos las aceleraciones
						for (int j = 0; j < acceletarions.size(); j++) {
							//Si la aceleracion está correspondida en el rango de la localización y localización + 1 o es menor que la localización, lo agregamos
							if (Long.parseLong(acceletarions.get(j).getTime()) >= Long.parseLong(locations.get(i).getTime()) && 
									Long.parseLong(acceletarions.get(j).getTime()) <= Long.parseLong(locations.get(i+1).getTime()) || 
									Long.parseLong(acceletarions.get(j).getTime()) <= Long.parseLong(locations.get(i).getTime())){
								measures.add(new Measures(acceletarions.get(j).getTime(), acceletarions.get(j).getZ(), 0));
							}
						}
						tp.add(new Track_points(Integer.toString(locations.get(i).getTravelId()), locations.get(i).getId(), new Double[]{locations.get(i).getLatitude(), locations.get(i).getLongitude()}, locations.get(i).getSpeed(), locations.get(i).getAltitude(), locations.get(i).getTime(), locations.get(i+1).getTime(), measures));
					} else if (i < locations.size() - 1 && i != 0 ) {
						//Recorremos las aceleraciones
						for (int j = 0; j < acceletarions.size(); j++) {
							//Si la aceleracion está correspondida en el rango de la localización y localización + 1, lo agregamos
							if (Long.parseLong(acceletarions.get(j).getTime()) >= Long.parseLong(locations.get(i).getTime()) && 
									Long.parseLong(acceletarions.get(j).getTime()) <= Long.parseLong(locations.get(i+1).getTime())) {
								measures.add(new Measures(acceletarions.get(j).getTime(), acceletarions.get(j).getZ(), 0));
							}
						}
						tp.add(new Track_points(Integer.toString(locations.get(i).getTravelId()), locations.get(i).getId(), new Double[]{locations.get(i).getLatitude(), locations.get(i).getLongitude()}, locations.get(i).getSpeed(), locations.get(i).getAltitude(), locations.get(i).getTime(), locations.get(i+1).getTime(), measures));
					} else {
						//Recorremos las aceleraciones
						for (int j = 0; j < acceletarions.size(); j++) {
							//Si la aceleracion está correspondida en el rango de la localización, lo agregamos
							if (Long.parseLong(acceletarions.get(j).getTime()) >= Long.parseLong(locations.get(i).getTime())) {
								measures.add(new Measures(acceletarions.get(j).getTime(), acceletarions.get(j).getZ(), 0));
							}
						}
						tp.add(new Track_points(Integer.toString(locations.get(i).getTravelId()), locations.get(i).getId(), new Double[]{locations.get(i).getLatitude(), locations.get(i).getLongitude()}, locations.get(i).getSpeed(), locations.get(i).getAltitude(), locations.get(i).getTime(), acceletarions.get(acceletarions.size()-1).getTime(), measures));
					}
				}
				//Leemos los baches del array bumps
				for (int i = 0; i < bumps.size(); i++) {
					for (int j = 0; j < tp.size(); j++) {
						if (Long.parseLong(bumps.get(i).getTime()) >= Long.parseLong(tp.get(j).getStart_time()) && Long.parseLong(bumps.get(i).getTime()) < Long.parseLong(tp.get(j).getEnd_time())) {
							Double maxVa = 0.0;
							String time = "";
							for (Measures measure : tp.get(j-1).getMeasures()) {
								if (measure.getVa() > maxVa) {
									maxVa = measure.getVa();
									time = measure.getTime();
								}
							}
							for (Measures measure : tp.get(j-1).getMeasures()) {
								if (measure.getTime() == time) {
									measure.setBm(1);
								}
							}
						}
					}
				}
				for(int j = 0; j < tp.size(); j++) {
					List <Integer> va = new ArrayList<>();
					for (Measures m: tp.get(j).getMeasures()) {
						va.add(m.getBm());
					}
				}
				//Ejecutar el guardado de tp en la base de datos
			}
		}
		
    }
}
