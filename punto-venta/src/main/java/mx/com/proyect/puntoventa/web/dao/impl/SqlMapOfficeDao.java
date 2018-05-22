package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import mx.com.proyect.puntoventa.web.dao.OfficeDAO;
import mx.com.proyect.puntoventa.web.model.OfficeDTO;

public class SqlMapOfficeDao extends SqlSessionDaoSupport implements OfficeDAO {

	@Override
	public List<OfficeDTO> getAll() {		
		return getSqlSession().selectList("getAllOffices");
	}

}
