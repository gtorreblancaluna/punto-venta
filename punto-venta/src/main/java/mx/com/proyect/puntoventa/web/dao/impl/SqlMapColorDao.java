package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import mx.com.proyect.puntoventa.web.dao.ColorDAO;
import mx.com.proyect.puntoventa.web.model.ColorDTO;

public class SqlMapColorDao extends SqlSessionDaoSupport implements ColorDAO {

	@Override
	public ColorDTO add(ColorDTO color) {
		getSqlSession().insert("addColor",color);
		return color;
	}

	@Override
	public List<ColorDTO> getAll() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getAllColors");
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
