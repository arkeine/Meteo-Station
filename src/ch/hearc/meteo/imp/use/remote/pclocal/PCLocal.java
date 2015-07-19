
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;

import ch.hearc.meteo.imp.afficheur.simulateur.AfficheurSimulateurFactory;
import ch.hearc.meteo.imp.com.simulateur.MeteoServiceSimulatorFactory;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.imp.use.remote.PropertiesManager;
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

	public PCLocal(MeteoServiceOptions meteoServiceOptions, String portCom) throws IOException
		{
		this.meteoServiceOptions = meteoServiceOptions;
		this.portCom = portCom;

		InetAddress ip = InetAddress.getByName(PropertiesManager.getInstance().getIpPcCentral());
		String id = RemoteAfficheurCreator.RMI_ID;
		int port = PropertiesManager.getInstance().getPortPcCentral();

		RmiURL rmi = new RmiURL(id, ip, port);

		this.rmiURLafficheurManager = rmi;
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
		catch (IOException | MeteoServiceException | NotBoundException e)
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

	private void server() throws MeteoServiceException, IOException
		{
		//Changement du timeout des sockets du RMI
		RMISocketFactory.setSocketFactory(new RMISocketFactory()
			{

				@Override
				public Socket createSocket(String host, int port) throws IOException
					{
					int timeout = PropertiesManager.getInstance().getRmiSocketTimeOut();
					Socket socket = new Socket();
					socket.setSoTimeout(timeout);
					socket.setSoLinger(false, 0);
					socket.connect(new InetSocketAddress(host, port), timeout);
					return socket;
					}

				@Override
				public ServerSocket createServerSocket(int port) throws IOException
					{
					return new ServerSocket(port);
					}
			});
		}

	/*------------------------------*\
	|*				client			*|
	\*------------------------------*/

	private void client() throws MeteoServiceException, NotBoundException, IOException
		{
		meteoService = new MeteoServiceSimulatorFactory().create(portCom);
		meteoService.connect();
		meteoService.start(meteoServiceOptions);
		MeteoServiceWrapper meteoServiceWrapper = new MeteoServiceWrapper(meteoService);

		//Creation du service d'affichage local
		AffichageOptions affichageOptionLocal = new AffichageOptions(3, "PCLocal-" + portCom);
		afficheurServiceLocal = (new AfficheurSimulateurFactory()).createOnLocalPC(affichageOptionLocal, meteoServiceWrapper);
		connectMeteoServiceLocal();

		//Partage
		InetAddress ip = InetAddress.getByName(PropertiesManager.getInstance().getIpPcLocal());
		String id = portCom;
		int port = PropertiesManager.getInstance().getPortPcLocal();

		RmiURL rmiUrlMeteoService = new RmiURL(id, ip, port);
		RmiTools.shareObject(meteoServiceWrapper, rmiUrlMeteoService);

		//Télécomande sur l'objet de creation distant
		AffichageOptions affichageOptionDistant = new AffichageOptions(3, "PCCentral-" + portCom);

		Thread t = new Thread(createRunnable(rmiUrlMeteoService, affichageOptionDistant));
		t.start();
		}

	private Runnable createRunnable(final RmiURL rmiUrlMeteoService, final AffichageOptions finalAffichageOptionDistant)
		{
		return new Runnable()
			{

				@Override
				public void run()
					{
					try
						{
						while(true)
							{
							try
								{
								if (!connected)
									{
									System.out.println("tentative de connection");
									int timeout = PropertiesManager.getInstance().getRmiConnectionTimeOut();
									int tryNb = PropertiesManager.getInstance().getRmiConnectionTry();

									RemoteAfficheurCreator_I remoteAfficheurCreator = (RemoteAfficheurCreator_I)RmiTools.connectionRemoteObjectBloquant(rmiURLafficheurManager, timeout, tryNb);

									RmiURL rmiAfficheurServiceDistant = remoteAfficheurCreator.createRemoteAfficheurService(finalAffichageOptionDistant, rmiUrlMeteoService);

									remoteAfficheurServicePcCentral = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObjectBloquant(rmiAfficheurServiceDistant,timeout, tryNb);

									connected = true;
									System.out.println("Passage en mode connecté");
									}
								}
							catch (IOException e)
								{
								System.out.println("echec de connection");
								}

							Thread.sleep(WAIT_TIME_CONNECTION_LOST);
							}
						}
					catch (InterruptedException e1)
						{
						//RIEN
						}
					}
			};
		}

	private void connectMeteoServiceLocal()
		{
		meteoService.addMeteoListener(new MeteoListener_I()
			{

				@Override
				public void temperaturePerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printTemperature(event);
					}

				@Override
				public void pressionPerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printPression(event);
					}

				@Override
				public void altitudePerformed(MeteoEvent event)
					{
					afficheurServiceLocal.printAltitude(event);
					}
			});

		meteoService.addMeteoListener(new MeteoListener_I()
			{

				@Override
				public void temperaturePerformed(MeteoEvent event)
					{
					try
						{
						if (connected)
							{
							remoteAfficheurServicePcCentral.printTemperature(event);
							}
						}
					catch (RemoteException e)
						{
						errorLost();
						}
					}

				@Override
				public void pressionPerformed(MeteoEvent event)
					{
					try
						{
						if (connected)
							{
							remoteAfficheurServicePcCentral.printPression(event);
							}
						}
					catch (RemoteException e)
						{
						errorLost();
						}
					}

				@Override
				public void altitudePerformed(MeteoEvent event)
					{
					try
						{
						if (connected)
							{
							remoteAfficheurServicePcCentral.printAltitude(event);
							}
						}
					catch (RemoteException e)
						{
						errorLost();
						}
					}
			});
		}

	private void errorLost()
		{
		connected = false;
		System.out.println("Passage en mode déconnecté");

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private MeteoServiceOptions meteoServiceOptions;
	private String portCom;
	private RmiURL rmiURLafficheurManager;

	// Tools
	private boolean connected;
	private MeteoService_I meteoService;
	private AfficheurServiceWrapper_I remoteAfficheurServicePcCentral;
	private AfficheurService_I afficheurServiceLocal;

	public static final int WAIT_TIME_CONNECTION_LOST = 10000;
	}
