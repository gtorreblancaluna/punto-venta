package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;

public interface InventoryDAO {
	
	public ItemDTO findById(Long id);
	ItemDTO validate(LoginForm loginForm) throws DataAccessException;
	boolean add(ItemDTO item);
	boolean update(ItemDTO item);
	boolean delete(ItemDTO item);
	public List<ItemDTO> getAll();
	public List<StoreDTO> getAllStore();
	ItemDTO getItemById(int id);
	boolean decreaseStockByItemid(float stock, Integer id);
	public List<ItemDTO> getItemsByFilter(SaleNoteFilter saleNoteFilter);
}
