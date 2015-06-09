
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;

import javax.swing.Box;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelMain_A;

public class JPanelData extends JPanelMain_A
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelData()
		{
		super();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void setStation(Station station)
		{
		super.setStation(station);

		pannelPression.setStationEvent(station.getStatPression(), station.getListPression());
		pannelAltitude.setStationEvent(station.getStatAltitude(), station.getListAltitude());
		pannelTemperature.setStationEvent(station.getStatTemperature(), station.getListTemperature());
		}

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
		this.pannelPression = new JPanelEvent("Pression");
		this.pannelAltitude = new JPanelEvent("Altitude");
		this.pannelTemperature = new JPanelEvent("Temperature");
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
