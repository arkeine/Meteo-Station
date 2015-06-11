
package ch.hearc.meteo.imp.afficheur.simulateur;

import ch.hearc.meteo.imp.afficheur.simulateur.moo.AfficheurServiceMOO;
import ch.hearc.meteo.imp.afficheur.simulateur.vue.JFrameAfficheurService;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.ImproveAfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class ImprovedAfficheurServiceSimulateur implements ImproveAfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * n = #data to print
	 */
	public ImprovedAfficheurServiceSimulateur(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		//Holder de données
		afficheurServiceMOO = new AfficheurServiceMOO(affichageOptions, meteoServiceRemote);

		//Fenètre
		jFrameAfficheurService = new JFrameAfficheurService(afficheurServiceMOO);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printAltitude(MeteoEvent event)
		{
		afficheurServiceMOO.printAltitude(event);
		jFrameAfficheurService.refresh();
		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		afficheurServiceMOO.printTemperature(event);
		jFrameAfficheurService.refresh();
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		afficheurServiceMOO.printPression(event);
		jFrameAfficheurService.refresh();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		jFrameAfficheurService.updateMeteoServiceOptions(meteoServiceOptions);
		}

	@Override
	public void setMeteoServiceWrapper(MeteoServiceWrapper_I meteoServiceRemote)
		{
		afficheurServiceMOO.setMeteoServiceRemote(meteoServiceRemote);
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private AfficheurServiceMOO afficheurServiceMOO;
	private JFrameAfficheurService jFrameAfficheurService;
	}
