package mx.com.proyect.puntoventa.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.WarehouseDTO;

public interface InventoryService {
	public ItemDTO findById(Long id);
	ItemDTO validate(LoginForm loginForm) throws DataAccessException;
	boolean add(ItemDTO account);
	boolean update(ItemDTO account);
	boolean delete(ItemDTO account);
	public List<ItemDTO> getAll();
	public List<WarehouseDTO> getAllWarehouse();
}