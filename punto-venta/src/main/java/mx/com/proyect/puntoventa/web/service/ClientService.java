package mx.com.proyect.puntoventa.web.service;

import mx.com.proyect.puntoventa.web.model.AccountDTOclient;

public interface ClientService {
	
	boolean insertClient(AccountDTOclient a);
	boolean updateClient(AccountDTOclient a);
	boolean deleteClient(AccountDTOclient a);
	AccountDTOclient getClientById(String id);
}
