package entes;

import entes.criatura.Jugador;
import entes.criatura.NPC;
import graficos.Pantalla;
import graficos.Sprite;
import juego.Juego;

public class Spam {
	private Jugador jugador;
	private Juego juego;

	long tiempo = juego.getTiempo();
	int npcs[][] = new int[128][4];

	int referencia[] = { jugador.getX(), jugador.getY() };

	/*
	 * 450 * 350
	 */

	private void generarNpcs() {

		for (int z = 0; z < 128; z++) {
			for (int y = 0; y < 4; y++) {
				npcs[z][y] = 256;
			}
		}

		long a = (long) 20000 * tiempo;
		long b = (long) (Math.random() * 1000000000);
		if (b <= a) {
			int z = 0;

			while (z < 128) {
				if (npcs[z][0] == 256 && z >= 0 && z < 128) {
					npcs[z][0] = z;
					npcs[z][1] = 0;

					if ((Math.random() * 1) == 0) {
						if ((Math.random() * 1) == 0) {
							npcs[z][2] = ((int) (450 + (Math.random() * 50))) + jugador.getX();
							if ((Math.random() * 1) == 0) {
								npcs[z][3] = ((int) ((Math.random() * 450))) + jugador.getY();
							} else {
								npcs[z][3] = (((int) ((Math.random() * 450))) * -1) + jugador.getY();
							}
						} else {
							npcs[z][2] = ((int) (450 + (Math.random() * 50)) * -1) + jugador.getX();
							if ((Math.random() * 1) == 0) {
								npcs[z][3] = ((int) ((Math.random() * 450))) + jugador.getY();
							} else {
								npcs[z][3] = (((int) ((Math.random() * 450))) * -1) + jugador.getY();
							}

						}

					} else if ((Math.random() * 1) == 0) {
						if ((Math.random() * 1) == 0) {
							npcs[z][3] = ((int) (450 + (Math.random() * 50))) + jugador.getY();
							if ((Math.random() * 1) == 0) {
								npcs[z][2] = ((int) ((Math.random() * 350))) + jugador.getX();
							} else {
								npcs[z][2] = (((int) ((Math.random() * 350))) * -1) + jugador.getX();
							}
						} else {
							npcs[z][3] = ((int) (450 + (Math.random() * 50)) * -1) + jugador.getY();
							if ((Math.random() * 1) == 0) {
								npcs[z][2] = ((int) ((Math.random() * 350))) + jugador.getX();
							} else {
								npcs[z][2] = (((int) ((Math.random() * 350))) * -1) + jugador.getX();
							}

						}

					}

					z = 256;

				} else if (z < 128 && z >= 0) {
					z++;
				} else {
					z = 256;
				}

			}
		} // if
	}// void

	public void atualizar() {
		generarNpcs();

	}

	public void mostrar(Pantalla pantalla) {
		for (int z = 0; z < 128; z++) {
			if (npcs[z][0] != 256) {
				int x = npcs[z][2];
				int y = npcs[z][3];
				NPC enemigo = new NPC(Juego.obtenrMapa(), Sprite.KINROJOARRIBA0, jugador, 14250, 14250);
				pantalla.mostrarNpc(x, y, this);
			}
		}

	}
}// clase
