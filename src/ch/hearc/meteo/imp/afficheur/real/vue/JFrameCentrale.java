
package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.central.JLabelTabTitle;

public class JFrameCentrale extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameCentrale()
		{
		isInstanced = false;

		geometry();
		control();
		apparence();
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	//Constructor singleton
	public static JFrameCentrale getInstance()
		{
		//Constructeur appellé une seule fois
		if (!isInstanced)
			{
			jframeCentrale = new JFrameCentrale();
			isInstanced = true;
			}

		return jframeCentrale;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addStationToTabbedPan(Station station, final JPanel panel)
		{
		tabbedPane.add(panel);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(panel), new JLabelTabTitle(tabbedPane, panel, station));
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle("Station Météo");

		setSize(500, 550);
		setResizable(true);
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private int nbStation;
	private static boolean isInstanced;
	private static JFrameCentrale jframeCentrale;
	private JTabbedPane tabbedPane;

	}
