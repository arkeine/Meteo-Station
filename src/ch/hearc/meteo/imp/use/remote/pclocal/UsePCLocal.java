
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

//		JPanelPort panelPort = new JPanelPort(new MeteoPortDetectionService());
//		JFramePort framePort = new JFramePort(panelPort);
//		framePort.setVisible(true);
//		String portName = panelPort.getPortCom();
//
//
//		if (portName != null && framePort.isOkeyPressed())
//			{
//			MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
//
//			new PCLocal(meteoServiceOptions, portName).run();
//			}
//		else
//			{
//			JOptionPane.showMessageDialog(null, "Aucune station � charger!\nFin du programme.", "Erreur", JOptionPane.WARNING_MESSAGE);
//			System.exit(0);
//			}


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
