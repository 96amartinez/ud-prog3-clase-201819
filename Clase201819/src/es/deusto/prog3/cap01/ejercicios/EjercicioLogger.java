package es.deusto.prog3.cap01.ejercicios;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/** Ejercicio de logger: sacar a fichero el registro de lo que hace el programa, al menos estas cosas:
 * - Cuando se lanza la ventana
 * - Cuando se pulsa el bot�n
 * - Cuando se edita el textfield
 * - Cada carpeta que se visualiza el n�mero de ficheros
 * - Observa que hay un posible error si la carpeta no existe. Atrapa esta excepci�n y a��dela al log indicando un mensaje al usuario
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjercicioLogger {
	
	// TODO A�adir logger y registrar lo indicado en la cabecera (y m�s si se quiere)
	
	private static JTextField tfEntrada = new JTextField( 60 );
	private static JLabel lMensaje = new JLabel( " " );
	public static void main(String[] args) {
		// Ventana de ejemplo para el ejercicio
		final JFrame f = new JFrame( "Ventana r�pida para ejercicio logger" );  // Ventana a visualizar
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		f.setSize( 800, 150 );
		f.setLocationRelativeTo( null );
		JPanel pSuperior = new JPanel();
		JPanel pInferior = new JPanel();
		JButton bBuscar = new JButton( "Buscar" );
		pSuperior.add( new JLabel("Indica carpeta") ); 
		pSuperior.add( tfEntrada );
		pSuperior.add( bBuscar );
		pInferior.add( lMensaje );
		f.add( pSuperior, BorderLayout.CENTER );
		f.add( pInferior, BorderLayout.SOUTH );
		tfEntrada.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sacaFicheros();
			}
		});
		bBuscar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Saca un di�logo de b�squeda de fichero con JFileChooser
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
				int cod = f.showOpenDialog( f );
				if (cod==JFileChooser.APPROVE_OPTION) {
					File dir = f.getSelectedFile();
					tfEntrada.setText( dir.getAbsolutePath() );
					sacaFicheros();
				}
			}
		});
		f.setVisible( true );
	}
	
	// Saca el n�mero de ficheros de la carpeta indicada
	private static  void sacaFicheros() {
		File f = new File( tfEntrada.getText() );
		File[] listDir = f.listFiles();
		lMensaje.setText( "Ficheros+directorios en la carpeta: " + listDir.length );
	}

}
