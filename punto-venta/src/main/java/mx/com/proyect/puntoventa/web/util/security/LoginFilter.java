package mx.com.proyect.puntoventa.web.util.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.acegisecurity.Authentication;
//import org.acegisecurity.context.SecurityContextImpl;
//import org.acegisecurity.userdetails.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import mx.com.proyect.puntoventa.web.model.AccountDTO;
import mx.com.proyect.puntoventa.web.service.AccountService;


public class LoginFilter extends OncePerRequestFilter
{
  private Log log = LogFactory.getLog( LoginFilter.class );
  
  @Autowired
  private AccountService accountService;


protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterchain )
      throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    SecurityContextImpl securityContextImpl = ( SecurityContextImpl ) session.getAttribute( "SPRING_SECURITY_CONTEXT" );
    AccountDTO userSession = ( AccountDTO ) session.getAttribute( "userSession" );

  

    if( userSession == null )
    {
      userSession = new AccountDTO();
      String agent_id = request.getParameter( "agent_id" );
      String redirect_url = request.getParameter( "redirect_url" );
      String cryptoInfoAgent = request.getParameter( "agent_cred" );
      String user_agent = request.getHeader( "user-agent" );
      log.debug( "agent_id: " + agent_id + " redirect_url: " + redirect_url + " cryptoInfoAgent: " + cryptoInfoAgent + " user_agent " + user_agent ); 
    
    }

    if( securityContextImpl != null )
    {
      Authentication authentication = securityContextImpl.getAuthentication();
      User user = ( User ) authentication.getPrincipal();
      if( authentication.isAuthenticated() && ( userSession == null || !userSession.getEmail().equals( user.getUsername() ) ) )
      {
    	  userSession = accountService.getAccount( user.getUsername() );   

      }
    }
    session.setAttribute( "userSession", userSession );

    filterchain.doFilter( request, response );
  }

}
