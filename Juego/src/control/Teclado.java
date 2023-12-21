package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];

	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;

	public boolean correr;
	public boolean salir;

	public void actualizar() {
		arriba = teclas[KeyEvent.VK_W];
		abajo = teclas[KeyEvent.VK_S];
		izquierda = teclas[KeyEvent.VK_A];
		derecha = teclas[KeyEvent.VK_D];
		salir = teclas[KeyEvent.VK_ESCAPE];

		correr = teclas[KeyEvent.VK_SHIFT];
	}

	// tecla pulsada// tecla tecleada
	public void keyTyped(KeyEvent e) {

	}

	// tecla pulsada
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;// cuando se pulsa una tecla se ejecuta este
	}

	// tecla liberada
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;// cuando se suelta este otro
	}

}
