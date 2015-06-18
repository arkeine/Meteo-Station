
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ch.hearc.meteo.imp.afficheur.real.data.Stat;
import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelEvent extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelEvent(String title, String unit, Color color)
		{
		this.title = title;
		this.unit = unit;
		this.color = color;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setStationEvent(Station station, Stat stat, List<MeteoEvent> listMeteoEvent)
		{
		panelGraph.setListMeteoEvent(listMeteoEvent);
		panelStat.setState(stat);
		panelSlider.setStation(station);
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
		panelStat = new JPanelStat(color);
		panelGraph = new JPanelGraph(title, unit, color);
		panelSlider = new JPanelSlider(title, 500, 4000);

		panelStat.setMaximumSize(new Dimension(180, 100));
		panelGraph.setMaximumSize(new Dimension(250, 100));

			// Layout : Specification
			{
			setLayout(new BorderLayout());
			}

		// JComponent : add
		add(panelGraph, BorderLayout.CENTER);
		add(panelStat, BorderLayout.WEST);
		add(panelSlider, BorderLayout.EAST);
		}

	private void apparence()
		{
		TitledBorder border = BorderFactory.createTitledBorder("<html><b>"+title+"</b></html>");
		border.setTitleColor(color);
		border.setTitleFont(getFont().deriveFont((float)20));
		setBorder(border);
		}

	private void control()
		{
		// rien
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getTitle()
		{
		return title;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelStat panelStat;
	private JPanelGraph panelGraph;
	private JPanelSlider panelSlider;

	String title;
	String unit;
	Color color;

	}
