package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryDetailDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryStatusDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.model.SaleDetailDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;

public interface ProviderDAO {
	public List<ProviderDTO> getAll();
	public ProviderDTO findByID(long id);	
	boolean add(ProviderDTO account);
	boolean update(ProviderDTO account);
	boolean delete(ProviderDTO account);
	boolean addDelivery(DeliveryDTO delivery);
	List<ResultQueryDelivery> getByFilter(SaleNoteFilter saleNoteFilter);
	DeliveryDTO getDeliveryById (int id);
	boolean cambiarEstatusEntrega(DeliveryDTO deliveryDTO);
	List<DeliveryStatusDTO> obtenerEstatusEntrega();
	
	
}
