package mx.com.proyect.puntoventa.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.StoreDTO;

public interface InventoryService {
	public ItemDTO findById(Long id);
	ItemDTO validate(LoginForm loginForm) throws DataAccessException;
	boolean add(ItemDTO account);
	boolean update(ItemDTO account);
	boolean delete(ItemDTO account);
	public List<ItemDTO> getAll(int sucursalId);
	public List<StoreDTO> getAllStore();
	public List<ItemDTO> getItemsByFilter(SaleNoteFilter saleNoteFilter);
}
