
package ch.hearc.meteo.imp.afficheur.real.controller;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelInfoStation;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class MainController implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private MainController()
		{
		dataController = new DataController();
		pageController = new PageController();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printPression(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static MainController getInstance()
		{
		if (!isInstanced)
			{
			mainController = new MainController();
			isInstanced = true;
			}
		return mainController;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void addStation(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
			//TODO: tester les options pour verifier le mode d'affichage et adapter la fenetre en fonction dans le pageController
			//voir eventuellement dans le afficheurService.
			Station station = new Station(affichageOptions, meteoServiceRemote);

			dataController.addStation(station);
			pageController.addPanel(new JPanelInfoStation(station));
			//pageController.add
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Tools
	//controlleurs
	DataController dataController;
	PageController pageController;

	//singleton
	static MainController mainController;
	static boolean isInstanced;

	}
