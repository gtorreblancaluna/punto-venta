package mx.com.proyect.puntoventa.web.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;


public interface AccountService {
	
	public AccountDTO findById(Long id);
	AccountDTO validateUser(LoginForm loginForm) throws DataAccessException;

}
