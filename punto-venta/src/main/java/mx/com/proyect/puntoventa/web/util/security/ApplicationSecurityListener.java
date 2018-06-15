package mx.com.proyect.puntoventa.web.util.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;


@SuppressWarnings("rawtypes")
public class ApplicationSecurityListener implements ApplicationListener
{
  private static final Log log = LogFactory.getLog( ApplicationSecurityListener.class );
  @Autowired
  private AccountService accountService;
 

  public void onApplicationEvent( ApplicationEvent event ) throws AuthenticationException
  {
    if( event instanceof AuthenticationFailureBadCredentialsEvent )
    {
      AuthenticationFailureBadCredentialsEvent authorizationFailureEvent = ( AuthenticationFailureBadCredentialsEvent ) event;
      log.fatal( "not authorized:" + authorizationFailureEvent );

      Authentication authentication = authorizationFailureEvent.getAuthentication();
      // User user = (User) authentication.getPrincipal();
      String username = ( String ) authentication.getPrincipal();

      AccountDTO account = accountService.getAccount( username );
      
      String messageException = "FAILURE_LOGIN";
      
      if(account != null) {
    	  
    	  
      }
      throw new FailureMaximumTriesException( messageException );
    }
     
    else if( event instanceof AuthenticationSuccessEvent )
    {
      AuthenticationSuccessEvent authenticationSuccessEvent = ( AuthenticationSuccessEvent ) event;

      Authentication authenticationA = authenticationSuccessEvent.getAuthentication();

      User user = (User) authenticationA.getPrincipal();     
    	  
    	 
     
    }
  }


  

}