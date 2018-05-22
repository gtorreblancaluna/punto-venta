package mx.com.proyect.puntoventa.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.OfficeDAO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;
import mx.com.proyect.puntoventa.web.service.OfficeService;

@Service("officeServiceImpl")
public class OfficeServiceImpl implements OfficeService {
	
	@Autowired
	OfficeDAO officeDao;

	@Override
	public List<OfficeDTO> getAll() {
		// TODO Auto-generated method stub
		return officeDao.getAll();
	}

}
