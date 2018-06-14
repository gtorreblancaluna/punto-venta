package mx.com.proyect.puntoventa.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.dao.SaleNoteDAO;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;

@Service("saleNoteServiceImpl")
public class SaleNoteServiceImpl implements SaleNoteService {

	@Autowired
	SaleNoteDAO saleNoteDao;
	@Autowired
	InventoryDAO inventoryDao;
	
	@Override
	public boolean add(SaleNoteForm saleNoteForm) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
		   
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
		
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY, 0);
		 cal.set(Calendar.MINUTE, 0);
		 cal.set(Calendar.SECOND, 0);
		 Date today = cal.getTime();
		 
		 String todayString = dateFormat.format(today);
	    
		// si la venta es igual a hoy , entonces se descontara los articulos de la bd
		if(todayString.equals(saleNoteForm.getDateSaleNote())) {
			// ponemos status de entregado
			saleNoteForm.setStatus("5");
			for(ItemDTO item : saleNoteForm.getItems()) {
				//descontamos los articulos de la bd
				ItemDTO itemDTO = inventoryDao.getItemById(item.getItemId());
				float stock_anterior = itemDTO.getStock();
				float stock_actual = stock_anterior - item.getAmountEntry();
				inventoryDao.decreaseStockByItemid(stock_actual,item.getItemId());
			} 
	    }else {
	    	//ponemos status de registrado
	    	saleNoteForm.setStatus("1");
	    }
		
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

	@Override
	public SaleNoteDTO getSaleById(Integer id) {
		return saleNoteDao.getSaleById(id);
	}
	

}
