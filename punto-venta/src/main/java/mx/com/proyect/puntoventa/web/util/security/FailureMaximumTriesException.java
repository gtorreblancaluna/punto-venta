package mx.com.proyect.puntoventa.web.util.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepcion que indica si el usuario se logeo incorrectamente y/o exedio el
 * numero de intentos (3)
 * 
 * @author drojas
 */
public class FailureMaximumTriesException extends AuthenticationException
{
	
  /**
   * 
   */
  private static final long serialVersionUID = -1040130337891500493L;

  public FailureMaximumTriesException( String msg )
  {
    super( msg );
  }
}
