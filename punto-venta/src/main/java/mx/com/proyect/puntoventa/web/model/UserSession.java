package mx.com.proyect.puntoventa.web.model;

import java.io.Serializable;

public class UserSession implements Serializable{
//	 private static final long serialVersionUID = 4138415982058325752L;
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountDTO account;
	 
	 public UserSession( AccountDTO account )
	  {
	    this.account = account;
	  }

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	 
	 

}
