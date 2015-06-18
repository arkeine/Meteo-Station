
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelRoot_A;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelStation extends JPanelRoot_A implements ActionListener
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
		}

	@Override
	public void update()
		{
		panelData.update();
		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		//Rien
		}

	@Override
	public void actionPerformed(ActionEvent e)
		{
		panelData.actionPerformed(e);
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
		panelData = new JPanelData();
		panelControl = new JPanelControl();

			// Layout : Specification
			{
			setLayout(new BorderLayout());
			}

		// JComponent : add
		add(panelData, BorderLayout.CENTER);
		add(panelControl, BorderLayout.SOUTH);
		}

	@Override
	protected void apparence()
		{

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
	private JPanelData panelData;
	private JPanelControl panelControl;//Mettre slider dans control

	}
