
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelGraph extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraph(String title)
		{
		this.title = title;
		this.labelX = title;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setListMeteoEvent(List<MeteoEvent> listMeteoEvent)
		{
		this.listMeteoEvent = listMeteoEvent;
		update();
		}

	public void update()
		{
		empty();
		fill();

		validate();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		panelChart = new ChartPanel(currentChart);

			// Layout : Specification
			{
			setLayout(new BorderLayout());
			}

		// JComponent : add
		add(panelChart, BorderLayout.CENTER);
		}

	private void apparence()
		{
		setBorder(BorderFactory.createTitledBorder("Graphe"));
		}

	private void control()
		{
		// rien
		}

	/*------------------------------*\
	|*			  geometry			*|
	\*------------------------------*/

	private void fill()
		{
		dataset = new XYSeriesCollection();
		XYSeries serie = new XYSeries("Pression");

		for(MeteoEvent meteoEvent:listMeteoEvent)
			{
			serie.add(meteoEvent.getTime(), meteoEvent.getValue());
			}
		((XYSeriesCollection)dataset).addSeries(serie);

		currentChart = ChartFactory.createXYLineChart(title, labelX, labelY, dataset, PlotOrientation.HORIZONTAL, showLegend, createTooltip, createURL);
		panelChart.setChart(currentChart);
		}

	private void empty()
		{
//		for(Component compo:this.getComponents())
//			{
//			this.remove(compo);
//			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private List<MeteoEvent> listMeteoEvent;
	private XYDataset dataset;

	// Tools
	private ChartPanel panelChart;
	private JFreeChart currentChart;

	private String title;
	private String labelX;
	private String labelY = "temps";
	private boolean showLegend = false;
	private boolean createURL = false;
	private boolean createTooltip = false;

	}
