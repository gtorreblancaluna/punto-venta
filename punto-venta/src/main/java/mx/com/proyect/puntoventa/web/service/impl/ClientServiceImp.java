package mx.com.proyect.puntoventa.web.service.impl;

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
		return clientDAO.insertClient(a);
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
	
}
