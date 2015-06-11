
package ch.hearc.meteo.spec.afficheur;

import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;


public interface ImproveAfficheurService_I extends AfficheurService_I
	{

	public void setMeteoServiceWrapper(MeteoServiceWrapper_I meteoServiceWrapper);

	}
