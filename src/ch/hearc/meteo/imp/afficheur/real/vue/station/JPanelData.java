
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelMain_A;

public class JPanelData extends JPanelMain_A implements ActionListener
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

		pannelPression.setStationEvent(station, station.getStatPression(), station.getListPression());
		pannelAltitude.setStationEvent(station, station.getStatAltitude(), station.getListAltitude());
		pannelTemperature.setStationEvent(station, station.getStatTemperature(), station.getListTemperature());
		}

	public void update()
		{
		pannelPression.update();
		pannelAltitude.update();
		pannelTemperature.update();
		}

	@Override
	public void actionPerformed(ActionEvent e)
		{
		String title = e.getActionCommand().split(" ")[1];
		System.out.println(title);
		if(pannelAltitude.getTitle().equals(title))
			{
			pannelAltitude.setVisible(!pannelAltitude.isVisible());
			}
		else if(pannelPression.getTitle().equals(title))
			{
			pannelPression.setVisible(!pannelPression.isVisible());
			}
		else if(pannelTemperature.getTitle().equals(title))
			{
			pannelTemperature.setVisible(!pannelTemperature.isVisible());
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void init()
		{
		//Rien
		}

	@Override
	protected void geometry()
		{
		// JComponent : Instanciation
		pannelPression = new JPanelEvent("Pression", "Hectopascal", new Color(147203));
		pannelAltitude = new JPanelEvent("Altitude", "Mètres", Color.BLUE);
		pannelTemperature = new JPanelEvent("Température", "Degrés", Color.RED);

			// Layout : Specification
			{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
		//Rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelEvent pannelPression;
	private JPanelEvent pannelAltitude;
	private JPanelEvent pannelTemperature;

	}
