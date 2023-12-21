package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

public class Cuadro {
	public int x;
	public int y;

	public Sprite sprite;

	private boolean solido;

	public static final int LADO = 32;

	// Colección de cuadros
	public static final Cuadro VACIO = new Cuadro(Sprite.VACIO, true);

	public static final Cuadro ESQUINA_DA = new Cuadro(Sprite.ESQUINA_DA, true);
	public static final Cuadro ESQUINA_IA = new Cuadro(Sprite.ESQUINA_IA, true);
	public static final Cuadro ESQUINA_DB = new Cuadro(Sprite.ESQUINA_DB, true);
	public static final Cuadro ESQUINA_IB = new Cuadro(Sprite.ESQUINA_IB, true);

	public static final Cuadro PARED_A = new Cuadro(Sprite.PARED_A, true);
	public static final Cuadro PARED_B = new Cuadro(Sprite.PARED_B, true);
	public static final Cuadro PARED_D = new Cuadro(Sprite.PARED_D, true);
	public static final Cuadro PARED_I = new Cuadro(Sprite.PARED_I, true);

	public static final Cuadro ARENA = new Cuadro(Sprite.ARENA);
	public static final Cuadro PLANTA = new Cuadro(Sprite.PLANTA, true);

	public static final Cuadro MARESQUINA_DA = new Cuadro(Sprite.MARESQUINA_DA, true);
	public static final Cuadro MARESQUINA_IA = new Cuadro(Sprite.MARESQUINA_IA, true);
	public static final Cuadro MARESQUINA_DB = new Cuadro(Sprite.MARESQUINA_DB, true);
	public static final Cuadro MARESQUINA_IB = new Cuadro(Sprite.MARESQUINA_IB, true);

	public static final Cuadro MARPARED_A = new Cuadro(Sprite.MARPARED_A, true);
	public static final Cuadro MARPARED_B = new Cuadro(Sprite.MARPARED_B, true);
	public static final Cuadro MARPARED_D = new Cuadro(Sprite.MARPARED_D, true);
	public static final Cuadro MARPARED_I = new Cuadro(Sprite.MARPARED_I, true);

	public static final Cuadro AGUA = new Cuadro(Sprite.AGUA);

	// Fin de la colección de cuadros

	public Cuadro(Sprite sprite) {
		this.sprite = sprite;
		solido = false;
	}

	public Cuadro(Sprite sprite, boolean solido) {
		this.sprite = sprite;
		this.solido = solido;
		;
	}

	public void mostrar(int x, int y, Pantalla pantalla) {
		pantalla.mostrarCuadro(x << 5, y << 5, this);
	}

	public boolean esSolido() {
		return solido;
	}
}