package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;

public interface AccountDAO {
	
	List<AccountDTO> getAll();	 
	AccountDTO findById(Long id);
	AccountDTO validateUser(LoginForm loginForm);
 
	void update(AccountDTO account);
 
	void remove(AccountDTO account);
 
	void insert(AccountDTO account);
}
