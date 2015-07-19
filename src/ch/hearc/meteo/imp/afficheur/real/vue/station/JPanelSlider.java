
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelSlider extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSlider(String title, int minValue, int maxValue)
		{
		this.title = title;

		geometry(minValue, maxValue);
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setStation(Station station)
		{
		this.station = station;
		control();
		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		int value = (int)meteoServiceOptions.getTemperatureDT();
		jslider.setValue(value);
		setTitleValue(value);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	protected void geometry(int minValue, int maxValue)
		{
		min = minValue;
		max = maxValue;
		int defaultValue = 1000;

		jslider = new JSlider(min, max, defaultValue);
		labelSlider = new JLabel();
		setTitleValue(defaultValue);

		setLayout(new BorderLayout());

		add(labelSlider, BorderLayout.NORTH);
		add(jslider, BorderLayout.CENTER);
		}

	protected void apparence()
		{
		jslider.setMinimumSize(new Dimension(0, 0));
		setMinimumSize(new Dimension(0, 0));
		jslider.setOrientation(SwingConstants.VERTICAL);
		}

	protected void control()
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
						if (title.equals("Pression"))
							{
							meteoServiceOption.setPressionDT(value);
							}
						else if (title.equals("Altitude"))
							{
							meteoServiceOption.setAltitudeDT(value);
							}
						else if (title.equals("Température"))
							{
							meteoServiceOption.setTemperatureDT(value);
							}
						else
							{
							System.out.println("No dt Setter");
							}

						setTitleValue(value);
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

	private void setTitleValue(int value)
		{
		labelSlider.setText("dt = " + value + " (ms)");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Input
	private String title;
	private Station station;

	// Tools
	private int min;
	private int max;

	private JSlider jslider;
	private JLabel labelSlider;

	}
