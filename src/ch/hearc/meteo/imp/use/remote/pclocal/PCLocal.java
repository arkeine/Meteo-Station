
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.simulateur.AfficheurSimulateurFactory;
import ch.hearc.meteo.imp.com.simulateur.MeteoServiceSimulatorFactory;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoListener_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper;

import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class PCLocal implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCLocal(MeteoServiceOptions meteoServiceOptions, String portCom, AffichageOptions affichageOptions, RmiURL rmiURLafficheurManager)
		{
		this.meteoServiceOptions = meteoServiceOptions;
		this.portCom = portCom;
		this.affichageOptions = affichageOptions;
		this.rmiURLafficheurManager = rmiURLafficheurManager;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			server(); // avant
			}
		catch (Exception e)
			{
			System.err.println("[PCLocal :  run : server : failed");
			e.printStackTrace();
			}

		try
			{
			client(); // après
			}
		catch (RemoteException | MeteoServiceException | NotBoundException e)
			{
			System.err.println("[PCLocal :  run : client : failed");
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				server			*|
	\*------------------------------*/

	private void server() throws MeteoServiceException, RemoteException
		{

		}

	/*------------------------------*\
	|*				client			*|
	\*------------------------------*/

	private void client() throws RemoteException, MeteoServiceException, NotBoundException
		{
		//Creation du service d'acces à la station météo
		MeteoService_I meteoService = (new MeteoServiceSimulatorFactory()).create(portCom);
		meteoService.connect();
		meteoService.start(meteoServiceOptions);
		MeteoServiceWrapper meteoServiceWrapper = new MeteoServiceWrapper(meteoService);

		//Creation du service d'affichage local
		AffichageOptions affichageOptionLocal = new AffichageOptions(3, "PC local");
		final AfficheurService_I afficheurServiceLocal = (new AfficheurSimulateurFactory()).createOnLocalPC(affichageOptionLocal, meteoServiceWrapper);
		meteoService.addMeteoListener(new MeteoListener_I()
			{

				@Override public void temperaturePerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printTemperature(event);
					}

				@Override public void pressionPerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printPression(event);
					}

				@Override public void altitudePerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printAltitude(event);
					}
			});

		//Partage
		RmiURL rmiUrlMeteoService= new RmiURL(MeteoService_I.class.getName());
		RmiTools.shareObject(meteoServiceWrapper, rmiUrlMeteoService);

		//Télécomande sur l'objet de creation distant
		AffichageOptions affichageOptionDistant = new AffichageOptions(3, "PC central");
		RemoteAfficheurCreator_I remoteAfficheurCreator =(RemoteAfficheurCreator_I)RmiTools.connectionRemoteObjectBloquant(RemoteAfficheurCreator.RMI_URL);
		RmiURL rmiAfficheurServiceDistant = remoteAfficheurCreator.createRemoteAfficheurService(affichageOptionDistant, rmiUrlMeteoService);

		//Récupération et connexion de la télécommande de l'affichage distant
		final AfficheurServiceWrapper_I remoteAfficheurServicePcCentral = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObjectBloquant(rmiAfficheurServiceDistant);
		meteoService.addMeteoListener(new MeteoListener_I()
			{

				@Override public void temperaturePerformed(MeteoEvent event)
					{
					try
						{
						remoteAfficheurServicePcCentral.printTemperature(event);
						}
					catch (RemoteException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}

				@Override public void pressionPerformed(MeteoEvent event)
					{
					try
						{
						remoteAfficheurServicePcCentral.printPression(event);
						}
					catch (RemoteException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}

				@Override public void altitudePerformed(MeteoEvent event)
					{
					try
						{
						remoteAfficheurServicePcCentral.printAltitude(event);
						}
					catch (RemoteException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
			});
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private MeteoServiceOptions meteoServiceOptions;
	private String portCom;
	private AffichageOptions affichageOptions;
	private RmiURL rmiURLafficheurManager;

	// Tools

	}
