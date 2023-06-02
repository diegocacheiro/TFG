package com.usc.algoritmos;

import java.util.*;
import com.usc.datos.Traza;

public interface AlgoritmoInterfaz {
	List<Traza> ejecutar(List<Traza> array, Map<String, Object> parametros);
}
