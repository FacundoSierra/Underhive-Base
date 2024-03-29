package graficos;

import entes.criatura.Jugador;
import entes.criatura.NPC;
import mapa.cuadro.Cuadro;

public class Pantalla {
	private final int ancho;
	private final int alto;

	private int diferenciaX;
	private int diferenciaY;
	public final int[] pixeles;

	// temporal
//	private final static int LADO_SPRITE = 32;
//	private final static int MASCARA_SPRITE = LADO_SPRITE - 1;// porque necesitamos 32 valores y es del 0-31

	// fin temporal

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];
	}

	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	} // lo de compesacion es para que dibuhe y a la vez se puedan usar los controles

//	public void mostrar(final int compesacionX, final int compesacionY) {
//		for (int y = 0; y < alto; y++) {
//			int posicionY = y + compesacionY;
//			if (posicionY < 0 || posicionY >= alto) {
//				continue;// significa que nos salimos del bucle for y pasamos al siguiente nivel
//			}
//
//			for (int x = 0; x < ancho; x++) {
//				int posicionX = x + compesacionX;// error : puse = y + compesacionX.
//				if (posicionX < 0 || posicionX >= ancho) {
//					continue;
//				}
//				// temporal
//				pixeles[posicionX + posicionY * ancho] = Sprite.ARENA.pixeles[(x & MASCARA_SPRITE)
//						+ (y & MASCARA_SPRITE) * LADO_SPRITE];// el & es para que cuando llegue al valor de mascara
//																// sprite vuelva a contar en la siguiente fila.
//			}
//
//		}
//	}

	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -cuadro.sprite.obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;

				}
				if (posicionX < 0) {
					posicionX = 0;
				}
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenLado()];
			}
		}
	}

	public void mostrarJugador(int compensacionX, int compensacionY, Jugador jugador) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < jugador.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < jugador.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -jugador.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;

				}
				if (posicionX < 0) {
					posicionX = 0;
				}
//				pixeles[posicionX + posicionY * ancho] = jugador.obtenSprite().pixeles[x
//						+ y * jugador.obtenSprite().obtenLado()];
				int colorPixelJugador = jugador.obtenSprite().pixeles[x + y * jugador.obtenSprite().obtenLado()];
				if (colorPixelJugador != 0xffffffff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}

//Final del jugador
	public void mostrarNpc(int compensacionX, int compensacionY, NPC npc) {
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;

		for (int y = 0; y < npc.obtenSprite().obtenLado(); y++) {
			int posicionY = y + compensacionY;
			for (int x = 0; x < npc.obtenSprite().obtenLado(); x++) {
				int posicionX = x + compensacionX;
				if (posicionX < -npc.obtenSprite().obtenLado() || posicionX >= ancho || posicionY < 0
						|| posicionY >= alto) {
					break;

				}
				if (posicionX < 0) {
					posicionX = 0;
				}

				int colorPixelJugador = npc.obtenSprite().pixeles[x + y * npc.obtenSprite().obtenLado()];
				if (colorPixelJugador != 0xffffffff) {
					pixeles[posicionX + posicionY * ancho] = colorPixelJugador;
				}
			}
		}
	}
	// Final del enemigo

	public void estableceDiferencia(final int diferenciaX, final int diferenciaY) {
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;
	}

	public int obtenAncho() {
		return ancho;
	}

	public int obtenAlto() {
		return alto;
	}

}
