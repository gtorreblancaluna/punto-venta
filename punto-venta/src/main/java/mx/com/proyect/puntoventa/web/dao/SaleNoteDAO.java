package mx.com.proyect.puntoventa.web.dao;

import java.util.List;
import java.util.Map;

import mx.com.proyect.puntoventa.web.forms.FiltroAbonos;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;
import mx.com.proyect.puntoventa.web.model.TipoAbonoDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;

public interface SaleNoteDAO {
	
	boolean add(SaleNoteForm saleNoteForm);
	boolean update(SaleNoteForm saleNoteForm);
	boolean delete(SaleNoteForm saleNoteForm);
	List<SaleNoteForm> getAll();
	List<ResultQuerySaleNote> getByFilter(SaleNoteFilter saleNoteFilter);
	SaleNoteForm getSaleNoteById(Integer id);
	SaleNoteDTO getSaleById(Integer id);
	List<ResultQuerySaleNote> getSalesByParameter(String parameter);
	public Map<String,Object> getItemsSold();
	public List<SaleStatusDTO> getSalesStatus();
	boolean changeStatus(int saleId, int statusId);
	Float getTotalSaleById(int id);
	public List<TipoAbonoDTO> obtenerTiposAbono();
	public void agregarAbono(AbonoDTO abono);
	public void eliminarAbono(int abonoId);
	public List<AbonoDTO> obtenerAbonosPorFiltro(FiltroAbonos filtroAbonos);
	public List<StoreDTO> obtenerAlmacenesPorSucursal(int sucursalId);

}
