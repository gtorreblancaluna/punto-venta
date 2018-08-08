package mx.com.proyect.puntoventa.web.commons;

public interface ApplicationConstants {	
	/** ----------------------------------------------------------------------------
	 *  VALORES ESTATICOS PARA EL MANEJO DEL CATALOGO C_ESTATUS_VENTA EN LA BASE DE DATOS
	 * ------------------------------------------------------------------------------
	 * */	
	/** estado de la venta REGISTRADO = 1 */
	public static final Integer VENTA_REGISTRADO = 1;
	/** estado de la venta CANCELADO = 2 */
	public static final Integer VENTA_CANCELADO = 2;
	/** estado de la venta AUTORIZADO = 3 */
	public static final Integer VENTA_AUTORIZADO = 3;
	/** estado de la venta ARCHIVADO = 4 */
	public static final Integer VENTA_ARCHIVADO = 4;
	/** estado de la venta ENTREGADO = 5 */
	public static final Integer VENTA_ENTREGADO = 5;	
	/** ----------------------------------------------------------------------------
	 *  VALORES ESTATICOS PARA EL MANEJO DEL CATALOGO C_PUESTO EN LA BASE DE DATOS
	 * ------------------------------------------------------------------------------
	 * */
	/** estado para el puesto ADMINISTRADOR = 1 */
	public static final Integer PUESTO_ADMINISTRADOR = 1;
	/** estado para el puesto VENDEDOR = 2 */
	public static final Integer PUESTO_VENDEDOR = 2;
	/** estado para el puesto CHOFER = 3 */
	public static final Integer PUESTO_CHOFER = 3;
	/** estado para el puesto PROVEEDOR = 4 */
	public static final Integer PUESTO_PROVEEDOR = 4;

	// Mascara fecha grande
	public static final String MASK_DATE_FORMAT = "EEEEEEEE dd 'de' MMMMMMMMMMMMMM, yyyy";

	/** ----------------------------------------------------------------------------
	 *  VALORES ESTATICOS PARA EL MANEJO DEL CATALOGO C_ESTATUS_ENTREGAS EN LA BASE DE DATOS
	 * ------------------------------------------------------------------------------
	 * */	
	/** estado de la entrega REGISTRADO = 1 */
	public static final Integer ENTREGA_REGISTRADO = 1;
	/** estado de la venta CANCELADO = 2 */
	public static final Integer ENTREGA_CANCELADO = 2;
	/** estado de la venta AUTORIZADO = 3 */
	public static final Integer ENTREGA_AUTORIZADO = 3;
	/** estado de la venta ARCHIVADO = 4 */
	public static final Integer ENTREGA_ARCHIVADO = 4;
	/** estado de la venta ENTREGADO = 5 */
	public static final Integer ENTREGA_ENTREGADO = 5;
	
}
