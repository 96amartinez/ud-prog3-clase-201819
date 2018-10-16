package es.deusto.prog3.cap01.ejerciciosResueltos;

import java.util.regex.Pattern;

/** Programa en esta clase:<br/>
 * 
 * - un m�todo est�tico boolean telefCorrecto( String ) que indique cu�ndo un tel�fono es correcto<br/>
 * - un m�todo est�tico boolean emailCorrecto( String ) que indique cu�ndo un email es correcto<br/>
 * 
 * Puedes usar las variantes que quieras. Al menos podr�as considerar<br/>
 * - Tel�fono v�lido: 
 *   999999999 o 999 999 999 o (99)9999999<br/>
 * - Email v�lido: 
 *   cualquier combinaci�n de letra, d�gitos, punto, _, %, + o -
 *   seguido de una arroba
 *   seguido de un nombre de dominio (letras, d�gitos, puntos o guiones)
 *   seguido de un punto
 *   seguido de una extensi�n de dominio (letras) <br/>
 * 
 * Prueba que los m�todos funcionan utilizando una prueba de unidad.<br/>
 * 
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjercicioPatternYTest {

	// Expresi�n regular de 
	private static final Pattern REGEX_TELEFONO_VALIDO = Pattern.compile(
		"\\d{9}|\\d{3}\\s\\d{3}\\s\\d{3}|\\(\\d{2}\\)\\d{7}" ); // {n} indica un n�mero de repeticiones obligatorio, \s un car�cter de espacio
		
	// Expresi�n regular de email
	public static final Pattern REGEX_EMAIL_VALIDO = Pattern.compile( 
		"[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",  // el {2,6} limita la longitud a 2-6 caracteres
		Pattern.CASE_INSENSITIVE );  // No diferencia may�sculas de min�sculas

	/** Comprueba si un string tiene formato de tel�fono correcto de acuerdo a las posibilidades 
	 *  999999999 o 999 999 999 o (99)9999999
	 * @param string	String a probar
	 * @return	true si cumple el formato, false en caso contrario
	 */
	public static boolean telefCorrecto( String string ) {
		return REGEX_TELEFONO_VALIDO.matcher(string).matches();
	}
	
	/** Comprueba si un email tiene formato de email correcto
	 * @param string	Email a probar
	 * @return	true si es correcto, false en caso contrario
	 */
	public static boolean emailCorrecto( String string ) {
		return REGEX_EMAIL_VALIDO.matcher(string).matches();
	}
	
}
