
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
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
		panelGraph.setListMeteoEvent(listMeteoEvent);
		panelStat.setState(stat);
		}

	public void update()
		{
		panelGraph.update();
		panelStat.update();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		panelStat = new JPanelStat();
		panelGraph = new JPanelGraph(titre);//listMeteoEvent);

		panelStat.setMaximumSize(new Dimension(180, 100));
		panelGraph.setMaximumSize(new Dimension(250, 100));

			// Layout : Specification
			{
			setLayout(new BorderLayout());
			}

		// JComponent : add
		add(panelGraph, BorderLayout.CENTER);
		add(panelStat, BorderLayout.WEST);
		}

	private void apparence()
		{
		setBorder(BorderFactory.createTitledBorder(titre));
		}

	private void control()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private String titre;

	// Tools
	private JPanelStat panelStat;
	private JPanelGraph panelGraph;

	}
