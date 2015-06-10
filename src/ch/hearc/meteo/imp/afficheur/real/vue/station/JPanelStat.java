
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.data.Stat;
import ch.hearc.meteo.imp.afficheur.real.tools.MathTools;

public class JPanelStat extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStat()
		{
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setState(Stat stat)
		{
		this.stat = stat;
		}

	public void update()
		{
		labelAverageTen.setText("" + MathTools.arrondir(stat.getAverageTen()));
		labelAverage.setText("moy " + MathTools.arrondir(stat.getAverage()));
		labelMinimum.setText("min " + MathTools.arrondir(stat.getMinimum()));
		labelMaximum.setText("max " + MathTools.arrondir(stat.getMaximum()));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		labelAverageTen = new JLabel("-----");
		labelAverage = new JLabel("moy ------");
		labelMinimum = new JLabel("min ------");
		labelMaximum = new JLabel("max ------");

		JPanel panelCenter = new JPanel();
		//panelControl
		//panelVariation
		JPanel panelMainInfo = new JPanel();
		JPanel panelSecondaryInfos = new JPanel();

			// Layout : Specification
			{
			setLayout(new BorderLayout());
			panelCenter.setLayout(new BorderLayout());
			panelMainInfo.setLayout(new BorderLayout());
			panelSecondaryInfos.setLayout(new GridLayout(3, 0));
			}

		// JComponent : add
		panelMainInfo.add(new JLabel("Moyenne progressive"), BorderLayout.NORTH);
		panelMainInfo.add(labelAverageTen, BorderLayout.CENTER);

		panelSecondaryInfos.add(labelAverage);
		panelSecondaryInfos.add(labelMaximum);
		panelSecondaryInfos.add(labelMinimum);

		panelCenter.add(panelMainInfo, BorderLayout.CENTER);
		panelCenter.add(panelSecondaryInfos, BorderLayout.EAST);

		add(panelCenter, BorderLayout.CENTER);
		}

	private void apparence()
		{
		labelAverageTen.setFont(new Font("courier", Font.BOLD, 25));
		//		labelCurrent.setForeground(Color.RED);
		//		setBorder(BorderFactory.createTitledBorder("Statistique"));
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

	// Tools
	private JLabel labelAverageTen;
	private JLabel labelMinimum;
	private JLabel labelMaximum;
	private JLabel labelAverage;

	}
