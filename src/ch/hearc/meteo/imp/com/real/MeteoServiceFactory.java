package ch.hearc.meteo.imp.com.real;

import ch.hearc.meteo.imp.com.logique.MeteoServiceCallback_I;
import ch.hearc.meteo.imp.com.real.com.ComConnexion;
import ch.hearc.meteo.imp.com.real.com.ComConnexions_I;
import ch.hearc.meteo.imp.com.real.com.ComOption;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceFactory_I;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;

public class MeteoServiceFactory implements MeteoServiceFactory_I {

	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MeteoServiceFactory()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * <pre>
	 * Example:
	 * 		Windows : namePort=COM1
	 * 		Linux	: ??
	 * 		Mac 	: ??
	 * </pre>
	 */
	@Override public MeteoService_I create(String portName)
		{
		ComConnexions_I comConnexion = new ComConnexion( portName, new ComOption());
		MeteoService_I meteoService = new MeteoService(comConnexion);
		comConnexion.setMeteoServiceCallback((MeteoServiceCallback_I) meteoService);
		
		return meteoService;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

}
