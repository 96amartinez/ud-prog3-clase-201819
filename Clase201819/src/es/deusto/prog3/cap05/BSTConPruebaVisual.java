package es.deusto.prog3.cap05;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import es.deusto.prog3.cap06.pr0506resuelta.*;
import es.deusto.prog3.cap06.pr0506resuelta.gui.VentanaBancoDePruebas;
import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Ejemplo de BST con prueba visual de tiempos de ejecuci�n en BST con equilibrio / sin �l
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public class BSTConPruebaVisual {
	
	public static void main(String[] args) {
		// Prueba de eficiencia de �rboles equilibrados y desequilibrados
		String[] pruebas = { "BST equil", "BST desequil" };
		ArrayList<ProcesoProbable> procs = new ArrayList<ProcesoProbable>();
		procs.add( new BSTEquil() );
		procs.add( new BSTDesequil() );
		VentanaBancoDePruebas vent = new VentanaBancoDePruebas();
		vent.setAtributosAVer( "raiz#elemento#izquierdo#derecho" );
		vent.setProcesos( pruebas, procs );
		vent.setVisible( true );
		// Visualizaci�n de �rboles de ejemplo equilibrados y desequilibrados
		BSTEquil bstEq = new BSTEquil();
		bstEq.init( 250 );
		BSTDesequil bstDeseq = new BSTDesequil();
		bstDeseq.init( 250 );
		visualizarArboles( bstEq.bst, bstDeseq.bst ); // Visualiza los �rboles esquem�ticamente en una ventana gr�fica
	}

	
	private static class BSTEquil implements ProcesoProbable {
		private BST<Integer> bst;
		private int tam;		
		@Override
		public void init(int tamanyoTest) {
			tam = tamanyoTest;
			bst = new BST<>();
			initRec( bst, 0, tamanyoTest-1 );  // Inicializa un �rbol equilibradamente - insertando siempre antes el punto medio
		}
			private void initRec( BST<Integer> bst, int desde, int hasta ) {
				if (desde>hasta) {
					// Caso base vac�o
				} else if (desde==hasta) {
					bst.insertar( desde );  // Caso base inserci�n hoja
				} else {
					int medio = (desde+hasta) / 2;
					bst.insertar( medio );
					initRec( bst, desde, medio-1 );
					initRec( bst, medio+1, hasta );
				}
			}

		@SuppressWarnings("unused")
		public int cont;  // Se hace el contador atributo para que la actualizaci�n del contador del test no pueda ser optimizada (y eliminada) por el compilador
		@Override
		public Object test() {
			cont = 0;
			for (int i=0; i<tam; i++) {
				if (bst.contains( i )) cont++;
				else System.out.println( "Error BSTequil: elemento " + i + " no encontrado." );
			}
			return bst;
		}
	}
	
	private static class BSTDesequil implements ProcesoProbable {
		private BST<Integer> bst;
		private int tam;		
		@Override
		public void init(int tamanyoTest) {
			tam = tamanyoTest;
			bst = new BST<>();
			for (int i=0; i<tam; i++) {  // Creaci�n de un BST perfectamente desequilibrado
				bst.insertar( i );
			}
		}
		public int cont;  // Se hace el contador atributo para que la actualizaci�n del contador del test no pueda ser optimizada (y eliminada) por el compilador
		@Override
		public Object test() {
			cont = 0;
			for (int i=0; i<tam; i++) {
				if (bst.contains( i )) cont++;
				else System.out.println( "Error BSTDese: elemento " + i + " no encontrado." );
			}
			return bst;
		}
	}

	/** Visualiza dos �rboles esquem�ticamente en una ventana gr�fica
	 * @param bst1	Arbol de la izquierda
	 * @param bst2	Arbol de la derecha
	 */
	private static void visualizarArboles( final BST<?> bst1, final BST<?> bst2 ) {
		VentanaGrafica v = new VentanaGrafica( 1000, 800, "Visualizaci�n esquem�tica de BSTs" );
		visualizarArboles( v, bst1, bst2 );
		v.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				visualizarArboles( v, bst1, bst2 );
			}
		});
	}
		private static void visualizarArboles( VentanaGrafica v, BST<?> bst1, BST<?> bst2 ) {
			v.borra();
			v.repaint();
			visualizaBST( bst1, v, 0, 0, v.getAnchura()/2, v.getAltura(), bst2.altura() );
			visualizaBST( bst2, v, v.getAnchura()/2, 0, v.getAnchura(), v.getAltura(), bst2.altura() );
			System.out.println( "Diferencia de alturas en BSTs: " + bst1.altura() + " - " + bst2.altura() );
			System.out.println( v.getAnchura() );
		}

	/** Visualiza un �rbol esquem�ticamente en una ventana gr�fica
	 * @param bst	BST a visualizar
	 * @param v	Ventana donde visualizarlo
	 * @param xOrig	X inicial del rect�ngulo de dibujo
	 * @param yOrig	Y inicial del rect�ngulo de dibujo
	 * @param xFin	X final del rect�ngulo de dibujo
	 * @param yFin	Y final del rect�ngulo de dibujo
	 * @param alturaTotal	Altura total que cabe en el rect�ngulo de dibujo
	 */
	private static void visualizaBST( BST<?> bst, VentanaGrafica v, int xOrig, int yOrig, int xFin, int yFin, int alturaTotal ) {
		xAct = xOrig;
		v.setDibujadoInmediato( false );
		double altNivel = (yFin-yOrig) * 1.0 / alturaTotal;   // Altura pixels entre niveles
		double ancNodos = (xFin-xOrig) * 1.0 / bst.size();    // Anchura pixels entre nodos consecutivos en valor
		visualizaBSTRec( bst.raiz, v, xOrig, yOrig, 0, 0, altNivel, ancNodos );
		v.repaint();
	}
		private static double xAct = 0;    // x de dibujado actual (de izquierda a derecha)
		private static double xLast = 0;   // x de dibujado de �ltimo nodo dibujado
		private static void visualizaBSTRec( NodoBST<?> nodo, VentanaGrafica v, int xOrig, int yOrig, int nivel, int sigPosicionSec, double altNivel, double ancNodos ) {
			if (nodo==null) return;
			visualizaBSTRec( nodo.izquierdo, v, xOrig, yOrig, nivel+1, sigPosicionSec, altNivel, ancNodos);
			double miX = xAct;
			double miY = yOrig + nivel*altNivel;
			v.dibujaCirculo( miX, miY, 2.5, 1.5f, Color.GREEN );
			if (nodo.izquierdo!=null) v.dibujaLinea( miX, miY, xLast, yOrig + (nivel+1)*altNivel, 0.75f, Color.BLUE );
			xAct += ancNodos;
			visualizaBSTRec( nodo.derecho, v, xOrig, yOrig, nivel+1, sigPosicionSec, altNivel, ancNodos);
			if (nodo.derecho!=null) v.dibujaLinea( miX, miY, xLast, yOrig + (nivel+1)*altNivel, 0.75f, Color.BLUE );
			xLast = miX;
		}
	
}

