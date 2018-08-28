package mx.com.proyect.puntoventa.web.service.impl;

import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.VENTA_ENTREGADO;
import static mx.com.proyect.puntoventa.web.commons.ApplicationConstants.VENTA_REGISTRADO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.ClientDAO;
import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.dao.SaleNoteDAO;
import mx.com.proyect.puntoventa.web.forms.FiltroAbonos;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.AbonoDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;
import mx.com.proyect.puntoventa.web.model.TipoAbonoDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;
import mx.com.proyect.puntoventa.web.service.ClientService;
import mx.com.proyect.puntoventa.web.service.SaleNoteService;;

@Service("saleNoteServiceImpl")
public class SaleNoteServiceImpl implements SaleNoteService {

	@Autowired
	private SaleNoteDAO saleNoteDao;
	@Autowired
	private InventoryDAO inventoryDao;
	@Autowired
	private ClientDAO clientDao;
	
	@Override
	public boolean add(SaleNoteForm saleNoteForm) {
		
		if(saleNoteForm.getCustomer() != null && saleNoteForm.getCustomer().getUserId() != 0) {
			// eligio un cliente de la lista
			saleNoteForm.setUserId(saleNoteForm.getCustomer().getUserId()+"");
		}else {
			// vamos agregar al cliente
			clientDao.insertClient(saleNoteForm.getCustomer());
			saleNoteForm.setUserId(saleNoteForm.getCustomer().getUserId()+"");
			
		}
		
		if(saleNoteForm.getStatus() == null)
    		saleNoteForm.setStatus(new SaleStatusDTO());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {		   
		    java.util.Date date = dateFormat.parse(saleNoteForm.getDateSaleNote());
		    java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		    saleNoteForm.setDateTimestamp(timestamp);
		} catch(Exception e) { //this generic but you can control another types of exception
		    System.out.println(e);
		}
		
		for(ItemDTO item : saleNoteForm.getItems()) {
//			String array[] = item.getItemIdForm().split("|");
//			item.setItemId(new Integer(array[0]));
			item.setItemId(new Integer (item.getItemIdForm()));
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
			saleNoteForm.getStatus().setStatusId(VENTA_ENTREGADO);
//			this.decreaseStock(saleNoteForm.getItems());
			for(ItemDTO item : saleNoteForm.getItems()) {
				//descontamos los articulos de la bd
				ItemDTO itemDTO = inventoryDao.getItemById(item.getItemId());
				float stock_anterior = itemDTO.getStock() == null ? 0f : itemDTO.getStock();
				float stock_actual = stock_anterior - item.getAmountEntry();
				inventoryDao.updateStockByItemid(stock_actual,item.getItemId());
			} 
	    }else {
	    	//ponemos status de registrado	    	
	    	saleNoteForm.getStatus().setStatusId(VENTA_REGISTRADO);
	    }
		
		saleNoteDao.add(saleNoteForm);
			
		return true;
	}
	
	// descontaremos stock desde cambiar estatus
	public boolean decreaseStockPerSale(List<SaleDetailDTO> details) {
		for(SaleDetailDTO detail : details) {
			//descontamos los articulos de la bd
			ItemDTO itemDTO = inventoryDao.getItemById(detail.getItem().getItemId());
			float stock_anterior = itemDTO.getStock();
			float stock_actual = stock_anterior - detail.getAmount();
			inventoryDao.updateStockByItemid(stock_actual,detail.getItem().getItemId());
		} 
		
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

	@Override
	public List<ResultQuerySaleNote> getSalesByParameter(String parameter) {
		return saleNoteDao.getSalesByParameter(parameter);
	}

	@Override
	public Map<String,Object> getItemsSold() {		
		return saleNoteDao.getItemsSold();
	}

	@Override
	public List<SaleStatusDTO> getSalesStatus() {
		// TODO Auto-generated method stub
		return saleNoteDao.getSalesStatus();
	}

	@Override
	public boolean changeStatus(int saleId, int statusId) {
		// TODO Auto-generated method stub
		return saleNoteDao.changeStatus(saleId,statusId);
	}

	@Override
	public Float getTotalSaleById(int id) {
		// TODO Auto-generated method stub
		return saleNoteDao.getTotalSaleById(id);
	}

	@Override
	public boolean increaseStockPerSale(List<SaleDetailDTO> details) {
		for(SaleDetailDTO detail : details) {
			//descontamos los articulos de la bd
			ItemDTO itemDTO = inventoryDao.getItemById(detail.getItem().getItemId());
			float stock_anterior = itemDTO.getStock();
			float stock_actual = stock_anterior + detail.getAmount();
			inventoryDao.updateStockByItemid(stock_actual,detail.getItem().getItemId());
		} 
		
		return true;
	}

	@Override
	public List<TipoAbonoDTO> obtenerTiposAbono() {
		// TODO Auto-generated method stub
		return saleNoteDao.obtenerTiposAbono();
	}

	@Override
	public void agregarAbono(AbonoDTO abono) {
		saleNoteDao.agregarAbono(abono);
		
	}

	@Override
	public void eliminarAbono(int abonoId) {
		saleNoteDao.eliminarAbono(abonoId);
		
	}

	@Override
	public List<AbonoDTO> obtenerAbonosPorFiltro(FiltroAbonos filtroAbonos) {
		// TODO Auto-generated method stub
		return saleNoteDao.obtenerAbonosPorFiltro(filtroAbonos);
	}
	

}
