
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelGraph extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraph(String title, String unit, Color color)
		{
		serie = new TimeSeries(title);
		serie.setMaximumItemAge(10); //Sur 10 secondes
		dataset = new TimeSeriesCollection();
		currentChart = ChartFactory.createTimeSeriesChart(title, labelX, unit, dataset, showLegend, createTooltip, createURL);
		XYPlot plot = currentChart.getXYPlot();
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesPaint(0, color);

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
		}

	public void update()
		{
		fill();
//		validate();
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
		//final TimeSeries serie = new TimeSeries("XXX");
		for(MeteoEvent meteoEvent:listMeteoEvent)
			{
			Date date = new Date(meteoEvent.getTime());
			serie.addOrUpdate(new Second(date), meteoEvent.getValue());
			}
		dataset.addSeries(serie);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private List<MeteoEvent> listMeteoEvent;

	// Tools
	private ChartPanel panelChart;
	private JFreeChart currentChart;
	private TimeSeries serie;
	private TimeSeriesCollection dataset;

	private String labelX = "Secondes";
	private boolean showLegend = false;
	private boolean createURL = false;
	private boolean createTooltip = false;

	}
