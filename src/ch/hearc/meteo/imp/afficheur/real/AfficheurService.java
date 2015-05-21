
package ch.hearc.meteo.imp.afficheur.real;


import ch.hearc.meteo.imp.afficheur.real.vue.JFrameMain;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurService implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public AfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		//Recup affichageOptions, meteoServiceRemote dans une classe controleur
		jFrameMain = new JFrameMain();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override public void printAltitude(MeteoEvent event)
		{
		//afficheurServiceMOO.printAltitude(event);
		jFrameMain.printPression(event);
		}

	@Override public void printTemperature(MeteoEvent event)
		{
		//TODO
		//afficheurServiceMOO.printTemperature(event);
		//jFrameMain.refresh();
		}

	@Override public void printPression(MeteoEvent event)
		{
		//TODO
		//afficheurServiceMOO.printPression(event);
		//jFrameMain.refresh();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		jFrameMain.updateMeteoServiceOptions(meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JFrameMain jFrameMain;

	}
