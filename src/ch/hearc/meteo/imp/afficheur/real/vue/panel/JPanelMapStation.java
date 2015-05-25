
package ch.hearc.meteo.imp.afficheur.real.vue.panel;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelControl;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelData;
import ch.hearc.meteo.imp.afficheur.real.vue.infostat.JPanelSlider;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelMapStation extends JPanel
	{


	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMapStation(Station station)
		{
		this.panelControl = new JPanelControl(station);
		this.panelData = new JPanelData(station);
		this.panelSlider=new JPanelSlider(station);

		geometry();
		control();
		apparence();
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
		panelSlider.updateMeteoServiceOptions( meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		Box boxV = Box.createVerticalBox();
		//boxV.add(Box.createVerticalStrut(15));
		boxV.add(panelData);
		//boxV.add(Box.createVerticalStrut(15));
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

	private void apparence()
		{
		// rien
		//setBackground(Color.ORANGE);
		}

	private void control()
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
