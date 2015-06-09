
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.data.Stat;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelEvent extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelEvent(String titre)
		{
		this.titre = titre;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setStationEvent(Stat stat, List<MeteoEvent> listMeteoEvent)
	{
		this.stat = stat;
		panelStat.setState(stat);
		this.listMeteoEvent = listMeteoEvent;
		boxSerieTemporelle.setListMeteoEvent(listMeteoEvent);
	}

	public void update()
		{
		boxSerieTemporelle.update();
		panelStat.update();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelStat = new JPanelStat();
		boxSerieTemporelle = new BoxSerieTemporelle();//listMeteoEvent);

		panelStat.setMaximumSize(new Dimension(180, 100));
		boxSerieTemporelle.setMaximumSize(new Dimension(250, 100));

		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalStrut(15));
		boxH.add(panelStat);
		boxH.add(Box.createHorizontalStrut(15));
		boxH.add(boxSerieTemporelle);
		boxH.add(Box.createHorizontalStrut(15));

		Box boxV = Box.createVerticalBox();
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(boxH);
		boxV.add(Box.createVerticalStrut(15));

		setLayout(new BorderLayout());
		add(boxV, BorderLayout.CENTER);
		setBorder(BorderFactory.createTitledBorder(titre));
		}

	private void apparence()
		{
		// rien
		}

	private void control()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private Stat stat;

	private List<MeteoEvent> listMeteoEvent;
	private String titre;

	// Tools
	private JPanelStat panelStat;
	private BoxSerieTemporelle boxSerieTemporelle;

	}
