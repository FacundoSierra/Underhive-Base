package graficos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {

	private final int ancho;
	private final int alto;
	public final int[] pixeles;

	// coleccion de hojas de sprites
	// Mapa

	public static HojaSprites desierto = new HojaSprites("recursos/texturas/SpritesMapa2.png", 320, 320);
	// Jugador

	public static HojaSprites jugador = new HojaSprites("recursos/texturas/KINAZUL.png", 320, 320);
	public static HojaSprites enemigo = new HojaSprites("recursos/texturas/KinRojoSprites.png", 320, 320);

	// fin de la coleccion

	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		BufferedImage imagen;
		try {
			File file = new File(ruta); // Se agregan estos metodos de File
			FileInputStream img = new FileInputStream(file);

			imagen = ImageIO.read(img);// y se pone el img delInputStream
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int obtenAncho() {
		return ancho;
	}
}// Clase
