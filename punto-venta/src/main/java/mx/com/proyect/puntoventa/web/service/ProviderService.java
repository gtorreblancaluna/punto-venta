package mx.com.proyect.puntoventa.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryStatusDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;

public interface ProviderService {
	public ProviderDTO findByID(long id);
	ProviderDTO validate(LoginForm loginForm) throws DataAccessException;
	boolean add(ProviderDTO account);
	boolean update(ProviderDTO account);
	boolean delete(ProviderDTO account);
	public List<ProviderDTO> getAll();
	boolean addDelivery(DeliveryDTO delivery);
	List<ResultQueryDelivery> getByFilter(SaleNoteFilter saleNoteFilter);
	DeliveryDTO getDeliveryById(int id);
	boolean cambiarEstatusEntrega(DeliveryDTO deliveryDTO,int estadoCambiar);
	List<DeliveryStatusDTO> obtenerEstatusEntrega();
}
