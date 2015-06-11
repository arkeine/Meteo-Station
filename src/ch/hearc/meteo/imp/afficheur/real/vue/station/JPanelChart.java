package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class JPanelChart extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelChart()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		chartBarDataPanel = new ChartPanel(createChart());

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(chartBarDataPanel);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	private JFreeChart createChart() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i <= 20; i++) {
			dataset.addValue(i, "Homme", new Integer(i));
		}
		for (int i = 0; i <= 20; i++) {
			dataset.addValue(i+1*i, "Femme", new Integer(i));
		}

		JFreeChart chartBarData = ChartFactory.createBarChart("Notes du dernier exam", "Note", "Nombre", dataset, PlotOrientation.VERTICAL, true, true, false);
		return chartBarData;
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	ChartPanel chartBarDataPanel;

	}

