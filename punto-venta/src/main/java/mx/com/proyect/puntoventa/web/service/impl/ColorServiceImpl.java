package mx.com.proyect.puntoventa.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.ColorDAO;
import mx.com.proyect.puntoventa.web.model.ColorDTO;
import mx.com.proyect.puntoventa.web.service.ColorService;

@Service("colorService")
public class ColorServiceImpl implements ColorService {
	@Autowired
	ColorDAO colorDao;
	
	
	@Override
	public ColorDTO add(ColorDTO color) {
		return colorDao.add(color);
		 
	}

	@Override
	public List<ColorDTO> getAll() {		
		return colorDao.getAll();
	}

	@Override
	public boolean update(ColorDTO color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ColorDTO color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ColorDTO getById(float colorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColorDTO getByDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}

}
