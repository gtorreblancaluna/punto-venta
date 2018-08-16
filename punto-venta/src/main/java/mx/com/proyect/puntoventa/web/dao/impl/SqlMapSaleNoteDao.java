package mx.com.proyect.puntoventa.web.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import mx.com.proyect.puntoventa.web.dao.SaleNoteDAO;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.forms.SaleNoteForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleNoteDTO;
import mx.com.proyect.puntoventa.web.model.SaleStatusDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryItemsSold;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQuerySaleNote;

public class SqlMapSaleNoteDao extends SqlSessionDaoSupport implements SaleNoteDAO {

	@Override
	public boolean add(SaleNoteForm saleNoteForm) {
		getSqlSession().insert("addSaleNote",saleNoteForm);
		// insertamos los detalles				
		for(ItemDTO item : saleNoteForm.getItems()) {
			Map<String,Object> param = new HashMap<>();
			param.put("saleId", saleNoteForm.getSaleId());
			param.put("itemId", item.getItemId());
			param.put("amount", item.getAmountEntry());
			param.put("colorId", item.getColor().getColorId());
			param.put("itemSalePrice", item.getSalePrice());
			getSqlSession().insert("addSaleNoteDetails",param);
		}
		return true;
	}

	@Override
	public boolean update(SaleNoteForm saleNoteForm) {
		// primero actualizamos la tabla c_venta
		getSqlSession().update("updateSaleNote",saleNoteForm);
		
		//borramos los detalles de la venta para despues insertarlos
		getSqlSession().delete("removeDetailSaleNote",saleNoteForm.getSaleId());
		
		// ahora agregamos los articulos que envian desde la vista actualizar nota de venta
		for(ItemDTO item : saleNoteForm.getItems()) {
			Map<String,Object> param = new HashMap<>();
			param.put("saleId", saleNoteForm.getSaleId());
			param.put("itemId", item.getItemIdForm());
			param.put("amount", item.getAmountEntry());
			param.put("colorId", item.getColor().getColorId());
			param.put("itemSalePrice", item.getSalePrice());
			getSqlSession().insert("addSaleNoteDetails",param);
		}		
		return true;
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
		List<ResultQuerySaleNote> list = getSqlSession().selectList("getSaleNoteByFilter",saleNoteFilter);		
		for(ResultQuerySaleNote queryRest: list) {
			queryRest.setSaleDetail(getSqlSession().selectList("getDetailSaleNoteById",queryRest.getSaleId()));
		}
		return list;
	}

	@Override
	public SaleNoteForm getSaleNoteById(Integer id) {
		SaleNoteForm saleNoteForm = getSqlSession().selectOne("getSaleNoteById",id);
		saleNoteForm.setSaleDetail(getSqlSession().selectList("getDetailSaleNoteById",id));
		return saleNoteForm;
	}

	@Override
	public SaleNoteDTO getSaleById(Integer id) {
		SaleNoteDTO saleNote = getSqlSession().selectOne("getSaleById",id);
		saleNote.setSaleDetails(getSqlSession().selectList("getDetailSaleNoteById",id));
		return saleNote;
	}

	@Override
	public List<ResultQuerySaleNote> getSalesByParameter(String parameter) {
		if(parameter.equals("today")) {
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today = df.format(date);
			return getSqlSession().selectList("getSalesToday",today);
		}else if(parameter.equals("week")) {
			return getSqlSession().selectList("getSalesWeek");
		}else if(parameter.equals("month")) {			
			Map<String,Object> parameters = new HashMap<>();
			LocalDate today = LocalDate.now();
			int month = today.getMonth().getValue();
			int year = today.getYear();
			parameters.put("month", month);
			parameters.put("year", year);
			return getSqlSession().selectList("getSalesMonth",parameters);
		}
		
		return null;
	}

	@Override
	public Map<String, Object> getItemsSold() {
		LocalDate today = LocalDate.now();
		int month = today.getMonth().getValue();
		int year = today.getYear();
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("month", month);
		parameters.put("year", year);		
		
		List<ResultQueryItemsSold> itemsByDay = getSqlSession().selectList("getItemsByDay",today.toString());
		List<ResultQueryItemsSold> itemsByWeek = getSqlSession().selectList("getItemsByWeek");
		List<ResultQueryItemsSold> itemsByMonth = getSqlSession().selectList("getItemsByMonth",parameters);
		
		result.put("itemsByDay", itemsByDay);
		result.put("itemsByWeek", itemsByWeek);
		result.put("itemsByMonth", itemsByMonth);
		
		return result;
	}

	@Override
	public List<SaleStatusDTO> getSalesStatus() {
		return getSqlSession().selectList("getSalesStatus");
	}

	@Override
	public boolean changeStatus(int saleId, int statusId) {
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("saleId", saleId);
		parameters.put("statusId", statusId);
		getSqlSession().update("changeStatusSaleNote",parameters);
		return true;
	}

	@Override
	public Float getTotalSaleById(int id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getTotalSaleById",id);
	}

}
