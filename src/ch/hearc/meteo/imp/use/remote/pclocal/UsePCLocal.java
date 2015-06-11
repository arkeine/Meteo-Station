
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.io.IOException;

import ch.hearc.meteo.imp.use.remote.PropertiesManager;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class UsePCLocal
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
		catch (IOException e)
			{
			e.printStackTrace();
			}
		}

	public static void main() throws IOException
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

		String portName = "COM1";
		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);

		new PCLocal(meteoServiceOptions, portName).run();
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/


	/*------------------------------------------------------------------*\
	|*							Atribut Private						*|
	\*------------------------------------------------------------------*/

	}
