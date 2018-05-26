package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;

public interface SaleNoteDAO {
	
	boolean add(SaleNoteForm saleNoteForm);
	boolean update(SaleNoteForm saleNoteForm);
	boolean delete(SaleNoteForm saleNoteForm);
	List<SaleNoteForm> getAll();

}
