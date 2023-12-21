package entes.criatura;

import control.Teclado;
import graficos.Pantalla;
import graficos.Sprite;
import mapa.Mapa;

public class Jugador extends Criaturas {
	private Teclado teclado;

	private int animacion;

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite) {
		this.mapa = mapa;
		this.teclado = teclado;
		this.sprite = sprite;
	}

	public Jugador(Mapa mapa, Teclado teclado, Sprite sprite, int posicionX, int posicionY) {
		this.mapa = mapa;
		this.sprite = sprite;
		this.teclado = teclado;
		this.x = posicionX;
		this.y = posicionY;
	}

	public void actualizar() {
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		int velocidadMovimiento = 1;

		if (animacion < 32767) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (teclado.correr) {
			velocidadMovimiento = 2;
		}

		if (teclado.arriba) {
			desplazamientoY -= velocidadMovimiento;

		}
		if (teclado.abajo) {
			desplazamientoY += velocidadMovimiento;
		}
		if (teclado.izquierda) {
			desplazamientoX -= velocidadMovimiento;
		}
		if (teclado.derecha) {
			desplazamientoX += velocidadMovimiento;
		}
		if (desplazamientoX != 0 || desplazamientoY != 0) {
			mover(desplazamientoX, desplazamientoY);
			enMovimiento = true;
		} else {
			enMovimiento = false;
		}
		if (direccion == 'n') {
			sprite = Sprite.KINARRIBA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINARRIBA1;
				} else {
					sprite = Sprite.KINARRIBA2;
				}
			}
		}
		if (direccion == 's') {
			sprite = Sprite.KINABAJO0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINABAJO1;
				} else {
					sprite = Sprite.KINABAJO2;
				}
			}
		}
		if (direccion == 'o') {
			sprite = Sprite.KINIZQUIERDA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINIZQUIERDA1;
				} else {
					sprite = Sprite.KINIZQUIERDA2;
				}
			}
		}
		if (direccion == 'e') {
			sprite = Sprite.KINDERECHA0;
			if (enMovimiento) {
				if (animacion % 30 > 15) { // poner 30 y su mitad por ejemplo 20% > 10
					sprite = Sprite.KINDERECHA1;
				} else {
					sprite = Sprite.KINDERECHA2;
				}
			}
		}
	}

	public void mostrar(Pantalla pantalla) {
		pantalla.mostrarJugador(x, y, this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
