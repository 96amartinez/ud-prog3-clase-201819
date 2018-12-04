package es.deusto.prog3.cap05.sols;

import java.util.*;

import es.deusto.prog3.utils.IUConsola;

public class GrafoCarreteras {

	/* Versi�n 1 - correcta pero mala para buscar carrs
	private HashSet<String> ciudades;  // V�rtices como un set
	private ArrayList<Carretera> carreteras;  // Aristas como una lista  */
	
	// Versi�n 2 
	private HashMap<String,ArrayList<Carretera>> carreteras;  // Grafo como un mapa de adyacencias
	// Har�a falta un set adicional si el v�rtice tuviera informaci�n (ahora solo es un array)
	
	public ArrayList<Carretera> getCarrs( String ciudad ) {
		return carreteras.get( ciudad );
	}
	
	public GrafoCarreteras() {
		carreteras = new HashMap<>();
	}

	/** A�ade una carretera al grafo. La carretera NO debe estar antes
	 * @param ciudad1
	 * @param ciudad2
	 * @param distancia
	 */
	public void anyadirCarretera( String ciudad1, String ciudad2, int distancia ) {
		if (!carreteras.containsKey( ciudad1 ))
			carreteras.put( ciudad1, new ArrayList<>()); // Nuevo v�rtice con adyacencias vac�as
		carreteras.get( ciudad1 ).add( new Carretera( ciudad1, ciudad2, distancia )); // A�adir carretera (adyacencia)
		// Grafo no dirigido - las carreteras van igual en las dos direcciones:
		if (!carreteras.containsKey( ciudad2 ))
			carreteras.put( ciudad2, new ArrayList<>()); // Nuevo v�rtice con adyacencias vac�as
		carreteras.get( ciudad2 ).add( new Carretera( ciudad2, ciudad1, distancia )); // A�adir carretera (adyacencia)
	}
	
	/** Calcula la distancia m�nima entre dos ciudades
	 * @param ciudad1	Ciudad origen
	 * @param ciudad2	Ciudad destino
	 * @return	Distancia m�nima (si no hay camino, Integer.MAX_VALUE
	 */
	public int getDistanciaMinima( String ciudad1, String ciudad2 ) {
		return getDMRec( ciudad1, ciudad2, ciudad1, 0 );
	}
	
		public int getDMRec( String c1, String c2, String camino, int distAcum ) {
			System.out.println( "<" + camino + "> dist " + distAcum );
			if (c1.equals(c2)) {
				System.out.println( " Fin! " + camino + " - distancia " + distAcum );
				return 0;
			} else {
				int menor = Integer.MAX_VALUE;
				for (Carretera c : carreteras.get(c1)) {  // Probamos todas las opciones de caminos...
					if (!camino.contains(c.ciudad2)) {  // ...excepto las que hacen bucles (OJO! Sin esta condici�n ser�a infinito)
						int dist = getDMRec( c.ciudad2, c2, camino + "#" + c.ciudad2, distAcum+c.distancia );
						if (dist<Integer.MAX_VALUE) dist = dist + c.distancia;  // Hay camino a c2 y la distancia es dist
						if (dist<menor) menor = dist;  // Hay camino a c2 y la distancia es la menor hasta ahora
					}
				}
				return menor;
			}
		}
	
	public class Carretera {
		private String ciudad1; // Redundante si usamos un mapa (ciudad de origen)
		private String ciudad2;
		private int distancia; // en km
		public Carretera(String ciudad1, String ciudad2, int distancia) {
			super();
			this.ciudad1 = ciudad1;
			this.ciudad2 = ciudad2;
			this.distancia = distancia;
		}
		public String getCiudad1() {
			return ciudad1;
		}
		public void setCiudad1(String ciudad1) {
			this.ciudad1 = ciudad1;
		}
		public String getCiudad2() {
			return ciudad2;
		}
		public void setCiudad2(String ciudad2) {
			this.ciudad2 = ciudad2;
		}
		public int getDistancia() {
			return distancia;
		}
		public void setDistancia(int distancia) {
			this.distancia = distancia;
		}
	}
	
	public static void main(String[] args) {
		IUConsola.lanzarConsolaEnIU( null );
		GrafoCarreteras grafoEjemplo = new GrafoCarreteras();
		grafoEjemplo.anyadirCarretera( "Bilbao", "Vitoria", 61 );
		grafoEjemplo.anyadirCarretera( "Bilbao", "Donostia", 119  );
		grafoEjemplo.anyadirCarretera( "Bilbao", "Santander", 108  );
		grafoEjemplo.anyadirCarretera( "Vitoria", "Donostia", 101 );
		grafoEjemplo.anyadirCarretera( "Donostia", "Pamplona", 82 );
		grafoEjemplo.anyadirCarretera( "Pamplona", "Vitoria", 95 );
		grafoEjemplo.anyadirCarretera( "Pamplona", "Logro�o", 80 );
		grafoEjemplo.anyadirCarretera( "Vitoria", "Burgos", 114 );
		grafoEjemplo.anyadirCarretera( "Burgos", "Palencia", 93 );
		grafoEjemplo.anyadirCarretera( "Burgos", "Valladolid", 117 );
		grafoEjemplo.anyadirCarretera( "Burgos", "Soria", 141 );
		grafoEjemplo.anyadirCarretera( "Logro�o", "Soria", 100 );
		grafoEjemplo.anyadirCarretera( "Logro�o", "Zaragoza", 172 );
		grafoEjemplo.anyadirCarretera( "Zaragoza", "Huesca", 72 );
		grafoEjemplo.anyadirCarretera( "Las Palmas", "Maspalomas", 59 );  // Subgrafo
		System.out.println( "Camino entre Bilbao y Pamplona?" );
		System.out.println( 
			grafoEjemplo.getDistanciaMinima( "Bilbao", "Pamplona" ));
		System.out.println();
		System.out.println( "Camino entre Las Palmas y Bilbao?" );
		System.out.println( 
			grafoEjemplo.getDistanciaMinima( "Las Palmas", "Bilbao" ));
	}

}
