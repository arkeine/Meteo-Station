
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;

import javax.swing.Box;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelRoot_A;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelStation extends JPanelRoot_A//JPanelMain implements JPanelRoot_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStation()
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
		panelControl.setStation(station);
		panelData.setStation(station);
		panelSlider.setStation(station);
		}

	@Override
	public void update()
		{
		panelData.update();
		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelSlider.updateMeteoServiceOptions(meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void init()
		{
		this.panelControl = new JPanelControl();
		this.panelData = new JPanelData();
		this.panelSlider = new JPanelSlider();
		}

	@Override
	protected void geometry()
		{
		Box boxV = Box.createVerticalBox();
		boxV.add(panelData);
		boxV.add(panelSlider);
		boxV.add(panelControl);
		boxV.add(Box.createVerticalStrut(15));

		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalStrut(15));
		boxH.add(boxV);
		boxH.add(Box.createHorizontalStrut(15));

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	@Override
	protected void apparence()
		{
		// rien
		//setBackground(Color.ORANGE);
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
	private JPanelControl panelControl;
	private JPanelData panelData;
	private JPanelSlider panelSlider;

	}
