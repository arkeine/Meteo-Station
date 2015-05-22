
package ch.hearc.meteo.imp.afficheur.real;


import ch.hearc.meteo.imp.afficheur.real.controller.MainController;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurService implements AfficheurService_I//eventuellement dans Datacontrolleur
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		//Recup affichageOptions, meteoServiceRemote dans une classe controleur
		mainController = MainController.getInstance();
		mainController.addStation(affichageOptions, meteoServiceRemote);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	//++ refresh dans les 3
	@Override public void printAltitude(MeteoEvent event)
		{
		mainController.printPression(event);
		}

	@Override public void printTemperature(MeteoEvent event)
		{
		mainController.printTemperature(event);
		}

	@Override public void printPression(MeteoEvent event)
		{
		mainController.printPression(event);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		mainController.updateMeteoServiceOptions(meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private MainController mainController;

	}
