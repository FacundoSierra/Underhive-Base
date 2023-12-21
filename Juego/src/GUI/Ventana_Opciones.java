package GUI;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Opciones extends JDialog {

	private JButton botonVolumen;
	private JButton botonMostrarFPS;
	private JButton botonVolver;

	public Ventana_Opciones(JFrame parent) {
		super(parent, "Opciones", true); // El tercer parámetro indica que el cuadro de diálogo es modal
		setLocationRelativeTo(parent);
		setPreferredSize(new Dimension(800, 600));

		JPanel panelOpciones = new JPanel();
		botonVolumen = new JButton("Ajustar Volumen");
		botonMostrarFPS = new JButton("Mostrar FPS");
		botonVolver = new JButton("Volver a Principal");

		panelOpciones.add(new JLabel("Configuración de Opciones:"));
		panelOpciones.add(botonVolumen);
		panelOpciones.add(botonMostrarFPS);
		panelOpciones.add(botonVolver);

		volver();

		add(panelOpciones);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void volver() {
		botonVolver.addActionListener(e -> {

			dispose();

		});
	}
}
