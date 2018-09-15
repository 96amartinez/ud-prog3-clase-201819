package es.deusto.prog3.cap00.ejercicios.edicionSprites;

/** Ejercicio de creaci�n de ventanas complejas
 * En este mismo paquete ver�s un fichero 
 *   <a href="file:./ej-sprites-1.jpg">ej-sprites-1.jpg</a>
 * con una propuesta de ventana. Dise�a con las variantes que veas oportuna esa ventana (en principio, sin programarla).<br/>
 * Hay otro fichero con marcas:
 *   <a href="file:./ej-sprites-2.jpg">ej-sprites-2.jpg</a>
 * sobre el que puedes observar los comentarios de cada parte de la ventana. Lo que se pretende es:<br/>
 *  - Objetivo de la ventana: editar y visualizar animaciones de sprites partiendo de gr�ficos ya existentes<br/>
 *  - Funcionamiento: al pulsar el bot�n (2) aparecer� una selecci�n de ficheros (JFileChooser) que permitir�
 *    seleccionar una CARPETA cualquiera del disco <br/>
 *  - Los ficheros png/jpg/gif [recomendado pngs transparentes] que est�n en esa carpeta aparecer�n en (1) <br/>
 *  - (1) es un JList con desplazamiento (dentro de un JScrollPane) donde aparecer�n los nombres de ficheros existentes en la carpeta <br/>
 *  - Entre el bot�n (2) y la lista (1) aparece el nombre de la carpeta seleccionada (JLabel)<br/>
 *  - Al cambiar la carpeta se crea una selecci�n nueva vac�a (lista (5)) <br/>
 *  - Al hacer doble click en un fichero de (1) se a�ade al final de la lista (5) <br/>
 *  - Al seleccionar un fichero en (5) se ve en el panel de preview (3), centrado con respecto al panel <br/>
 *  - Los componentes que hay a la derecha de la lista (5) tienen los siguientes significados: <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de nivel de zoom, desde 10% hasta 200% <br/>
 *  &nbsp;&nbsp;- Un deslizador (JSlider) de rotaci�n, desde 0� hasta 360� <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento a derecha de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Un JTextField de desplazamiento abajo de pixels (0 por defecto) <br/>
 *  &nbsp;&nbsp;- Una indicaci�n de milisegundos de cada imagen dentro de la secuencia del sprite (100 por defecto) <br/>
 *  &nbsp;&nbsp;Estos valores indicados configuran cada imagen dentro de la secuencia (y por tanto deben almacenarse y restaurarse por cada imagen de la secuencia). Lo dem�s es para toda la secuencia:<br/>
 *  &nbsp;&nbsp;- Checkbox de ciclo (al visualizar la secuencia, tras la �ltima imagen vuelve a empezar la primera) <br/>
 *  &nbsp;&nbsp;- Botones de arriba/abajo que suben o bajan una posici�n la imagen seleccionada con respecto al resto de la lista (5) <br/>
 *  &nbsp;&nbsp;- Debajo de (5) dos JTextField de ancho y alto en p�xels de toda la secuencia <br/>
 *  &nbsp;&nbsp;- (6) Botones de nuevo/save/load para reiniciar, guardar o cargar una secuencia configurada (en save y load aparecer� un cuadro de di�logo que pide el nombre y localizaci�n) <br/>
 *  - (7) es un panel donde se define la animaci�n de movimiento del sprite en la "arena" (4). Su contenido es: <br/>
 *  &nbsp;&nbsp;- P�xels de origen x e y de la animaci�n dentro de la arena<br/>
 *  &nbsp;&nbsp;- Velocidad inicial de la animaci�n en p�xels por segundo, con un cuadro de texto y un JSlider entre 0 y 200<br/>
 *  &nbsp;&nbsp;- �ngulo inicial de la animaci�n en grados, con un cuadro de texto y un JSlider entre 0 y 90<br/>
 *  &nbsp;&nbsp;- Gravedad de la animaci�n, con un JSlider entre 0.0 y 10.0  (9.8 por defecto)<br/>
 *  - (8) es un panel donde se complementa la animaci�n de movimiento del sprite. Contenido: <br/>
 *  &nbsp;&nbsp;- Rotaci�n de la animaci�n en grados por segundo, con un cuadro de texto y un JSlider entre 0 y 360 (0 por defecto)<br/>
 *  &nbsp;&nbsp;- Zoom de la animaci�n en % por segundo, con un cuadro de texto y un JSlider entre 50 y 200 (100 por defecto)<br/>
 *  &nbsp;&nbsp;- Checkbox de si la animaci�n se hace c�clica (infinita) <br/>
 *  &nbsp;&nbsp;- Checkbox de si la animaci�n se hace con retorno (va y vuelve) <br/>
 *  - (9) son los tres botones de animaci�n:<br/>
 *  &nbsp;&nbsp;- Anima solo la secuencia sin mover el sprite en la arena<br/>
 *  &nbsp;&nbsp;- Hace el movimiento sin animar la secuencia (toma el sprite seleccionado actualmente)<br/>
 *  &nbsp;&nbsp;- Realiza a la vez las dos animaciones, la de secuencia y la de movimiento<br/>
 *  Otras anotaciones:<br/>
 *  - Valora los layouts m�s adecuados para cada panel. Intenta utilizar los m�s sencillos.<br/>
 *  - Los cuadros de texto que van con sliders asociados deben alimentarse mutuamente (si se cambia el slider cambia el texto y viceversa). Intenta hacerlo de una forma met�dica en lugar de repitiendo c�digo.<br/>
 *  - Haz una ventana interna (JInternalFrame) en lugar de un JFrame normal y as� podr�a integrarse con otras ventanas de la misma aplicaci�n<br/>
 *  - La Arena deber�a ocupar el m�ximo espacio posible de la ventana. El resto de los paneles el m�nimo necesario.<br/>
 *  - En la zona (9) se podr�a a�adir una JProgressBar que vaya mostrando la progresi�n de la animaci�n en curso<br/>
 *  - A�ade tooltips a las partes que consideres interesante<br/>
 *  - Si quieres probar los SplitPane puedes hacer uno entre (3) y (4)<br/>
 *  
 * <br/>
 * Programaci�n posterior:<br/>
 *  Hay que definir modelos de datos para las listas (�de qu� tipo cada JList y cada modelo?)<br/>
 *  Hilos para los plays (�cu�ntos? �cu�ndo?)<br/>
 *  Hay que usar alguna estructura (ArrayList por ejemplo) para guardar toda la configuraci�n de la secuencia (�varios arraylists o un arraylist de una clase nueva?)<br/>
 */
public class VentanaEdicionSprites {

}
