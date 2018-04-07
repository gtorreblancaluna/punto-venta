package mx.com.proyect.puntoventa.web.i18n;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class BundleController extends AbstractController
{
  private static final Log LOG = LogFactory.getLog( BundleController.class );
  private String defaultBundle = "message";
  private Locale defaultLocale = new Locale( "es", "MX" );

  @Override
  protected ModelAndView handleRequestInternal( HttpServletRequest request, HttpServletResponse response )
      throws UnsupportedEncodingException, IOException
  {
    String bundles = ensureValidBundles( request.getParameter( "bundles" ) );
    Locale locale = ensureValidLocale( request.getParameter( "lang" ) );

    List<ResourceBundle> resourceBundles = findResourceBundles( bundles.split( "," ), locale );

    StringBuilder script = new StringBuilder();

    JavaScriptI18nRenderer renderer = new JavaScriptI18nRenderer();
    renderer.setResourceBundles( resourceBundles );
    renderer.renderScript( script );

    response.setContentType( "text/javascript; charset=utf-8" );
    response.getWriter().write( script.toString() );
    return null;
  }

  /**
   * Asegura que se procesara una lista v&aacute;lida de bundles.
   * 
   * @param bundles Lista separada por comas de bundles que se validar&aacute;.
   * @return Lista separada por comas de los bundles v&aacute;lidos o el bundle
   *         "default" s&iacute; la lista de bundles es inv&aacute;lida.
   */
  private String ensureValidBundles( String bundles )
  {
    if( bundles == null || "".equals( bundles.trim() ) )
    {
      return getDefaultBundle();
    }
    return bundles;
  }

  /**
   * Asegura que los bundles se cargaran utilizando un &Aacute;rea geogr&aacute;fica v&aacute;lida.
   * 
   * @param lang C&oacute;digo ISO del &Aacute;rea geogr&aacute;fica.
   * @return Un bojeto {@code Locale} equivalente a {@code lang} &oacute;
   *         {@code Locale.getDefault} s&iacute; lang no es un c&oacute;digo ISO v&aacute;lido.
   */
  private Locale ensureValidLocale( String lang )
  {
    if( lang != null && !"".equals( lang.trim() ) )
    {
      String[] langCodes = lang.split( "_" );
      if( langCodes != null && langCodes.length > 0 )
      {
        String language = langCodes[0];
        String country = (langCodes.length > 1) ? langCodes[1] : "";
        String variant = (langCodes.length > 2) ? langCodes[2] : "";

        return new Locale( language, country, variant );
      }
    }
    return defaultLocale;
  }

  /**
   * Busca el resource bundle correspondiente a cada posici&oacute;n del arreglo
   * {@code bundles} en el idioma correspondiente a {@code locale}.
   * 
   * @param bundles Arreglo que contiene los nombres de los bundles que se
   *          desean encontrar.
   * @param locale &Aacute;rea geogr&aacute;fica en cuyo idioma se buscan los resource
   *          bundles.
   * @return Una lista con los resource bundles encontrados y v&aacute;lidos.
   */
  private List<ResourceBundle> findResourceBundles( String[] bundles, Locale locale )
  {
    List<ResourceBundle> bundlesList = new ArrayList<ResourceBundle>();
    for( String bundleName : bundles )
    {
      ResourceBundle bundle = findResourceBundle( bundleName, locale );
      addIfValidBundle( bundle, bundlesList );
    }
    return bundlesList;
  }

  /**
   * Busca el resource bundle con nombre {@code bundleName} en el idioma
   * correspondiente a {@code locale}.
   * 
   * @param bundleName Nombre del bundle que se desea encontrar.
   * @param locale &Aacute;rea geogr&aacute;fica en cuyo idioma se busca el resource bundle.
   * @return El resource bundle encontrado o null si no se encuentra.
   */
  private ResourceBundle findResourceBundle( String bundleName, Locale locale )
  {
    ResourceBundle bundle = null;
    try
    {
      String bundleBaseName = String.format( "locale.%s", bundleName );
      bundle = ResourceBundle.getBundle( bundleBaseName, locale );
    }
    catch( MissingResourceException e )
    {
      LOG.error( String.format( "El locale '%s' no existe", bundleName ), e );
    }
    return bundle;
  }

  /**
   * Agrega el resource bundle {@code bundle} en la colecci&oacute;n {@code bundles} s&iacute;
   * es v&aacute;lido.
   * 
   * @param bundle El resource bundle a validar
   * @param bundles La colecci&oacute;n a la que se agregan los resource bundles.
   */
  private void addIfValidBundle( ResourceBundle bundle, Collection<ResourceBundle> bundles )
  {
    if( bundle != null )
    {
      bundles.add( bundle );
    }
  }

  public String getDefaultBundle()
  {
    return defaultBundle;
  }

  public void setDefaultBundle( String defaultBundle )
  {
    this.defaultBundle = defaultBundle;
  }
}
