
package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import ch.hearc.meteo.imp.afficheur.real.controller.DataController;
import ch.hearc.meteo.imp.afficheur.real.controller.PageController;
import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelMain;

public class JFrameMain extends JFrame
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

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void addMenu(JPanelMain jPanelMain) {
		menuBar.add(jPanelMain);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle("Station Météo");

		setSize(500, 550);
		setResizable(false);
		setVisible(true);
		}


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private DataController dataController;
	private PageController pagecontroller;

	// Tools
	private JMenuBar menuBar;

	}
