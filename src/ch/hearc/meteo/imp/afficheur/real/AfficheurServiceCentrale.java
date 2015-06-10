
package ch.hearc.meteo.imp.afficheur.real;


import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.JFrameCentrale;
import ch.hearc.meteo.imp.afficheur.real.vue.station.JPanelStation;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurServiceCentrale implements AfficheurService_I//eventuellement dans Datacontrolleur
	{

	//TODO : modifier la classe car c'est une copie de afficheurservicestation

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceCentrale(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		//Holder de données
		Station station = new Station(affichageOptions, meteoServiceRemote);

		//Afficheur de données
		jpanel = new JPanelStation();
		jpanel.setStation(station);

		//Fenêtre
		JFrameCentrale jframe = JFrameCentrale.getInstance();
		jframe.add(jpanel);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	//++ refresh dans les 3
	@Override public void printAltitude(MeteoEvent event)
		{
		station.printAltitude(event);
		jpanel.update();
		}

	@Override public void printTemperature(MeteoEvent event)
		{
		station.printTemperature(event);
		jpanel.update();
		}

	@Override public void printPression(MeteoEvent event)
		{
		station.printPression(event);
		jpanel.update();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		jpanel.updateMeteoServiceOptions(meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Station station;
	private JPanelStation jpanel;

	}
