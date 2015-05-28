
package ch.hearc.meteo.imp.afficheur.real.controller;

import java.util.ArrayList;
import java.util.List;

import ch.hearc.meteo.imp.afficheur.real.vue.JFrameMain;
import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelMain;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class PageController implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PageController()
		{
		jframeMain = new JFrameMain();
		listJPanelMain = new ArrayList<JPanelMain>();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addPanel(JPanelMain jPanelMain) {
		jframeMain.addMenu(jPanelMain);
	}

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

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		//panelRootStat.updateMeteoServiceOptions( meteoServiceOptions);//remplacer par panel
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Public						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JFrameMain jframeMain;
	private List<JPanelMain> listJPanelMain;
	private int currentJPanelId;

	}
