
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
		this.title = title;
		this.labelY = unit;

		serie = new TimeSeries(title);
		serie.setMaximumItemAge(10);	//Sur 10 secondes
		dataset = new TimeSeriesCollection();
		currentChart = ChartFactory.createTimeSeriesChart(title, labelX, labelY, dataset, showLegend, createTooltip, createURL);
		XYPlot plot = currentChart.getXYPlot();
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesPaint(0, color);
//		renderer.setseries
		//		dataset.

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

		//		update();
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

	//	private void refreshChart()
	//		{
	//		panelChart.removeAll();
	//		panelChart.revalidate(); // This removes the old chart
	//		aChart = createChart();
	//		aChart.removeLegend();
	//		ChartPanel chartPanel = new ChartPanel(aChart);
	//		panelChart.setLayout(new BorderLayout());
	//		panelChart.add(chartPanel);
	//		panelChart.repaint(); // This method makes the new chart appear
	//		}

	/*------------------------------*\
	|*			  geometry			*|
	\*------------------------------*/

	private void fill()
		{

		//		MeteoEvent meteoEvent = listMeteoEvent.get(listMeteoEvent.size() - 1);
		for(MeteoEvent meteoEvent:listMeteoEvent)
			{
			Date date = new Date(meteoEvent.getTime());
			serie.addOrUpdate(new Second(date), meteoEvent.getValue());
			}
		dataset.addSeries(serie);
//		panelChart.revalidate();
		panelChart.repaint();

		//		((TimeSeriesCollection)dataset).addSeries(serie);

		//		currentChart = ChartFactory.createTimeSeriesChart(title, labelX, labelY, dataset, showLegend, createTooltip, createURL);
		//		panelChart.setChart(currentChart);
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

	// Tools
	private ChartPanel panelChart;
	private JFreeChart currentChart;
	private TimeSeries serie;
	private TimeSeriesCollection dataset;

	private String title;
	private String labelX = "temps";
	private String labelY;
	private boolean showLegend = false;
	private boolean createURL = false;
	private boolean createTooltip = false;

	}
