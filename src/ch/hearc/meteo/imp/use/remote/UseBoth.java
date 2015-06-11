
package ch.hearc.meteo.imp.use.remote;

import ch.hearc.meteo.imp.use.remote.pccentral.UsePCCentral;
import ch.hearc.meteo.imp.use.remote.pclocal.UsePCLocal;



public class UseBoth
	{
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		if(args.length == 1)
			{
			if (args[0].equals("local"))
				{
				UsePCLocal.main(args);
				}
			if (args[0].equals("central"))
				{
				UsePCCentral.main(args);
				}
			}
		System.out.println("ARGS error");
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

