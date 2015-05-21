
package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.controller.DataController;
import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelInfoStation;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JFrameMain extends JFrame implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain()
		{
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printPression(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		// TODO Auto-generated method stub

		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelRootStat.updateMeteoServiceOptions( meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelRootStat = new JPanelInfoStation(dataController);
		add(panelRootStat);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle(dataController.getTitre());

		setSize(500, 550);
		setResizable(false);
		setVisible(true);
		}


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private DataController dataController;

	// Tools
	private JPanelInfoStation panelRootStat;

	}
