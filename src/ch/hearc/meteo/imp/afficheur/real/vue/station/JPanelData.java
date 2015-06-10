
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.GridLayout;

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

		}

	@Override
	protected void geometry()
		{
		// JComponent : Instanciation
		pannelPression = new JPanelEvent("Pression");
		pannelAltitude = new JPanelEvent("Altitude");
		pannelTemperature = new JPanelEvent("Temperature");

			// Layout : Specification
			{
			setLayout(new GridLayout(3, 0));
//			setBackground(Color.BLACK);
			}

		// JComponent : add
		add(pannelAltitude);
		add(pannelPression);
		add(pannelTemperature);
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
