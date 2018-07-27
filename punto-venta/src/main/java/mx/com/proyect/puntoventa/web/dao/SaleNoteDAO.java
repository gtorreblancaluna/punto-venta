package mx.com.proyect.puntoventa.web.dao;

import java.util.List;
import java.util.Map;

import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;
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

}
