package es.deusto.prog3.cap03.cs;

/** Clase de configuraci�n de 
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class ConfigCS {

	// Configuraci�n de conexi�n
	static String HOST = "localhost";  // IP de conexi�n para la comunicaci�n del CLIENTE
	static int PUERTO = 4000;          // Puerto de conexi�n para cliente y servidor
	
	// Diccionario de comunicaci�n - protocolo definido para este ejemplo
	// el servidor devuelve un objeto recibido
	static String RECIBIDO = "recibido";
	// el servidor env�a un objeto recibido de otro cliente
	static String RECIBIDO_DE = "recibido-de";
		// luego env�a qui�n es el cliente que lo env�a (String)
		// y luego env�a el objeto que ese cliente envi� (Object)
	// el cliente acaba mandando objeto fin:
	static String FIN = "fin";  
}
