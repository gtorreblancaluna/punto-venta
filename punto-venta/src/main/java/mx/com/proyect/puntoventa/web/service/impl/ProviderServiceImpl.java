package mx.com.proyect.puntoventa.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.ProviderDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;
import mx.com.proyect.puntoventa.web.service.ProviderService;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	ProviderDAO providerDao;

	@Override
	public ProviderDTO findByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProviderDTO validate(LoginForm loginForm) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ProviderDTO account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ProviderDTO account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProviderDTO account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProviderDTO> getAll() {
		return providerDao.getAll();
	}

	@Override
	// agregar entrega a c_entrega
	public boolean addDelivery(DeliveryDTO delivery) {
		providerDao.addDelivery(delivery);
				
		
		return true;
	}

	@Override
	public List<ResultQueryDelivery> getByFilter(SaleNoteFilter saleNoteFilter) {
		// TODO Auto-generated method stub
		return providerDao.getByFilter(saleNoteFilter);
	}

	@Override
	public DeliveryDTO getDeliveryById(int id) {
		// TODO Auto-generated method stub
		return providerDao.getDeliveryById(id);
	}

}
