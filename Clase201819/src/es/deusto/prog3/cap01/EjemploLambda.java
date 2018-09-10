package es.deusto.prog3.cap01;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

/** Ejemplo de introducci�n a lambda con Java 8
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploLambda {
	
	public static void test() { System.out.println( "Soy test de clase" ); }

	@SuppressWarnings("unused")
	private void test2() { System.out.println( "Soy test de instancia" ); }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(
				
			null // Aqu� hay que dar algo que es... un Runnable
			// (�Por qu� es un Runnable?  Porque queremos pasar un c�digo)
			// (Pero pasar un c�digo no se puede... ufff... habr� que hacer un truco... o dos)
			
			// �C�mo dar un runnable a invokeLater?
			
			// Java 7 - clase interna an�nima
//			new Runnable() {
//				@Override
//				public void run() {
//					System.out.println( "Bla bla bla" );
//				}
//			}
				
			// O bien si se resume el c�digo en un m�todo
//				new Runnable() {
//					@Override
//					public void run() {
//						test();
//					}
//				}
					
				
			// Java 8
			// () -> { test(); }
				
			// O tb m�todo est�tico
			// EjemploLambda1::test
				
			// O tb m�todo instancia sobre un objeto
			// (new EjemploLambda())::test2
			
			// Tiene que emparejar con Runnable porque es lo que espera invokeLater:
			//    void run () { }
			// O sea
			//    () sin par�metros -> { } c�digo -> sin retorno (void)
			//
			// Cualquier interfaz con UN SOLO m�todo vale para hacerlo as�.  (definido retorno, definidos pars)
			
		);
		
		JButton b = new JButton("");
		b.addActionListener( 
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						System.out.println( e.getWhen() );
//					}
//				}
			(e) -> { System.out.println( e.getWhen() ); }
			// Tambi�n (java.awt.event.ActionEvent e) -> { System.out.println( e.getWhen() ); }
		);
	}

}
