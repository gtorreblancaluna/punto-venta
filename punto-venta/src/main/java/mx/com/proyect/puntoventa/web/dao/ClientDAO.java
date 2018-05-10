package mx.com.proyect.puntoventa.web.dao;

import mx.com.proyect.puntoventa.web.model.AccountDTOclient;

public interface ClientDAO {

	boolean insertClient(AccountDTOclient a);
	boolean updateClient(AccountDTOclient a);
	boolean deleteClient(AccountDTOclient a);
	AccountDTOclient getClientById(String id);
	
}
