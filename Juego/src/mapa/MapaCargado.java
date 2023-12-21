package mapa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mapa.cuadro.Cuadro;

public class MapaCargado extends Mapa {

	private int[] pixeles;

	public MapaCargado(String ruta) {
		super(ruta);
	}

	protected void cargarMapa(String ruta) {
		try {
			File file = new File(ruta); // Se agregan estos metodos de File
			// FileInputStream imagen = new FileInputStream(file);
			BufferedImage imagen = ImageIO.read(file);

			ancho = imagen.getWidth();
			alto = imagen.getHeight();

			cuadrosCatalogo = new Cuadro[ancho * alto];
			pixeles = new int[ancho * alto];

			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void generarMapa() {
		for (int i = 0; i < pixeles.length; i++) {
			switch (pixeles[i]) {
			case 0xffec7f06: // Aqui hay que poner el color asignado al sprite
				cuadrosCatalogo[i] = Cuadro.ESQUINA_DA;
				continue;// Para qeu se ejecute uno a uno
			case 0xffcd7e27:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_IA;
				continue;
			case 0xff844a09:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_DB;
				continue;
			case 0xff552f05:
				cuadrosCatalogo[i] = Cuadro.ESQUINA_IB;
				continue;
			case 0xfff3d7b9:
				cuadrosCatalogo[i] = Cuadro.PARED_A;
				continue;
			case 0xff82705d:
				cuadrosCatalogo[i] = Cuadro.PARED_B;
				continue;
			case 0xffd57205:
				cuadrosCatalogo[i] = Cuadro.PARED_D;
				continue;
			case 0xffc16a09:
				cuadrosCatalogo[i] = Cuadro.PARED_I;
				continue;
			case 0xffeba251:
				cuadrosCatalogo[i] = Cuadro.ARENA;
				continue;
			case 0xff97802f:
				cuadrosCatalogo[i] = Cuadro.PLANTA;
				continue;
			case 0xffab3589: // Aqui hay que poner el color asignado al sprite
				cuadrosCatalogo[i] = Cuadro.MARESQUINA_DA;
				continue;// Para qeu se ejecute uno a uno
			case 0xff67c1c3:
				cuadrosCatalogo[i] = Cuadro.MARESQUINA_IA;
				continue;
			case 0xff0079ad:
				cuadrosCatalogo[i] = Cuadro.MARESQUINA_DB;
				continue;
			case 0xff47a0d9:
				cuadrosCatalogo[i] = Cuadro.MARESQUINA_IB;
				continue;
			case 0xfff3b8cd:
				cuadrosCatalogo[i] = Cuadro.MARPARED_A;
				continue;
			case 0xffc90058:
				cuadrosCatalogo[i] = Cuadro.MARPARED_B;
				continue;
			case 0xffd2e6b7:
				cuadrosCatalogo[i] = Cuadro.MARPARED_D;
				continue;
			case 0xffcc91b9:
				cuadrosCatalogo[i] = Cuadro.MARPARED_I;
				continue;
			case 0xff3aabc7:
				cuadrosCatalogo[i] = Cuadro.AGUA;
				continue;

			default:
				cuadrosCatalogo[i] = Cuadro.VACIO;

			}
		}
	}
}
