package mx.com.proyect.puntoventa.web.service.impl;

import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.SaleNoteDAO;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Service("saleNoteServiceImpl")
public class SaleNoteServiceImpl implements SaleNoteService {

	@Autowired
	SaleNoteDAO saleNoteDao;
	
	@Override
	public boolean add(SaleNoteForm saleNoteForm) {
		
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    java.util.Date date = dateFormat.parse(saleNoteForm.getDateSaleNote());
		    java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		    saleNoteForm.setDateTimestamp(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    System.out.println(e);
		}
		
		for(ItemDTO item : saleNoteForm.getItems()) {
			String array[] = item.getItemIdForm().split("|");
			item.setItemId(new Integer(array[0]));
		}
		
		//2018.05.26 por el momento solo lo agregamos a la sucursal 1
		saleNoteForm.setStoreId("1");
		saleNoteDao.add(saleNoteForm);
		return true;
	}

	@Override
	public boolean update(SaleNoteForm saleNoteForm) {
		saleNoteDao.update(saleNoteForm);
		return false;
	}

	@Override
	public boolean delete(SaleNoteForm saleNoteForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SaleNoteForm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResultQuerySaleNote> getByFilter(SaleNoteFilter saleNoteFilter) {
		return saleNoteDao.getByFilter(saleNoteFilter);
	}

	@Override
	public SaleNoteForm getSaleNoteById(Integer id) {
		return saleNoteDao.getSaleNoteById(id);
	}

}
