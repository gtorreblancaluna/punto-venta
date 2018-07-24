package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
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
	public List<ItemDTO> getAll() {		
		return getSqlSession().selectList("getAllItems");
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
	public boolean decreaseStockByItemid(float stock,Integer id) {
		Map<String,Object> param = new HashMap<>();
		param.put("stock", stock);
		param.put("id", id);
		getSqlSession().update("decreaseStockByItemid",param);
		return true;
	}

	@Override
	public List<ItemDTO> getItemsByFilter(SaleNoteFilter saleNoteFilter) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getItemsByFilter",saleNoteFilter);
	}

}
