package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDetailDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryStatusDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;

public class SqlMapInventoryDao extends SqlSessionDaoSupport implements InventoryDAO {

	@Override
	public ItemDTO findById(Long id) {		
		return (ItemDTO) getSqlSession().selectOne("findByIdItem",id);
	}

	@Override
	public ItemDTO validate(LoginForm loginForm) throws DataAccessException {		
		return (ItemDTO) getSqlSession().selectOne("validateItem",loginForm);
	}

	@Override
	public boolean add(ItemDTO item) {		
		 getSqlSession().insert("addItem",item);
		 return true;
	}

	@Override
	public boolean update(ItemDTO item) {
		getSqlSession().update("updateItem",item);
		return true;
	}

	@Override
	public boolean delete(ItemDTO item) {
		getSqlSession().update("deleteItem",item);
		return true;
	}

	@Override
	public List<ItemDTO> getAll(int id) {	
		Map<String,Object> param = new HashMap<>();
		param.put("id", id);
		return getSqlSession().selectList("getAllItems", param);
	}

	@Override
	public List<StoreDTO> getAllStore() {
		
		return getSqlSession().selectList("getAllStore");
	}

	@Override
	public ItemDTO getItemById(int id) {
		return getSqlSession().selectOne("getItemById",id);
	}

	@Override
	public boolean updateStockByItemid(float stock,Integer id) {
		Map<String,Object> param = new HashMap<>();
		param.put("stock", stock);
		param.put("id", id);
		getSqlSession().update("updateStockByItemid",param);
		return true;
	}

	@Override
	public List<ItemDTO> getItemsByFilter(SaleNoteFilter saleNoteFilter) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getItemsByFilter",saleNoteFilter);
	}

	@Override
	public boolean incremetarStockEntrega(List<DeliveryDetailDTO> details) {
		for(DeliveryDetailDTO detail : details) {
			//descontamos los articulos de la bd
			ItemDTO itemDTO = getSqlSession().selectOne("getItemById",detail.getItem().getItemId());
			float stock_anterior = itemDTO.getStock();
			float stock_actual = stock_anterior + detail.getAmount();
					
			Map<String,Object> param = new HashMap<>();
			param.put("stock", stock_actual);
			param.put("id", detail.getItem().getItemId());
			getSqlSession().update("updateStockByItemid",param);
		} 
		return true;
	}
	
	@Override
	public boolean decrementarStockEntrega(List<DeliveryDetailDTO> details) {
		for(DeliveryDetailDTO detail : details) {
			//descontamos los articulos de la bd
			ItemDTO itemDTO = getSqlSession().selectOne("getItemById",detail.getItem().getItemId());
			float stock_anterior = itemDTO.getStock();
			float stock_actual = stock_anterior - detail.getAmount();
					
			Map<String,Object> param = new HashMap<>();
			param.put("stock", stock_actual);
			param.put("id", detail.getItem().getItemId());
			getSqlSession().update("updateStockByItemid",param);
		} 
		return true;
	}

	
}
