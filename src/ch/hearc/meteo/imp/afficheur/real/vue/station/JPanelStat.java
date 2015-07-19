
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.data.Stat;
import ch.hearc.meteo.imp.afficheur.real.tools.MathTools;

public class JPanelStat extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStat(Color color)
		{
		this.color = color;

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
		labelAverage.setText("<html>Moyenne :<br>" + MathTools.arrondir(stat.getAverage()));
		labelMinimum.setText("<html>Minimum :<br>" + MathTools.arrondir(stat.getMinimum()));
		labelMaximum.setText("<html>Maximum :<br>" + MathTools.arrondir(stat.getMaximum()));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		labelTitleAverageTen = new JLabel("<html><b>Actuelle</b></html>");
		labelAverageTen = new JLabel(defaultValue);

		labelAverage = new JLabel("Moyenne :\n" + defaultValue);
		labelMinimum = new JLabel("Minimum :\n" + defaultValue);
		labelMaximum = new JLabel("Maximum :\n" + defaultValue);

		JPanel panelCenter = new JPanel();
		JPanel panelMainInfo = new JPanel();
		JPanel panelSecondaryInfos = new JPanel();


			// Layout : Specification
			{
			setLayout(new BorderLayout());
			panelCenter.setLayout(new BorderLayout(10, 0));
			panelMainInfo.setLayout(new BorderLayout());
			panelSecondaryInfos.setLayout(new BoxLayout(panelSecondaryInfos, BoxLayout.Y_AXIS));
			}

		// JComponent : add
		panelMainInfo.add(labelTitleAverageTen, BorderLayout.NORTH);
		panelMainInfo.add(labelAverageTen, BorderLayout.CENTER);

		panelSecondaryInfos.add(labelAverage);
		panelSecondaryInfos.add(Box.createVerticalStrut(10));
		panelSecondaryInfos.add(labelMaximum);
		panelSecondaryInfos.add(Box.createVerticalStrut(10));
		panelSecondaryInfos.add(labelMinimum);

		panelCenter.add(panelMainInfo, BorderLayout.CENTER);
		panelCenter.add(panelSecondaryInfos, BorderLayout.EAST);

		add(panelCenter, BorderLayout.CENTER);
		}

	private void apparence()
		{
		biggerFont = new Font(getFont().getFontName(), Font.BOLD, 30);
//		labelTitleAverageTen.setFont(biggerFont.deriveFont((float)10.0));

		labelAverageTen.setFont(biggerFont);
		labelAverageTen.setForeground(color);
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
	private Color color;

	// Tools
	private JLabel labelTitleAverageTen;
	private JLabel labelAverageTen;
	private JLabel labelMinimum;
	private JLabel labelMaximum;
	private JLabel labelAverage;

	private Font biggerFont;
	private final String defaultValue = "N/A";

	}
