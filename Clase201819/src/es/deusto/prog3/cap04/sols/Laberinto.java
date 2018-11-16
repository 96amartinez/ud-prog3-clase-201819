package es.deusto.prog3.cap04.sols;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Soluci�n recursiva a un laberinto
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Laberinto {
	
	// ====================================================
	// Parte static
	// ====================================================
	
	public static void main(String[] args) {
		Laberinto l = new Laberinto();    sacaVentana( l );  // Visualizaci�n
		l.entra();    mostrarLab( l, "Inicio", true );  // Visualizaci�n
		boolean fin = resuelveLaberinto( l );
		sacaMens( "Fin de laberinto " + (fin ? "CON" : "SIN") + " soluci�n" );
	}
	
	// Intenta resolver el laberinto de forma recursiva. Devuelve true si se ha conseguido
	private static boolean resuelveLaberinto( Laberinto l ) {
		if (l.acabado()) return true;
		Direccion dir;
		while ((dir = l.posibleMovimiento()) !=null) {
			l.mueve( dir );         mostrarLab( l, "Mueve " + dir, true ); // Visualizaci�n
			boolean fin = resuelveLaberinto( l );
			if (fin) sacaMens( "Encontrada una salida" );
			// if (fin) return true;  // Truncamos el proceso cuando se acaba
			l.mueve( dir.dirOpuesta() );       mostrarLab( l, "Backtrack " + dir.dirOpuesta(), true ); // Visualizaci�n
		}
		return l.acabado();
	}

	// ====================================================
	// Parte visual
	// ====================================================
	
	private static JFrame ventana;          // Ventana de visualizaci�n del laberinto
	private static JLabel[][] lLab;         // Etiquetas de texto para mostrar el laberinto
	private static JTextArea taMens;        // �rea de texto para mensajes
	private static long msegsPausa = 1000;  // Msg de pausa entre movimientos  (de 0 a 1000)
	private static boolean enPausa = true;  // Informaci�n de pausa
	private static JSlider slTempo;         // Slider de velocidad
	
	// Saca la ventana con el laberinto
	private static void sacaVentana( Laberinto l ) {
		ventana = new JFrame( "Laberinto" );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JPanel p = new JPanel( new GridLayout( l.getAltura(), l.getAnchura() ) );
		lLab = new JLabel[l.getAltura()][l.getAnchura()];
		for (int fila=0; fila<l.getAltura(); fila++) {
			for (int col=0; col<l.getAnchura(); col++) {
				lLab[fila][col] = new JLabel( "   " );
				lLab[fila][col].setFont( new Font( "Courier", Font.PLAIN, 22 ) );
				lLab[fila][col].setOpaque( true );
				p.add( lLab[fila][col] );
				mostrarCasillaLab( l, fila, col );
			}
		}
		ventana.add( p, BorderLayout.CENTER );
		taMens = new JTextArea( 6, 10 ); // Lo importante van a ser las filas porque lo metemos en el sur
		taMens.setFont( new Font( "Courier", Font.PLAIN, 18 ) );
		ventana.add( new JScrollPane(taMens), BorderLayout.SOUTH );
		slTempo = new JSlider( 0, 1000 );
		slTempo.setValue( 1000 - (int) msegsPausa );
		JPanel pSuperior = new JPanel();
		JButton bPausa = new JButton( enPausa ? "Play" : "Pausa" );
		pSuperior.add( new JLabel("Vel:")); pSuperior.add( slTempo ); pSuperior.add( bPausa );
		ventana.add( pSuperior, BorderLayout.NORTH );
		mostrarLab( l, null, false ); // Carga el laberinto en la ventana por primera vez
		ventana.pack();
		ventana.setLocationRelativeTo( null );
		// Eventos
		slTempo.addChangeListener( new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				msegsPausa = 1000 - slTempo.getValue();
			}
		});
		bPausa.addActionListener( (e) -> {
			if (enPausa) {
				bPausa.setText( "Pausa" );
			} else {
				bPausa.setText( "Play" );
			}
			enPausa = !enPausa;
		});
		// Visualizaci�n de ventana
		ventana.setVisible( true );
	}
	
	private static void mostrarCasillaLab( Laberinto l, int fila, int col ) {
		char casilla = l.lab[fila][col];
		JLabel lCasilla = lLab[fila][col];
		if (casilla == 'X' ) {
			lCasilla.setBackground( Color.DARK_GRAY );
		} else if (casilla == 'E' ) {
			lCasilla.setBackground( Color.CYAN );
			lCasilla.setText( " E " );
		} else if (casilla == 'S' ) {
			lCasilla.setBackground( Color.CYAN );
			lCasilla.setText( " S " );
		} else if (casilla == ' ' ) {
			lCasilla.setBackground( Color.WHITE );
			lCasilla.setText( "   " );
		} else if (casilla == '.' ) {
			lCasilla.setBackground( Color.LIGHT_GRAY );
			lCasilla.setText( " . " );
		}
		if (fila==l.filaJugador && col==l.colJugador) {
			lCasilla.setBackground( Color.MAGENTA );
		}
	}
	
	// Muestra el laberinto en pantalla con posible pausa despu�s
	private static void mostrarLab( Laberinto l, String mens, boolean pausa ) {
		sacaMens( mens );
		for (int fila=0; fila<l.getAltura(); fila++) {
			for (int col=0; col<l.getAnchura(); col++) {
				mostrarCasillaLab( l, fila, col );
			}
		}
		if (pausa) pausa();
	}
	
	private static void sacaMens( String mens ) {
		if (mens!=null && !mens.isEmpty()) { 
			taMens.append( "\n" + mens );
			taMens.setSelectionStart( taMens.getText().length() );
		}
	}
	
	// Hace una pausa
	private static void pausa() {
		try { Thread.sleep( msegsPausa ); } catch (InterruptedException e) {}
		while (enPausa) {
			try { Thread.sleep( msegsPausa ); } catch (InterruptedException e) {}
		}
	}
	
	// ====================================================
	// Subclase utilitaria de direcci�n
	// ====================================================
	
	/** Direcci�n de avance en el laberinto
	 * @author andoni.eguiluz @ ingenieria.deusto.es
	 */
	public static enum Direccion { IZQUIERDA, ARRIBA, DERECHA, ABAJO;
		public Direccion dirOpuesta() {
			if (this==Direccion.IZQUIERDA) return Direccion.DERECHA;
			else if (this==Direccion.DERECHA) return Direccion.IZQUIERDA;
			else if (this==Direccion.ARRIBA) return Direccion.ABAJO;
			else return Direccion.ARRIBA;
		}
	}
	
	// ====================================================
	// Parte no static - clase Laberinto
	// ====================================================
	
	private static String[] LAB_POR_DEFECTO = { // Laberinto expresado como un array de strings
		"XXXXXXXXXSXXXSXXXXXX",   // X son las paredes
		"X   X X     X      X",   // S es la salida
		"X X X X XXXXXXXXXX X",   // espacios son los caminos viables
		"X X   X   X   X  X X",
		"X XXXXXX XX X X XX X",
		"X      X    X X  X X",
		"XXXXXX XXXXXX XX X X",
		"X      X   X     X X",
		"X XXXXXX XXXX XXXX X",
		"X      X X         X",
		"XXXXXX X X XXXXXXXXX",
		"X    X X X      X  X",
		"XXXX X X XXXXXX X XX",
		"X  X X X      X X  X",
		"X XX X X XXXX X XX X",
		"X    X X    X X    X",
		"X XXXX XXXX X XXXX X",
		"X           X      X",   // E es la entrada
		"XXXXXXEXXXXXXXXXXXXX"
	};
	
	private char[][] lab;     // Laberinto ('X' para pared, 'E' para entrada, 'S' para salida, ' ' para camino, '.' para marca)
	private int filaJugador;  // Posici�n de fila donde est� el jugador del laberinto (-1 si no ha entrado)
	private int colJugador;   // Posici�n de columna donde est� el jugador del laberinto (-1 si no ha entrado)
	
	/** Inicializa un laberinto partiendo de un modelo dato con un array de strings
	 * @param lab	Laberinto donde cada fila es un string y cada car�cter simboliza el objeto del
	 *              laberinto: "X" para pared, "E" para entrada, "S" para salida, " " para camino.<br/>
	 *              Las filas tienen que tener la misma longitud (anchura constante de laberinto)
	 */
	public Laberinto( String[] lab ) {
		int numCols = lab[0].length();
		this.lab = new char[lab.length][numCols];
		int numFila = 0;
		for (String fila : lab) {
			for (int col=0; col<fila.length(); col++) {
				this.lab[numFila][col] = fila.charAt( col );
			}
			numFila++;
		}
		filaJugador = -1;
		colJugador = -1;
	}
	
	/** Inicializa un laberinto con un valor por defecto de 20 columnas y 19 filas
	 */
	public Laberinto() {
		this( LAB_POR_DEFECTO );
	}
	
	/** Reinicia el laberinto (borra todas las marcas y pone al jugador fuera a�n del laberinto)
	 */
	public void reset() {
		for (int fila=0; fila<lab.length; fila++) {
			for (int col=0;col<lab[0].length; col++) {
				if (lab[fila][col]=='.') lab[fila][col] = ' ';
			}
		}
		filaJugador = -1;
		colJugador = -1;
	}
	
	/** Devuelve la altura del laberinto
	 * @return	N�mero de filas
	 */
	public int getAltura() {
		return lab.length;
	}
	
	/** Devuelve la anchura del laberinto
	 * @return	N�mero de columnas
	 */
	public int getAnchura() {
		return lab[0].length;
	}
	
	/** Mete al jugador en el laberinto (en la casilla de entrada)
	 */
	public void entra() {
		for (int fila=0; fila<lab.length; fila++) {
			for (int col=0;col<lab[0].length; col++) {
				if (lab[fila][col]=='E') {
					filaJugador = fila;
					colJugador = col;
					return;
				}
			}
		}
	}
	
	/** Intenta mover al jugador en la direcci�n indicada dentro del laberinto y marca la nueva posici�n (si procede)
	 * @param avance	Direcci�n de movimiento del jugador
	 * @return	true si se puede mover, false si hay una pared en esa direcci�n (y entonces no hay movimiento)
	 */
	public boolean mueve( Direccion avance ) {
		int filaDestino = calculaFilaDestino(avance);
		int colDestino = calculaColDestino(avance);
		if (filaDestino>=getAltura() || filaDestino<0 || colDestino>=getAnchura() || colDestino<0)
			return false;
		else if (lab[filaDestino][colDestino]=='X')  // Pared
			return false;
		else {  // Movimiento v�lido
			filaJugador = filaDestino;
			colJugador = colDestino;
			if (lab[filaDestino][colDestino] == ' ') // Pone marca
				lab[filaDestino][colDestino] = '.';  
			return true;
		}
	}
	
		// Calcula fila destino seg�n avance
		private int calculaFilaDestino( Direccion avance ) {
			int filaDestino = filaJugador;
			if (avance==Direccion.ARRIBA) filaDestino--;
			else if (avance==Direccion.ABAJO) filaDestino++;
			return filaDestino;
		}
		
		// Calcula columna destino seg�n avance
		private int calculaColDestino( Direccion avance ) {
			int colDestino = colJugador;
			if (avance==Direccion.IZQUIERDA) colDestino--;
			else if (avance==Direccion.DERECHA) colDestino++;
			return colDestino;
		}
	
	
	/** Comprueba si hay pasillo para avanzar en una direcci�n dada
	 * @param avance	Direcci�n de comprobaci�n
	 * @return	true si hay pasillo en esa direcci�n (est� marcado o no), false en caso contrario
	 */
	public boolean hayPasilloEn( Direccion avance ) {
		int filaDestino = calculaFilaDestino(avance);
		int colDestino = calculaColDestino(avance);
		if (filaDestino>=getAltura() || filaDestino<0 || colDestino>=getAnchura() || colDestino<0)
			return false;
		else if (" .S".contains( ""+lab[filaDestino][colDestino]))
			return true;
		else return false;
	}
	
	/** Comprueba si hay marca para avanzar en una direcci�n dada
	 * @param avance	Direcci�n de comprobaci�n
	 * @return	true si hay marca en esa direcci�n, false en caso contrario
	 */
	public boolean hayMarcaEn( Direccion avance ) {
		int filaDestino = calculaFilaDestino(avance);
		int colDestino = calculaColDestino(avance);
		if (filaDestino>=getAltura() || filaDestino<0 || colDestino>=getAnchura() || colDestino<0)
			return false;
		else if (lab[filaDestino][colDestino]=='.')
			return true;
		else return false;
	}
	
	/** Comprueba si hay alg�n movimiento posible a casilla sin marca y devuelve la primer direcci�n disponible.
	 * @return	Direcci�n de movimiento posible o null si no hay ninguna
	 */
	public Direccion posibleMovimiento() {
		for (Direccion d : Direccion.values()) {
			if (hayPasilloEn(d) && !hayMarcaEn(d)) {
				return d;
			}
		}
		return null;
	}
	
	/** Informa si se ha llegado al final del laberinto
	 * @return	true si el jugador ha llegado a la casilla de salida, false en caso contrario
	 */
	public boolean acabado() {
		return (lab[filaJugador][colJugador] == 'S');
	}
	
	/* Devuelve el laberinto en formato de string multil�nea
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ret = "";
		for (int fila=0; fila<lab.length; fila++) {
			for (int col=0;col<lab[0].length; col++) {
				char c = lab[fila][col];
				if (fila==filaJugador && col==colJugador) c = 'O';
				ret += c;
			}
			ret += "\n";
		}
		return ret;
	}

}
