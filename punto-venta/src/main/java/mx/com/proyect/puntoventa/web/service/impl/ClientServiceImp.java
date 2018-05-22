package mx.com.proyect.puntoventa.web.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.ClientDAO;
import mx.com.proyect.puntoventa.web.model.AccountDTOclient;
import mx.com.proyect.puntoventa.web.service.ClientService;

@Service("clientService")
public class ClientServiceImp implements ClientService{

	@Autowired
	ClientDAO clientDAO;
	
	@Override
	public boolean insertClient(AccountDTOclient a) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		a.setAddDate(timestamp);
		a.setState(a.getState().trim());
		return clientDAO.insertClient(a);
	}

	@Override
	public boolean updateClient(AccountDTOclient a) {
		// TODO Auto-generated method stub
		Timestamp timestamp =new Timestamp(System.currentTimeMillis());
		a.setAddDate(timestamp);
		a.setState(a.getState().trim());
		
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
		// TODO Auto-generated method stub
		return clientDAO.getAll();
	}
	
}
