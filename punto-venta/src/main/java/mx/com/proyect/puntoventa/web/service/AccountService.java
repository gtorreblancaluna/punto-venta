package mx.com.proyect.puntoventa.web.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;


public interface AccountService {
	
	public AccountDTO findById(Long id);
	AccountDTO validateUser(LoginForm loginForm) throws DataAccessException;
	boolean addUser(AccountDTO account);
	boolean updateUser(AccountDTO account);
	boolean deleteUser(AccountDTO account);
	public List<AccountDTO> getAllUser();
	AccountDTO getAccount(String email);
}




