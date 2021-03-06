
package ch.hearc.meteo.imp.afficheur.real;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.JFrameLocale;
import ch.hearc.meteo.imp.afficheur.real.vue.station.JPanelStation;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurServiceLocale implements AfficheurService_I//eventuellement dans Datacontrolleur
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurServiceLocale(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		//Holder de donn�es
		this.station = new Station(affichageOptions, meteoServiceRemote);
		showNewStation();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void showNewStation()
		{
		//Fen�tre
		JFrameLocale jframe = new JFrameLocale(station);
		jpanel = jframe.getPanelStation();
		jframe.pack();
		jframe.setVisible(true);
		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		station.printAltitude(event);
		jpanel.update();
		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		station.printTemperature(event);
		jpanel.update();
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		station.printPression(event);
		jpanel.update();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
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
