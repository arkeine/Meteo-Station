
package ch.hearc.meteo.imp.afficheur.real.vue.panel;

import java.awt.BorderLayout;

import javax.swing.Box;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelControl;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelData;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelSlider;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelInfoStation extends JPanelMain
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelInfoStation(Station station)
		{
		super(station);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void update()
		{
		panelData.update();
		}

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
		this.panelControl = new JPanelControl(station);
		this.panelData = new JPanelData(station);
		this.panelSlider = new JPanelSlider(station);
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
