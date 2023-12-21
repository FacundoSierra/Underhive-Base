package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

/*El abstract es para que la clase mapa no pueda instanciarse.
*es una clase de plantilla en la que en un futuro podremos hacer
*mas plantillas con ella.*/
public abstract class Mapa {
	protected int ancho;
	protected int alto;

	protected int[] cuadros;
	protected Cuadro[] cuadrosCatalogo;// para mapaCargafdo

	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		cuadros = new int[ancho * alto];
		generarMapa();
	}

	public Mapa(String ruta) {
		cargarMapa(ruta);
		generarMapa();
	}

	protected void generarMapa() {
	}

	protected void cargarMapa(String ruta) {
	}

	public void actualizar() {

	}

	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla) {

		pantalla.estableceDiferencia(compensacionX, compensacionY);

		int o = compensacionX >> 5; // /32
		int e = (compensacionX + pantalla.obtenAncho() + Cuadro.LADO) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.obtenAlto() + Cuadro.LADO) >> 5;

		for (int y = n; y < s; y++) {
			for (int x = o; x < e; x++) {
				// (x, y).mostrar(x, y, pantalla);
				if (x < 0 || y < 0 || x >= ancho || y >= alto) {
					Cuadro.VACIO.mostrar(x, y, pantalla);
				} else {
					cuadrosCatalogo[x + y * ancho].mostrar(x, y, pantalla);
				}
			}
		}
	}

	public Cuadro obtenCuadro(final int x, final int y) {
		if (x < 0 || y < 0 || x >= ancho || y >= alto) {
			return Cuadro.VACIO;
		}
		switch (cuadros[x + y * ancho]) {
		case 0:
			return Cuadro.ESQUINA_DA;
		case 1:
			return Cuadro.ESQUINA_IA;
		case 2:
			return Cuadro.ESQUINA_DB;
		case 3:
			return Cuadro.ESQUINA_IB;
		case 4:
			return Cuadro.PARED_A;
		case 5:
			return Cuadro.PARED_B;
		case 6:
			return Cuadro.PARED_D;
		case 7:
			return Cuadro.PARED_I;
		case 8:
			return Cuadro.ARENA;
		case 9:
			return Cuadro.PLANTA;
		case 10:
			return Cuadro.MARESQUINA_DA;
		case 11:
			return Cuadro.MARESQUINA_IA;
		case 12:
			return Cuadro.MARESQUINA_DB;
		case 13:
			return Cuadro.MARESQUINA_IB;
		case 14:
			return Cuadro.MARPARED_A;
		case 15:
			return Cuadro.MARPARED_B;
		case 16:
			return Cuadro.MARPARED_D;
		case 17:
			return Cuadro.MARPARED_I;
		case 18:
			return Cuadro.AGUA;
		default:
			return Cuadro.VACIO;
		}
	}

	public Cuadro obtenerCuadroCatalago(int posicion) {
		return cuadrosCatalogo[posicion];

	}

	public int obtenerAncho() {
		return ancho;

	}

}