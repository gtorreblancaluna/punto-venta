package mx.com.proyect.puntoventa.web.resultsQuerys;
/**
 * GTL - 2018.07.06
 * POJO contendra el resultado de la consulta de articulos vendidos 
 * por semana, mes y dia
 * */
public class ResultQueryItemsSold {
	
	// total_articulos - total vendidos
	private Integer total;
	// cl_articulo - id de articulo
	private Integer itemId;
	// descripcion del articulo
	private String description;
	// precio_articulo
	private Float itemPrice;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	

}
