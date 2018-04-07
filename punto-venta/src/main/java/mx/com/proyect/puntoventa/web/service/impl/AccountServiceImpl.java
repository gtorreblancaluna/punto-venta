package mx.com.proyect.puntoventa.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import mx.com.proyect.puntoventa.web.dao.AccountDAO;
import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;

@Service ("accountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDao;	

	@Override
	public AccountDTO findById(Long id) {
		return accountDao.findById(id);
		
//		return new AccountDTO();
	}

	@Override
	public AccountDTO validateUser(LoginForm loginForm) throws DataAccessException {
		
		return accountDao.validateUser(loginForm);
	}

}
