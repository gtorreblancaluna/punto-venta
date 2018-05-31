package mx.com.proyect.puntoventa.web.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import mx.com.proyect.puntoventa.web.forms.LoginForm;
import mx.com.proyect.puntoventa.web.model.ProviderDTO;

public interface ProviderDAO {
	public ProviderDTO findByID(long id);
	ProviderDTO validate(LoginForm loginForm) throws DataAccessException;
	boolean add(ProviderDTO account);
	boolean update(ProviderDTO account);
	boolean delete(ProviderDTO account);
	public List<ProviderDTO> getAll();
}
