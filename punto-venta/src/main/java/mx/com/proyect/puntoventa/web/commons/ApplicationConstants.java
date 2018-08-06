package mx.com.proyect.puntoventa.web.commons;

public interface ApplicationConstants {

	/** Constantes para los valores de estatus de venta */
	public static final Integer VENTA_REGISTRADO = 1;
	public static final Integer VENTA_CANCELADO = 2;
	public static final Integer VENTA_AUTORIZADO = 3;
	public static final Integer VENTA_ARCHIVADO = 4;
	public static final Integer VENTA_ENTREGADO = 5;

	/** constantes para los valores de puesto del usuario */
	public static final Integer PUESTO_ADMINISTRADOR = 1;
	public static final Integer PUESTO_VENDEDOR = 2;
	public static final Integer PUESTO_CHOFER = 3;
	public static final Integer PUESTO_PROVEEDOR = 4;

	// Mascara fecha grande
	public static final String MASK_DATE_FORMAT = "EEEEEEEE dd 'de' MMMMMMMMMMMMMM, yyyy";

}
