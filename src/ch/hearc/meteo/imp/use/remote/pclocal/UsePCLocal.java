
package ch.hearc.meteo.imp.use.remote.pclocal;

import ch.hearc.meteo.imp.afficheur.real.vue.JFramePort;
import ch.hearc.meteo.imp.afficheur.real.vue.config.JPanelPort;
import ch.hearc.meteo.imp.com.port.MeteoPortDetectionService;
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
		JPanelPort panelPort = new JPanelPort(new MeteoPortDetectionService());
		JFramePort framePort = new JFramePort(panelPort);
		framePort.setVisible(true);
		String portName = panelPort.getPortCom();

		if (portName != null && framePort.isOkeyPressed())
			{
			MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
			AffichageOptions affichageOption = new AffichageOptions(3,  "OPTION");

			PCLocal local = new PCLocal(meteoServiceOptions, portName, affichageOption, null);
			local.run();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
