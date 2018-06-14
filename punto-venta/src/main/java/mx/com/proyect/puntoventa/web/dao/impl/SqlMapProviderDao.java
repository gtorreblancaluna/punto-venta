package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.com.proyect.puntoventa.web.dao.ProviderDAO;
import mx.com.proyect.puntoventa.web.forms.SaleNoteFilter;
import mx.com.proyect.puntoventa.web.model.DeliveryDTO;
import mx.com.proyect.puntoventa.web.model.DeliveryDetailDTO;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;
import mx.com.proyect.puntoventa.web.resultsQuerys.ResultQueryDelivery;

public class SqlMapProviderDao extends SqlSessionDaoSupport implements ProviderDAO{

	@Override
	public ProviderDTO findByID(long id) {
		// TODO Auto-generated method stub
		return (ProviderDTO) getSqlSession().selectOne("findByItem");
	}


	@Override
	public boolean add(ProviderDTO provider) {
		getSqlSession().insert("addProvider",provider);
		return true;
	}

	@Override
	public boolean update(ProviderDTO provider) {
		getSqlSession().update("updateProvider",provider);
		return true;
	}

	@Override
	public boolean delete(ProviderDTO provider) {
		getSqlSession().update("deleteProvider",provider);
		return false;
	}

	@Override
	public List<ProviderDTO> getAll() {		
		return getSqlSession().selectList("getAllProvider");
	}


	@Override
	public boolean addDelivery(DeliveryDTO delivery) {
		// insertamos en c_entrega
		getSqlSession().insert("addDelivery",delivery);
		// insertamos los detalles				
		for(DeliveryDetailDTO dto : delivery.getDetails() ) {
			Map<String,Object> param = new HashMap<>();
			param.put("deliveryId", delivery.getDeliveryId());
			param.put("itemId", dto.getItem().getItemIdForm());
			param.put("colorId", dto.getItem().getColor().getColorId());
			param.put("amount", dto.getItem().getAmountEntry());			
			getSqlSession().insert("addDeliveryDetails",param);
		}
		return false;
	}


	@Override
	public List<ResultQueryDelivery> getByFilter(SaleNoteFilter saleNoteFilter) {
		return getSqlSession().selectList("getDeliveryByFilter",saleNoteFilter);
	}


	@Override
	public DeliveryDTO getDeliveryById(int id) {
		DeliveryDTO delivery = getSqlSession().selectOne("getDeliveryById",id);
		delivery.setDetails(getSqlSession().selectList("getDeliveryDetailsById",id));
		return delivery;
	}
	
	
	
	

}
