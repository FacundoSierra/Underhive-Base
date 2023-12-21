package graficos;

public final class Sprite {
	private final int lado;

	private int X;
	private int Y;

	public int[] pixeles;
	private HojaSprites hoja;
	// coleccion de sprites KINAZUL

	public static final Sprite KINABAJO0 = new Sprite(32, 0, 0, HojaSprites.jugador);
	public static final Sprite KINABAJO1 = new Sprite(32, 0, 1, HojaSprites.jugador);
	public static final Sprite KINABAJO2 = new Sprite(32, 0, 2, HojaSprites.jugador);

	public static final Sprite KINARRIBA0 = new Sprite(32, 1, 0, HojaSprites.jugador);
	public static final Sprite KINARRIBA1 = new Sprite(32, 1, 1, HojaSprites.jugador);
	public static final Sprite KINARRIBA2 = new Sprite(32, 1, 2, HojaSprites.jugador);

	public static final Sprite KINDERECHA0 = new Sprite(32, 2, 0, HojaSprites.jugador);
	public static final Sprite KINDERECHA1 = new Sprite(32, 2, 1, HojaSprites.jugador);
	public static final Sprite KINDERECHA2 = new Sprite(32, 2, 2, HojaSprites.jugador);

	public static final Sprite KINIZQUIERDA0 = new Sprite(32, 3, 0, HojaSprites.jugador);
	public static final Sprite KINIZQUIERDA1 = new Sprite(32, 3, 1, HojaSprites.jugador);
	public static final Sprite KINIZQUIERDA2 = new Sprite(32, 3, 2, HojaSprites.jugador);

	// fin de la coleccion

	// coleccion de sprites KINROJO

	public static final Sprite KINROJOABAJO0 = new Sprite(32, 0, 0, HojaSprites.enemigo);
	public static final Sprite KINROJOABAJO1 = new Sprite(32, 0, 1, HojaSprites.enemigo);
	public static final Sprite KINROJOABAJO2 = new Sprite(32, 0, 2, HojaSprites.enemigo);

	public static final Sprite KINROJOARRIBA0 = new Sprite(32, 1, 0, HojaSprites.enemigo);
	public static final Sprite KINROJOARRIBA1 = new Sprite(32, 1, 1, HojaSprites.enemigo);
	public static final Sprite KINROJOARRIBA2 = new Sprite(32, 1, 2, HojaSprites.enemigo);

	public static final Sprite KINROJODERECHA0 = new Sprite(32, 2, 0, HojaSprites.enemigo);
	public static final Sprite KINROJODERECHA1 = new Sprite(32, 2, 1, HojaSprites.enemigo);
	public static final Sprite KINROJODERECHA2 = new Sprite(32, 2, 2, HojaSprites.enemigo);

	public static final Sprite KINROJOIZQUIERDA0 = new Sprite(32, 3, 0, HojaSprites.enemigo);
	public static final Sprite KINROJOIZQUIERDA1 = new Sprite(32, 3, 1, HojaSprites.enemigo);
	public static final Sprite KINROJOIZQUIERDA2 = new Sprite(32, 3, 2, HojaSprites.enemigo);

	// fin de la coleccion

	// coleccion de sprites
	public static final Sprite VACIO = new Sprite(32, 0);

	public static final Sprite ESQUINA_DA = new Sprite(32, 1, 0, HojaSprites.desierto);// esquina derecha arriba
	public static final Sprite ESQUINA_IA = new Sprite(32, 0, 0, HojaSprites.desierto);// esquina izquierda arriba
	public static final Sprite ESQUINA_DB = new Sprite(32, 1, 1, HojaSprites.desierto);// Esquina derecha bajo
	public static final Sprite ESQUINA_IB = new Sprite(32, 0, 1, HojaSprites.desierto);// esquina izquierdda bajo

	public static final Sprite PARED_A = new Sprite(32, 3, 0, HojaSprites.desierto);// A = Arriba
	public static final Sprite PARED_B = new Sprite(32, 4, 1, HojaSprites.desierto);// B = bajo
	public static final Sprite PARED_D = new Sprite(32, 4, 0, HojaSprites.desierto);// D = derecha
	public static final Sprite PARED_I = new Sprite(32, 3, 1, HojaSprites.desierto);// I = izquierda

	public static final Sprite MARESQUINA_DA = new Sprite(32, 1, 3, HojaSprites.desierto);// esquina derecha arriba
	public static final Sprite MARESQUINA_IA = new Sprite(32, 0, 3, HojaSprites.desierto);// esquina izquierda arriba
	public static final Sprite MARESQUINA_DB = new Sprite(32, 1, 4, HojaSprites.desierto);// Esquina derecha bajo
	public static final Sprite MARESQUINA_IB = new Sprite(32, 0, 4, HojaSprites.desierto);// esquina izquierdda bajo

	public static final Sprite MARPARED_A = new Sprite(32, 8, 0, HojaSprites.desierto);// A = Arriba
	public static final Sprite MARPARED_B = new Sprite(32, 9, 1, HojaSprites.desierto);// B = bajo
	public static final Sprite MARPARED_D = new Sprite(32, 9, 0, HojaSprites.desierto);// D = derecha
	public static final Sprite MARPARED_I = new Sprite(32, 8, 1, HojaSprites.desierto);// I = izquierda

	public static final Sprite AGUA = new Sprite(32, 7, 0, HojaSprites.desierto);

	public static final Sprite ARENA = new Sprite(32, 5, 0, HojaSprites.desierto);
	public static final Sprite PLANTA = new Sprite(32, 6, 1, HojaSprites.desierto);

	// fin de la coleccion

	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		this.X = columna * lado;
		this.Y = fila * lado;
		this.hoja = hoja;

		cargaNormal();
	}

	public Sprite(final int lado, final int color) {

		this.lado = lado;
		pixeles = new int[lado * lado];
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}

	}

	public int obtenLado() {
		return lado;
	}

	private void cargarSprite(int version) {
		if (version == 0) {
			cargaNormal();
		} else {
			// cargaManipulada(version);
		}
	}

	private void cargaNormal() {
		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.X) + (y + this.Y) * hoja.obtenAncho()];
			}
		}
	}

}