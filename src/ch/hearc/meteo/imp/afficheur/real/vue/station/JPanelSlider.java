
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.FlowLayout;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelMain_A;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelSlider extends JPanelMain_A
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSlider()
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

		initSliderValue();
		setStationControl();
		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		int value = (int)meteoServiceOptions.getTemperatureDT();
		jslider.setValue(value);
		setTitleBorder(value);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void init()
		{
		// Rien
		}

	@Override
	protected void geometry()
		{
		min = 1000;
		max = 10000;
		int defaultValue = 5000;

		jslider = new JSlider(min, max, defaultValue);

		border = BorderFactory.createTitledBorder("");
		setTitleBorder(defaultValue);
		jslider.setBorder(border);

		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(jslider);
		}

	@Override
	protected void apparence()
		{
		//setBackground(Color.ORANGE);

		jslider.setOrientation(SwingConstants.HORIZONTAL);
		}

	@Override
	protected void control()
		{
		// Rien
		}

	private void initSliderValue()
		{
		int value;

		try
			{
			value = (int)station.getMeteoServiceOptions().getTemperatureDT();
			}
		catch (RemoteException e)
			{
			value = (min + max) / 2;
			e.printStackTrace();
			}
		jslider = new JSlider(min, max, value);
		}

	private void setStationControl()
		{
		jslider.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					int value = jslider.getValue();

					try
						{
						MeteoServiceOptions meteoServiceOption = new MeteoServiceOptions(station.getMeteoServiceOptions());
						meteoServiceOption.setTemperatureDT(value);

						setTitleBorder(value);
						station.setMeteoServiceOptions(meteoServiceOption);
						}
					catch (RemoteException e1)
						{
						System.err.println("[JPanelSlider] remote update failed");
						e1.printStackTrace();
						}

					}
			});
		}

	private void setTitleBorder(int value)
		{
		border.setTitle("dt Temperature =" + value + " (ms)");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	int min;
	int max;
	private JSlider jslider;
	private TitledBorder border;

	}
