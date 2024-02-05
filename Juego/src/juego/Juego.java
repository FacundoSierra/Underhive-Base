package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import control.Teclado;
import entes.Spam;
import entes.criatura.Jugador;
import entes.criatura.NPC;
import graficos.Pantalla;
import graficos.Sprite;
import gui.Scene1;
import mapa.Mapa;
import mapa.MapaCargado;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 800;
	private static final int ALTO = 600;

	private static volatile boolean enFuncionamiento = false;// el volatile es para que la variable no pueda utilizarse
																// de forma simultanea por los dos threads.

	private static final String NOMBRE = "Underhive";

	private static String CONTADOR_FPS = "";
	private static String CONTADOR_APS = "";
	private static String TIEMPO = "";
	private static String TIEMPO2 = "";
//------------------------------------------------
	private static int aps = 0;
	private static int fps = 0;

	private static int x = 0;
	private static int y = 0;

	private long crono0;
	public long tiempo;
	public long tambor = 0;
//-------------------------------------------------------
	private static boolean juegoIniciado = false;
	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Pantalla pantalla;
	private static Mapa mapa;
	private static Jugador jugador;
	private static NPC enemigo;
	private static Spam spam;

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();// devuelve una array
																									// de ints que
																									// representa a los
																									// pixeles de la
	// imagen

	private Juego() {

		setPreferredSize(new Dimension(ANCHO, ALTO));
//------------------Pantalla_---------------------
		pantalla = new Pantalla(ANCHO, ALTO);

//---------------controles----------------
		teclado = new Teclado();
		addKeyListener(teclado);// para que detecte todo lo que se esta pulsando en el canvas

		// mapa = new MapaGenerado(128, 128);
		mapa = new MapaCargado("recursos/mapa/MAPA900.png");
		jugador = new Jugador(mapa, teclado, Sprite.KINARRIBA0, 14000, 14000);
		enemigo = new NPC(mapa, Sprite.KINROJOARRIBA0, jugador, 14250, 14250);
//--------------Mapa --------------------------------

		// ---------------Ventana------------------

		ventana = new JFrame(NOMBRE);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Para cerrar ventana
		ventana.setResizable(false);// no podran cambiar el tamaño de la ventana
		ventana.setLayout(new BorderLayout());// Diseño para la ventana (organizacion interna)
		ventana.add(this, BorderLayout.CENTER);// No hay hueco entre canvas y el borde de la ventana; el usuario no
		// ventana.setUndecorated(true); // sabra que tenemos un canvas dentro de una

		ventana.pack();// para que el contenido de la ventana se ajuste al tamaño que queremos
		ventana.setLocationRelativeTo(null);// para cuando se vea en el escritorio
		ventana.setVisible(true);// La ventana sera visible cuando ejecutemos el programa

	}

	public static void iniciarJuego() {
		if (!juegoIniciado) {
			juegoIniciado = true;
			Juego juego = new Juego();
			juego.iniciar();
			// Puedes agregar aquí cualquier otra lógica relacionada con el inicio del juego
		}
	}
//-----------------Main----------------------------------------

	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			new Escena_principal();
//		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scene1 frame = new Scene1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}// final del main

	private synchronized void iniciar() {
		this.crono0 = System.nanoTime();
		enFuncionamiento = true;

		thread = new Thread(this, "Graficos");
		thread.start();// para que funcione el thread
	}

// el synchrinized hace lo mismo que el volatile
	private synchronized void detener() {
		enFuncionamiento = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() {
		teclado.actualizar();
		jugador.actualizar();
		enemigo.actualizar();
//		spam.actualizar();

		if (teclado.salir) {
			System.exit(0);
		}
		aps++;
	}

	private void mostrar() {
		BufferStrategy estrategia = getBufferStrategy();// el buffer es para que la imagen llegue ya hecha a la pantalla

		if (estrategia == null) {
			createBufferStrategy(3);// 3 = triple buffer
			return;
		}
		pantalla.limpiar();

		mapa.mostrar(jugador.obtenerPosicionX() - pantalla.obtenAncho() / 2 + jugador.obtenSprite().obtenLado() / 2,
				jugador.obtenerPosicionY() - pantalla.obtenAlto() / 2 + jugador.obtenSprite().obtenLado() / 2,
				pantalla);

		jugador.mostrar(pantalla);
		enemigo.mostrar(pantalla);

		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);// este es el mismo que hacer un for pero es
																			// menos costoso de leer para el ordenador
//		for (int i = 0; i < pixeles.length; i++) {
//			pixeles[i] = pantalla.pixeles[i];
//
//		}

		Graphics g = estrategia.getDrawGraphics();

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.blue);
//		g.fillRect(ANCHO / 2, ALTO / 2, 32, 32);
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);
		g.drawString("X : " + jugador.obtenerPosicionX(), 10, 70);
		g.drawString("Y : " + jugador.obtenerPosicionY(), 10, 55);
		// g.drawString(TIEMPO, 10, 90);
		// g.drawString(TIEMPO2, 10, 110);
		g.dispose();// para que no se cuelgue el ordenador
		estrategia.show();

		fps++;
	}
//-----------------------thread run-----------------------

	public void run() {

		final int NS_POR_SEGUNDO = 1000000000;// cauntos nano hay en un segundo
		final byte APS_OBJETIVO = 60;// Cuantas actualizaciones por segundo queremos llevar
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaAtualizacion = System.nanoTime();// se le retribuira una cantidad de tiempo en nano segundos
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;// cantidad de tiempo hasta que se realiza una actualizacion

		requestFocus();// comando para uqe el usuario pueda usar las teclas automaticamente y no tenga
						// que clicar en la ventana

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();// inicar cronometro

			tiempoTranscurrido = inicioBucle - referenciaAtualizacion;// cuanto ha pasado desde el inicio y la
																		// referencia
			referenciaAtualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) { // se ejecutara solo si delta es mayor o igual a 1
				actualizar();
				delta--;
			}
//-----------------------------------------------------------------------------
			tiempo = (System.nanoTime() - crono0) / 10000000;
			mostrar();
			TIEMPO = "TIEMPO: " + tiempo;
			TIEMPO2 = "TIEMPO EN SEG: " + tiempo / 100;
			if ((tiempo - tambor) > 100) {
				tambor = tiempo;

			}

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;

				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}

	}// final del run

	public long getTiempo() {
		return tiempo;

	}

	public static Mapa obtenrMapa() {
		return mapa;
	}
}// final del Canvas
