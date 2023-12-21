package entes.criatura;

import entes.Ente;
import graficos.Sprite;

public abstract class Criaturas extends Ente {
	protected Sprite sprite;
	protected char direccion = 'n';
	protected boolean enMovimiento = false;

	public void actualizar() {
	}

	public void mostrar() {
	}

	public void mover(int desplazamientoX, int desplazamientoY) {
		if (desplazamientoX > 0) {
			direccion = 'e';
		}
		if (desplazamientoX < 0) {
			direccion = 'o';
		}
		if (desplazamientoY > 0) {
			direccion = 's';
		}
		if (desplazamientoY < 0) {
			direccion = 'n';
		}

		if (!estaEliminado()) {

			modificarPosicionX(desplazamientoX);

			modificarPosicionY(desplazamientoY);
		}

	}

//	private boolean enColision(int desplazamientoX, int desplazamientoY) {
//
//		boolean colision = false;
//		// Hay que saber donde se encuentra el jugador
//		int posicionX = x + desplazamientoX;
//		int posicionY = y + desplazamientoY;
//
//		int margenIzquierdo = -8; // esto es para los pixeles del muñeco
//		int margenDerecho = 35;
//		int margenSuperior = -2;
//		int margenInferior = 32;
//
//		int bordeIzquierdo = (posicionX + margenDerecho) / sprite.obtenLado();
//		int bordeDerecho = (posicionX + margenIzquierdo + margenDerecho) / sprite.obtenLado();
//		int bordeSuperior = (posicionY + margenInferior) / sprite.obtenLado();
//		int bordeInferior = (posicionY + margenSuperior + margenInferior) / sprite.obtenLado();
//
//		if (mapa.obtenerCuadroCatalago(bordeIzquierdo + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
//			colision = true;
//		}
//		if (mapa.obtenerCuadroCatalago(bordeIzquierdo + bordeInferior * mapa.obtenerAncho()).esSolido()) {
//			colision = true;
//		}
//
//		if (mapa.obtenerCuadroCatalago(bordeDerecho + bordeSuperior * mapa.obtenerAncho()).esSolido()) {
//			colision = true;
//		}
//		if (mapa.obtenerCuadroCatalago(bordeDerecho + bordeInferior * mapa.obtenerAncho()).esSolido()) {
//			colision = true;
//		}
//
//		return colision;
//	}

	public Sprite obtenSprite() {
		return sprite;
	}
}
