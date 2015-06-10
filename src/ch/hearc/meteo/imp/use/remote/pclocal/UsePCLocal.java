
package ch.hearc.meteo.imp.use.remote.pclocal;

import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class UsePCLocal
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		String portName = "COM1";
		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
		AffichageOptions affichageOption = new AffichageOptions(3,  "OPTION");

		PCLocal local = new PCLocal(meteoServiceOptions, portName, affichageOption, null);
		local.run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
