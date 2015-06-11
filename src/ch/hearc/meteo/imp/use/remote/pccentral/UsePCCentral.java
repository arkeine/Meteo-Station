
package ch.hearc.meteo.imp.use.remote.pccentral;

import java.io.IOException;
import java.net.UnknownHostException;

import ch.hearc.meteo.imp.use.remote.PropertiesManager;

public class UsePCCentral
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		try
			{
			main();
			}
		catch (UnknownHostException e)
			{
			e.printStackTrace();
			}
		}

	public static void main() throws UnknownHostException
		{
		try
			{
			PropertiesManager.getInstance();
			}
		catch (IOException e)
			{
			e.printStackTrace();
			System.exit(-1);
			}
		new PCCentral().run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
