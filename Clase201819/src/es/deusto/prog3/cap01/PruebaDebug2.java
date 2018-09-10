package es.deusto.prog3.cap01;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

/** Prueba de Debug
 * Utiliza el depurador (observa en los breakpoints la diferencia entre
 * parar la VM o parar solo el hilo)
 * para corregir este programa en tres cosas:
 * 1) Corregir el nullpointer (por qu� boton da nullpointer si se crea el bot�n?)
 * 2) Que se redimensionen los componentes de la ventana seg�n se cambien de tama�o
 *    (ahora no coge ninguno del mapa, aunque la l�gica es buena... no?)
 * 2b) Por qu� el bot�n y el cuadro de texto a veces no se ven al empezar?
 * 3) Que se redimensionen bien
 *    (no lo hacen, aunque la l�gica es buena... no?)
 */
public class PruebaDebug2 {

	public static void main(String[] args) {
		reajusteLayoutNulo();
		// Al cabo de 10 segundos pulsa el bot�n para forzar el cierre de la ventana
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		boton.doClick();
	}
	
	private static Rectangle tamanyoPanel = null;  // Variable para almacenar el tama�o actual de la ventana (y saberlo cuando cambie)
	private static HashMap<Object,Rectangle> tamComponentes = new HashMap<>();  // Mapa para almacenar el tama�o de los componentes
	private static JFrame miJFrame = null;  // Atributo para la ventana
	private static JButton boton = null;  // Atributo para el bot�n
	
	private static void reajusteLayoutNulo() {
		miJFrame = new JFrame(); miJFrame.setLocation( 0, 0 );
		JPanel panelPrincipal = new JPanel();
		miJFrame.getContentPane().add( panelPrincipal, BorderLayout.CENTER );  // El panel ocupa siempre toda la ventana y se reescala con ella
		miJFrame.setVisible( true );
		miJFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		miJFrame.setTitle ( "Ventana con reajuste por programa de tama�os en layout nulo" );
		miJFrame.setSize( 640, 480 );
		panelPrincipal.setLayout( null );
		// A�adimos un par de componentes de ejemplo para el tema del reajuste
			JLabel rectangulo = new JLabel( "" );
			rectangulo.setBorder( BorderFactory.createLineBorder( Color.red, 2 ));
			rectangulo.setSize( 100, 100 );
			rectangulo.setLocation( 200, 100 );
			panelPrincipal.add( rectangulo );
			JLabel texto = new JLabel( "Redimensiona la ventana y observa los cambios" );
			texto.setBounds( 20, 0, 300, 40 );
			panelPrincipal.add( texto );
			JTextField tfEjemplo = new JTextField( "ejemplo JTextField" );
			tfEjemplo.setBounds( 50, 150, 100, 30 );
			panelPrincipal.add( tfEjemplo );
			JButton boton = new JButton( "Cerrar!" );
			boton.setBounds( 200, 250, 100, 50 );
			panelPrincipal.add( boton );
		// Evento para el bot�n
		boton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				miJFrame.dispose();
			}
		});
		// Eventos para gestionar el reescalado
		// 1.- Guarda los tama�os al activar la ventana
		miJFrame.addWindowListener( new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {  // Al activarse la ventana almacenamos el tama�o del panel
				tamanyoPanel = panelPrincipal.getBounds();
				for (Component c : panelPrincipal.getComponents()) {
					tamComponentes.put( c, c.getBounds() );  // Guardamos el tama�o y posici�n inicial de cada componente para luego escalarlo con �l
				}
			}
		});
		// 2.- Cambia los tama�os al redimensionar la ventana
		panelPrincipal.addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {  // Al redimensionarse el panel, reajustamos sus componentes
				if (panelPrincipal!=null && tamanyoPanel!=null) {
					int escalaX = panelPrincipal.getWidth() / (int) tamanyoPanel.getWidth();   // Nueva escala X
					int escalaY = panelPrincipal.getHeight() / (int) tamanyoPanel.getHeight(); // Nueva escala Y
						// Calcula la escala del tama�o de panel anterior con respecto al actual
					for (Component c : panelPrincipal.getComponents()) { // Reescala cada componente:
						Rectangle tamanyoInicial = tamComponentes.get( c );  // Coge el tama�o que tiene ahora...
						if (tamanyoInicial!=null && c!=null) {
							c.setSize( new Dimension( (int) (tamanyoInicial.getWidth()*escalaX), (int)(tamanyoInicial.getHeight()*escalaY) ) );
								// Multiplica el tama�o por la escala
							c.setLocation( (int) (tamanyoInicial.getX()*escalaX), (int)(tamanyoInicial.getY()*escalaY) );
								// Y cambia la posici�n inicial utilizando la escala
						}
					}
				}
			}
		});
	}

	
}
