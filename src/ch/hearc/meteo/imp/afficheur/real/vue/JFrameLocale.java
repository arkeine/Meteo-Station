
package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.station.JPanelStation;

public class JFrameLocale extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameLocale(Station station)
		{
		this.station = station;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public JPanelStation getPanelStation()
		{
		return panelStation;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelStation = new JPanelStation();
		panelStation.setStation(station);
		add(panelStation);

		JMenuBar menuBar = new JMenuBar();
		menuWindow = new JMenu("Window");

		addWindowVisibleMenuItem("Show Altitude");
		addWindowVisibleMenuItem("Show Pression");
		addWindowVisibleMenuItem("Show Température");

		menuBar.add(menuWindow);
		setJMenuBar(menuBar);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle(station.getTitre());
		setSize(500, 550);
		}

	public void addWindowVisibleMenuItem(String title)
		{
		JMenuItem menuItem = new JMenuItem(title);
		menuItem.addActionListener(panelStation);
		menuWindow.add(menuItem);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	JPanelStation panelStation;
	Station station;
	JMenu menuWindow;
	}
