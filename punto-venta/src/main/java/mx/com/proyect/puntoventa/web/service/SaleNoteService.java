package mx.com.proyect.puntoventa.web.service;

import java.util.List;

import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;

public interface SaleNoteService {
	
	boolean add(SaleNoteForm saleNoteForm);
	boolean update(SaleNoteForm saleNoteForm);
	boolean delete(SaleNoteForm saleNoteForm);
	List<SaleNoteForm> getAll();

}
