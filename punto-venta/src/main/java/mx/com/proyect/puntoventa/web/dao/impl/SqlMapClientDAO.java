package mx.com.proyect.puntoventa.web.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import mx.com.proyect.puntoventa.web.dao.ClientDAO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;

public class SqlMapClientDAO extends SqlSessionDaoSupport implements ClientDAO {

	@Override
	public boolean insertClient(AccountDTOclient a) {
		getSqlSession().insert("insertClient", a);
		return true;
	}

	@Override
	public boolean updateClient(AccountDTOclient a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClient(AccountDTOclient a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountDTOclient getClientById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDTOclient> getAll() {
		return getSqlSession().selectList("getAllClient");
	}

}
