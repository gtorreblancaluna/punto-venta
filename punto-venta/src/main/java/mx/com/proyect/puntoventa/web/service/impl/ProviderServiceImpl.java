package mx.com.proyect.puntoventa.web.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.commons.ApplicationConstants;
import mx.com.proyect.puntoventa.web.dao.InventoryDAO;
import mx.com.proyect.puntoventa.web.dao.ProviderDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryStatusDTO;
import mx.com.proyect.puntoventa.web.model.ItemDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;
import mx.com.proyect.puntoventa.web.service.ProviderService;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	private ProviderDAO providerDao;
	@Autowired
	private InventoryDAO inventoryDao;

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

	@Override
	public boolean cambiarEstatusEntrega(DeliveryDTO deliveryDTO , int estadoCambiar) {
		// TODO Auto-generated method stub
		int estadoOriginal = deliveryDTO.getDeliveryStatusDTO().getStatusId();
	
		
		if(	(estadoOriginal != ApplicationConstants.ENTREGA_AUTORIZADO &&
					estadoCambiar == ApplicationConstants.ENTREGA_AUTORIZADO
						))// vamos a agregar articulos al inventario
		{
			/**
			 * 2018.10.22
			 * Si la entrega esta diferente a autorizada 
			 * y se quiere cambiar a estado autorizado
			 * procedemos a incrementar los articulos al inventario
			 * */
			inventoryDao.incremetarStockEntrega(deliveryDTO.getDetails());
			
			deliveryDTO.getDeliveryStatusDTO().setStatusId(estadoCambiar);
			// se agrega la fecha de autorizado
			deliveryDTO.setFechaAutorizado(new Timestamp(System.currentTimeMillis()));
			providerDao.cambiarEstatusEntrega(deliveryDTO);
			
		}
		
		if(
				//2018.10.22
				/**
				 * Si la entrega esta como autorizada y se quiere cambiar a otro estado diferente a autorizado
				 * procedemos a quitar los articulos del inventario
				 * */
				estadoOriginal == ApplicationConstants.ENTREGA_AUTORIZADO &&
				estadoCambiar != ApplicationConstants.ENTREGA_AUTORIZADO
		) {
			inventoryDao.decrementarStockEntrega(deliveryDTO.getDetails());			
			deliveryDTO.getDeliveryStatusDTO().setStatusId(estadoCambiar);
			providerDao.cambiarEstatusEntrega(deliveryDTO);
		}
		return true;
	}

	@Override
	public List<DeliveryStatusDTO> obtenerEstatusEntrega() {
		// TODO Auto-generated method stub
		return providerDao.obtenerEstatusEntrega();
	}

}
