
package ch.hearc.meteo.imp.afficheur.real.vue.panel;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.controller.DataController;

public abstract class JPanelMain extends JPanel
	{


	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMain(DataController dataController)
		{
		this.dataController = dataController;

		init();
		geometry();
		control();
		apparence();
		}

	protected abstract void init();
	protected abstract void apparence();
	protected abstract void control();
	protected abstract void geometry();

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	protected DataController dataController;

	}
