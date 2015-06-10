
package ch.hearc.meteo.imp.afficheur.real.vue.structure;

import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public abstract class JPanelRoot_A extends JPanelMain_A//Surement interface
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	//public abstract void setStation(Station station);
	public abstract void update();
	public abstract void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions);

	}
