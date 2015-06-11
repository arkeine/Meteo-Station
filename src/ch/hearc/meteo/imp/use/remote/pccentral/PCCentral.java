
package ch.hearc.meteo.imp.use.remote.pccentral;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreatorFactory;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.imp.use.remote.PropertiesManager;

public class PCCentral implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCCentral()
		{
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void run()
		{
		try
			{
			server();
			}
		catch (IOException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void server() throws IOException
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

		//Creation du gestionnaire de connexion
		new RemoteAfficheurCreatorFactory().create();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	public static final int RMI_TIMEOUT_MILIS = 10000;

	}
