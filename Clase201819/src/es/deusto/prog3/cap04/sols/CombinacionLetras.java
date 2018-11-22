package es.deusto.prog3.cap04.sols;

/** Ejercicio: c�lculo combinatorio de letras
 */
public class CombinacionLetras {

	public static void main(String[] args) {
		char[] letras = { 'A', 'F', 'G', 'L' };
		// combinatoriaDe3Iterativa( letras );
		combinatoria( letras, 5 );
	}
	
	/** Visualiza en consola todas las combinaciones
	 * posibles con repetici�n de una serie de letras
	 * @param letras	Letras a combinar
	 * @param num	N�mero de letras a obtener en la combinaci�n
	 */
	public static void combinatoria( char[] letras, int num ) {
		combinatoriaRec( num, "", letras );
	}
	
	// As� ser�a iterativo pero... �c�mo se generaliza?
	private static void combinatoriaDe3Iterativa( char[] letras ) {
		String comb0 = "";
		for (char letra1 : letras) {
			String comb1 = comb0 + letra1;
			for (char letra2 : letras) {
				String comb2 = comb1 + letra2;
				for (char letra3 : letras) {
					String comb3 = comb2 + letra3;
					System.out.println( comb3 );
				}
			}
		}
	}
	
	/** M�todo de combinatoria recursivo
	 * @param num	N�mero de caracteres que quedan por probar para una combinaci�n final
	 * @param comb	Combinaci�n actual
	 * @param letras	Letras a combinar
	 */
	private static void combinatoriaRec( int num, String comb, char[] letras ) {
		if (num==0) {
			System.out.println( comb );
		} else {
			for (char miLetra : letras) {
				if (contLetras( miLetra, comb )<2) {  // Ejemplo, si se quiere poner el l�mite de 2 letras repetidas m�ximo  (si se quita el if, salen todas las combinaciones)
					String miComb = comb + miLetra;
					combinatoriaRec( num-1, miComb, letras );
				}
			}
		}
	}
	
		private static int contLetras( char letra, String frase ) {
			int cont = 0;
			for (int i=0; i<frase.length(); i++) {
				char l = frase.charAt(i);
				if (l==letra) cont++;
			}
			return cont;
		}

}
