
package ch.hearc.meteo.imp.afficheur.real.vue.infostat;

import java.awt.BorderLayout;

import javax.swing.Box;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelMain;

public class JPanelData extends JPanelMain
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelData(Station station)
		{
		super(station);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void update()
		{
		pannelPression.update();
		pannelAltitude.update();
		pannelTemperature.update();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void init()
		{
		// Tools
		this.pannelPression = new JPanelEvent(station.getStatPression(), station.getListPression(), "Pression");
		this.pannelAltitude = new JPanelEvent(station.getStatAltitude(), station.getListAltitude(), "Altitude");
		this.pannelTemperature = new JPanelEvent(station.getStatTemperature(), station.getListTemperature(), "Temperature");
		}

	@Override
	protected void geometry()
		{
		Box boxV = Box.createVerticalBox();
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelPression);
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelAltitude);
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelTemperature);
		boxV.add(Box.createVerticalStrut(15));

		//boxV.setBackground(Color.BLUE);

		setLayout(new BorderLayout());
		add(boxV, BorderLayout.CENTER);
		}

	@Override
	protected void apparence()
		{
		//setBackground(Color.GREEN);
		}

	@Override
	protected void control()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelEvent pannelPression;
	private JPanelEvent pannelAltitude;
	private JPanelEvent pannelTemperature;

	}
