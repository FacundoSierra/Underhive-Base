package entes.criatura;

import entes.Spam;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class NPC extends Criaturas {

	private Jugador jugador;
	private int animacion;
	private Spam spam;

	public NPC(Mapa mapa, Sprite sprite, Jugador jugador) {
		this.mapa = mapa;
		this.jugador = jugador;
		this.sprite = sprite;

	}

	public NPC(Mapa mapa, Sprite sprite, Jugador jugador, int posicionX, int posicionY) {
		this.mapa = mapa;
		this.sprite = sprite;
		this.jugador = jugador;
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		// Calcular la distancia entre el enemigo y el jugador
		int distanciaX = jugador.getX() - x;
		int distanciaY = jugador.getY() - y;

		int velocidadMovimiento = 1;
		int distanciaMinima = 29000;
		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}
		if (Math.abs(distanciaX) <= distanciaMinima && Math.abs(distanciaY) <= distanciaMinima) {
			// El jugador está lo suficientemente cerca, persigue al jugador
			if (distanciaX != 0) {
				desplazamientoX = distanciaX / Math.abs(distanciaX); // Mover hacia la dirección del jugador en el eje X
			}
			if (distanciaY != 0) {
				desplazamientoY = distanciaY / Math.abs(distanciaY); // Mover hacia la dirección del jugador en el eje Y
			}
		} else {
			// El jugador está lejos, moverse en una dirección aleatoria
			cambiarDireccionAleatoria();
			if (direccion == 'n') {
				desplazamientoY -= velocidadMovimiento;
			} else if (direccion == 's') {
				desplazamientoY += velocidadMovimiento;
			} else if (direccion == 'o') {
				desplazamientoX -= velocidadMovimiento;
			} else if (direccion == 'e') {
				desplazamientoX += velocidadMovimiento;
			}
		}

		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}

		if (direccion == 'n') {
			sprite = Sprite.KINROJOARRIBA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINROJOARRIBA1;
				} else {
					sprite = Sprite.KINROJOARRIBA2;
				}
			}
		}
		if (direccion == 's') {
			sprite = Sprite.KINROJOABAJO0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINROJOABAJO1;
				} else {
					sprite = Sprite.KINROJOABAJO2;
				}
			}
		}
		if (direccion == 'o') {
			sprite = Sprite.KINROJOIZQUIERDA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINROJOIZQUIERDA1;
				} else {
					sprite = Sprite.KINROJOIZQUIERDA2;
				}
			}
		}
		if (direccion == 'e') {
			sprite = Sprite.KINROJODERECHA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINROJODERECHA1;
				} else {
					sprite = Sprite.KINROJODERECHA2;
				}
			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarNpc(x, y, this);
	}

	private void cambiarDireccionAleatoria() {
		// Generar una dirección aleatoria para el enemigo
		int direccionAleatoria = (int) (Math.random() * 4);
		if (direccionAleatoria == 0) {
			direccion = 'n';
		} else if (direccionAleatoria == 1) {
			direccion = 's';
		} else if (direccionAleatoria == 2) {
			direccion = 'o';
		} else if (direccionAleatoria == 3) {
			direccion = 'e';
		}
	}

}
