
package ch.hearc.meteo.imp.afficheur.real.vue.structure;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.data.Station;

public abstract class JPanelMain_A extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMain_A()
		{
		init();
		geometry();
		control();
		apparence();
		}

	protected abstract void init();
	protected abstract void geometry();
	protected abstract void control();
	protected abstract void apparence();

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setStation(Station station)//peut etre pas necessaire pour certains panels
		{
		this.station = station;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	protected Station station;

	}
