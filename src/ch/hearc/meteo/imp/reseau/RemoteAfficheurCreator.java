
package ch.hearc.meteo.imp.reseau;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.simulateur.AfficheurSimulateurFactory;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
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

	private RemoteAfficheurCreator() throws RemoteException
		{
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
		//Télécomande sur les objet distant
		MeteoServiceWrapper_I remoteMeteoServicePCLocal = (MeteoServiceWrapper_I)RmiTools.connectionRemoteObjectBloquant(meteoServiceRmiURL);

		//TODO CHANGER LA SIGNATURE DE L'AFFICHAGE !

		//Creation de l'affichage central
		AfficheurService_I afficheurPCCentral = createAfficheurService(affichageOptions, remoteMeteoServicePCLocal);

		//Partage de l'affichage central
		AfficheurServiceWrapper afficheurServiceWrapper = new AfficheurServiceWrapper(afficheurPCCentral);
		RmiURL afficheurServicermiURL = rmiUrl();
		RmiTools.shareObject(afficheurServiceWrapper, afficheurServicermiURL);

		return afficheurServicermiURL; // Retourner le RMI-ID pour une connection distante sur le serveur d'affichage
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static synchronized RemoteAfficheurCreator_I getInstance() throws RemoteException
		{
		if (INSTANCE == null)
			{
			INSTANCE = new RemoteAfficheurCreator();
			}

		return INSTANCE;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private AfficheurService_I createAfficheurService(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		AfficheurSimulateurFactory factory = new AfficheurSimulateurFactory();
		return factory.createOnCentralPC(affichageOptions, meteoServiceRemote);
		}

	private void server() throws RemoteException
		{
		RmiTools.shareObject(this,  RemoteAfficheurCreator.RMI_URL);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/**
	 * Thread Safe
	 */
	private static RmiURL rmiUrl()
		{
		String id = IdTools.createID(PREFIXE);

		return new RmiURL(id);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	// Tools

	private static RemoteAfficheurCreator_I INSTANCE = null;

	// Tools final
	private static final String PREFIXE = "AFFICHEUR_SERVICE";

	public static final String RMI_ID = PREFIXE;

	//rmi
	public static final String RMI_OBJET_ID = RemoteAfficheurCreator_I.class.getName();
	public static final int RMI_LOCAL_PORT = RmiTools.PORT_RMI_DEFAUT;
	public static final RmiURL RMI_URL = new RmiURL(RMI_OBJET_ID, RMI_LOCAL_PORT);
	}
