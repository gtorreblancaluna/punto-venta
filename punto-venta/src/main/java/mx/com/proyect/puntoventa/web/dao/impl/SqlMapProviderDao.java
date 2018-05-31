package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.dao.ProviderDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;

public class SqlMapProviderDao extends SqlSessionDaoSupport implements ProviderDAO{

	@Override
	public ProviderDTO findByID(long id) {
		// TODO Auto-generated method stub
		return (ProviderDTO) getSqlSession().selectOne("findByItem");
	}

	@Override
	public ProviderDTO validate(LoginForm loginForm) throws DataAccessException {
		// TODO Auto-generated method stub
		return (ProviderDTO) getSqlSession().selectOne("ValidateItem",loginForm);
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
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getAllProvider");
	}
	
	

}
