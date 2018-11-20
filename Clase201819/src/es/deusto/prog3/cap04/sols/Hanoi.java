package es.deusto.prog3.cap04.sols;

public class Hanoi {

	public static void main(String[] args) {
		int tam = 10;
		System.out.println( "Soluci�n de torre de hanoi de tama�o " + tam );
		int numMovs = hanoi( tam, 'a', 'c', 'b' );
		System.out.println( "Han sido necesarios " + numMovs + " movimientos." );
	}
	
	/** Resuelve la torre de hanoi de tama�o dado
	 * Hace la soluci�n de forma recursiva:
	 * Torre de hanoi de tama�o n (origen->destino,aux)=
	 * Rec:  Torre n-1 (origen->aux,destino) +
	 *       mover disco n de origen a destino +
	 *       Torre n-1 (aux->destino,origen)
	 * Base: Si n=1 mover disco 1 de origen a destino
	 * @param nivel	Tama�o de la torre (>0)
	 * @param origen	Nombre de la varilla origen (a,b,c)
	 * @param destino	Nombre de la destino (a,b,c)
	 * @param auxiliar	Nombre de la auxiliar (a,b,c)
	 * @return N�mero de movimientos que hacen falta
	 */
	private static int hanoi( int nivel, 
			char origen, char destino, char auxiliar ) {
		if (nivel>1) {
			int n1 = hanoi( nivel-1, origen, auxiliar, destino );
			System.out.println( "Muevo disco " + nivel + " " + origen + " -> " + destino );
			int n2 = hanoi( nivel-1, auxiliar, destino, origen );
			return n1 + 1 + n2;
		} else {  // n==1 caso base
			System.out.println( "Muevo disco 1 " + origen + " -> " + destino );
			return 1;
		}
	}

}
