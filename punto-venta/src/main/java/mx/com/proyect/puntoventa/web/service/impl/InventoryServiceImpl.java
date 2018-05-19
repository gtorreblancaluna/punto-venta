package mx.com.proyect.puntoventa.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.WarehouseDTO;
import mx.com.proyect.puntoventa.web.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryDAO inventoryDao;
	
	@Override
	public ItemDTO findById(Long id) {
		// TODO Auto-generated method stub
		return inventoryDao.findById(id);
	}

	@Override
	public ItemDTO validate(LoginForm loginForm) throws DataAccessException {
		// TODO Auto-generated method stub
		return inventoryDao.validate(loginForm);
	}

	@Override
	public boolean add(ItemDTO item) {
		inventoryDao.add(item);
		return true;
	}

	@Override
	public boolean update(ItemDTO item) {
		inventoryDao.update(item);	
		return true;
	}

	@Override
	public boolean delete(ItemDTO item) {
		inventoryDao.delete(item);
		return true;
	}

	@Override
	public List<ItemDTO> getAll() {
		// TODO Auto-generated method stub
		return inventoryDao.getAll();
	}

	@Override
	public List<WarehouseDTO> getAllWarehouse() {
		// TODO Auto-generated method stub
		return inventoryDao.getAllWarehouse();
	}

}