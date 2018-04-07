package mx.com.proyect.puntoventa.web.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Se encarga de generar el script de internacionalizaci&oacute;n en lenguaje
 * JavaScript a partir de una lista de resource bundles.
 * 
 * @author iarces
 */
public class JavaScriptI18nRenderer
{
  private static Log log = LogFactory.getLog( JavaScriptI18nRenderer.class );
  private List<ResourceBundle> resourceBundles;

  /** Constructor default */
  public JavaScriptI18nRenderer() { }

  /**
   * Genera el script de internacionalizaci&oacute;n a partir de una lista de objetos
   * {@code ResourceBundle}.
   * @param script Objeto {@code StringBuilder} en el cual se almacena el script
   *          generado.
   * @throws UnsupportedEncodingException 
   */
  public void renderScript( StringBuilder script ) throws UnsupportedEncodingException
  {
    log.info( "renderScript [ " + script + " ]" );
    script.append( "var AppBundle = window.AppBundle || {};\n" );
    script.append( "AppBundle.i18n = AppBundle.i18n || {};\n" );
    for( ResourceBundle resourceBundle : resourceBundles )
    {
      Enumeration<String> keys = resourceBundle.getKeys();
      while( keys.hasMoreElements() )
      {
        String key = keys.nextElement();
        String value = resourceBundle.getString( key );
//        value = new String( value.getBytes( "UTF-8" ), "UTF-8" );
        script.append( String.format( "AppBundle.i18n['%s'] = '%s';\n", key, value ) );
      }
    }
  }

  public void setResourceBundles( List<ResourceBundle> resourceBundles )
  {
    this.resourceBundles = resourceBundles;
  }
}
