
package ch.hearc.meteo.imp.reseau;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import ch.hearc.meteo.imp.afficheur.real.AfficheurServiceFactory;
import ch.hearc.meteo.imp.use.remote.PropertiesManager;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurFactory_I;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.afficheur.ImproveAfficheurService_I;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

import com.bilat.tools.reseau.rmi.IdTools;
import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

/**
 * <pre>
 * One instance only (Singleton) on PC-Central
 * RMI-Shared
 * </pre>
 */
public class RemoteAfficheurCreator implements RemoteAfficheurCreator_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private RemoteAfficheurCreator() throws IOException
		{
		mapPcLocalDisplayRmi = new HashMap<String, RmiURL>();
		mapPcLocalDisplayAfficheur = new HashMap<String, AfficheurService_I>();
		server();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Remote use
	 * @throws NotBoundException
	 */
	@Override
	public RmiURL createRemoteAfficheurService(AffichageOptions affichageOptions, RmiURL meteoServiceRmiURL) throws RemoteException
		{
		System.out.println("-----------------------------------------------------------------------");
		System.out.println(meteoServiceRmiURL.getServeurHostAdress());
		System.out.println("-----------------------------------------------------------------------");

		//Clé de l'affichage pour un port com donné
		String key = affichageOptions.getTitre();

		try
			{
			//Télécomande sur les objet distant
			MeteoServiceWrapper_I remoteMeteoServicePCLocal = (MeteoServiceWrapper_I)RmiTools.connectionRemoteObjectBloquant(meteoServiceRmiURL, PropertiesManager.getInstance().getRmiConnectionTimeOut(), PropertiesManager.getInstance().getRmiConnectionTry());

			//Vérification de présence dans l'historique d'affichage
			if (mapPcLocalDisplayRmi.containsKey(key))
				{

				//Test si on a affaire a un objet "amélioré" ou un standard
				if (mapPcLocalDisplayAfficheur.get(key) instanceof ImproveAfficheurService_I)
					{
					System.out.println("PASS IMPROVE");

					System.out.println(remoteMeteoServicePCLocal.isRunning());
					ImproveAfficheurService_I improveAfficheurService = (ImproveAfficheurService_I)mapPcLocalDisplayAfficheur.get(key);
					improveAfficheurService.setMeteoServiceWrapper(remoteMeteoServicePCLocal);
					}
				else
					{
					System.out.println("PASS STANDARD");
					createNewDisplay(affichageOptions, remoteMeteoServicePCLocal, key);

					}
				}
			else
				{
				System.out.println("CREATE NEW");
				createNewDisplay(affichageOptions, remoteMeteoServicePCLocal, key);
				}
			}
		catch (IOException e)
			{
			throw new RemoteException("NOT RELATED ERROR", e);
			}

		return mapPcLocalDisplayRmi.get(key);
		}

	private void createNewDisplay(AffichageOptions affichageOptions, MeteoServiceWrapper_I remoteMeteoServicePCLocal, String key) throws IOException
		{
		//Creation de l'affichage central
		AfficheurService_I afficheurPCCentral = createAfficheurService(affichageOptions, remoteMeteoServicePCLocal);

		//Partage de l'affichage central
		AfficheurServiceWrapper afficheurServiceWrapper = new AfficheurServiceWrapper(afficheurPCCentral);
		RmiURL afficheurServicermiURL = rmiUrl();
		RmiTools.shareObject(afficheurServiceWrapper, afficheurServicermiURL);

		//Ajout du nouvel affichage dans la map
		mapPcLocalDisplayRmi.put(key, afficheurServicermiURL);
		mapPcLocalDisplayAfficheur.put(key, afficheurPCCentral);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized RemoteAfficheurCreator_I getInstance() throws RemoteException
		{
		if (INSTANCE == null)
			{
			try
				{
				INSTANCE = new RemoteAfficheurCreator();
				}
			catch (IOException e)
				{
				throw new RemoteException("NOT RELATED ERROR", e);
				}
			}

		return INSTANCE;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private AfficheurService_I createAfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		AfficheurFactory_I factory = new AfficheurServiceFactory();
		//AfficheurFactory_I factory = new AfficheurSimulateurFactory(); //Factory des spec de base
		return factory.createOnCentralPC(affichageOptions, meteoServiceRemote);
		}

	private void server() throws IOException
		{
		String id = RMI_ID;
		InetAddress ip = InetAddress.getByName(PropertiesManager.getInstance().getIpPcCentral());
		int port = PropertiesManager.getInstance().getPortPcCentral();

		rmiURLafficheurManager = new RmiURL(id, ip, port);
		RmiTools.shareObject(this, rmiURLafficheurManager);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/**
	 * Thread Safe
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	private static RmiURL rmiUrl() throws IOException
		{
		String id = IdTools.createID(PREFIXE);
		InetAddress ip = InetAddress.getByName(PropertiesManager.getInstance().getIpPcCentral());
		int port = PropertiesManager.getInstance().getPortPcCentral();

		return new RmiURL(id, ip, port);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private RmiURL rmiURLafficheurManager;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	// Tools
	private Map<String, RmiURL> mapPcLocalDisplayRmi;
	private Map<String, AfficheurService_I> mapPcLocalDisplayAfficheur;
	private static RemoteAfficheurCreator_I INSTANCE = null;

	// Tools final
	private static final String PREFIXE = "AFFICHEUR_SERVICE";
	public static final String RMI_ID = PREFIXE;

	}
