package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import juego.Juego;

public class Escena_principal extends JFrame {

	private JButton botonJugar;
	private JButton botonOpciones;
	private JButton botonSalir;

	public Escena_principal() {
		super("Underhive"); // Título de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Dimensiones de la ventana (ancho x alto)
		setPreferredSize(new Dimension(800, 600));

		// Configurar panel para organizar los botones y la imagen de fondo
		JPanel panelContenedor = new JPanel(new BorderLayout());

		// Cargar la imagen de fondo
		ImageIcon imagenFondo = new ImageIcon("recursos/mapa/descarga.jpeg"); // Cambia la ruta a tu imagen
		JLabel labelFondo = new JLabel(imagenFondo);

		panelContenedor.add(labelFondo, BorderLayout.CENTER);

		// Crear botones
		botonJugar = new JButton("Jugar");
		botonOpciones = new JButton("Opciones");
		botonSalir = new JButton("Salir");

		// Configurar panel para organizar los botones
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.BLUE); // Cambiar color de fondo
		panelBotones.add(botonJugar);
		panelBotones.add(botonOpciones);
		panelBotones.add(botonSalir);
		// Agregar el panel de botones a la ventana

		panelContenedor.add(panelBotones, BorderLayout.NORTH);

		jugar();
		salir();
		opciones();

		add(panelContenedor);

		// Configuraciones adicionales
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void jugar() {
		botonJugar.addActionListener(e -> {
			// Notificar al juego que se ha hecho clic en "Jugar"
			Juego.iniciarJuego();
			dispose();
		});
	}

	public void salir() {

		botonSalir.addActionListener(e -> {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Underhive", JOptionPane.YES_NO_OPTION);
			if (opcion == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});
	}

	public void opciones() {
		botonOpciones.addActionListener(e -> {
			// Crear un cuadro de diálogo de opciones
			Ventana_Opciones opcionesDialog = new Ventana_Opciones(this);
			opcionesDialog.setVisible(true);
		});
	}

	public JButton getBotonJugar() {
		return botonJugar;
	}

	public JButton getBotonOpciones() {
		return botonOpciones;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}
}