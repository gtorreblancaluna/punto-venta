package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.forms.UserFilter;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.model.JobDTO;

public interface AccountDAO {
	
	List<AccountDTO> getAll();	 
	AccountDTO findById(Long id);
	AccountDTO validateUser(LoginForm loginForm);
 
	void update(AccountDTO account);
 
	void remove(AccountDTO account);
 
	void insert(AccountDTO account);
	
	boolean deleteUser(AccountDTO account);
	AccountDTO getAccount(String email);
	public List<JobDTO> getAllJobs();
	List<AccountDTO> getUserByFilter(UserFilter userFilter);

}
