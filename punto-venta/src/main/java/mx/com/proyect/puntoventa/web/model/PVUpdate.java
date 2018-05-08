package mx.com.proyect.puntoventa.web.model;

public class PVUpdate {
private int Folio;
private String Fecha;
private String Cantidad;
private String Producto;
private String status;
private String unidad_M;
private String Vendedor;
private String Descripcion;
private int iva;
private int Precio;
private int subtotal;
public int getFolio() {
	return Folio;
}
public void setFolio(int folio) {
	Folio = folio;
}
public String getFecha() {
	return Fecha;
}
public void setFecha(String fecha) {
	Fecha = fecha;
}
public String getCantidad() {
	return Cantidad;
}
public void setCantidad(String cantidad) {
	Cantidad = cantidad;
}
public String getProducto() {
	return Producto;
}
public void setProducto(String producto) {
	Producto = producto;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getUnidad_M() {
	return unidad_M;
}
public void setUnidad_M(String unidad_M) {
	this.unidad_M = unidad_M;
}
public String getVendedor() {
	return Vendedor;
}
public void setVendedor(String vendedor) {
	Vendedor = vendedor;
}
public String getDescripcion() {
	return Descripcion;
}
public void setDescripcion(String descripcion) {
	Descripcion = descripcion;
}
public int getIva() {
	return iva;
}
public void setIva(int iva) {
	this.iva = iva;
}
public int getPrecio() {
	return Precio;
}
public void setPrecio(int precio) {
	Precio = precio;
}
public int getSubtotal() {
	return subtotal;
}
public void setSubtotal(int subtotal) {
	this.subtotal = subtotal;
}
@Override
public String toString() {
	return "PVUpdate [Folio=" + Folio + ", Fecha=" + Fecha + ", Cantidad=" + Cantidad + ", Producto=" + Producto
			+ ", status=" + status + ", unidad_M=" + unidad_M + ", Vendedor=" + Vendedor + ", Descripcion="
			+ Descripcion + ", iva=" + iva + ", Precio=" + Precio + ", subtotal=" + subtotal + "]";
}


	
	
}
