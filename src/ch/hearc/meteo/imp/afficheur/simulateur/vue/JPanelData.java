
package ch.hearc.meteo.imp.afficheur.simulateur.vue;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.simulateur.moo.AfficheurServiceMOO;

public class JPanelData extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelData(AfficheurServiceMOO afficheurServiceMOO)
		{
		super();

		this.pannelPression = new JPanelEvent(afficheurServiceMOO.getStatPression(), afficheurServiceMOO.getListPression(), "Pression");
		this.pannelAltitude = new JPanelEvent(afficheurServiceMOO.getStatAltitude(), afficheurServiceMOO.getListAltitude(), "Altitude");
		this.pannelTemperature = new JPanelEvent(afficheurServiceMOO.getStatTemperature(), afficheurServiceMOO.getListTemperature(), "Temperature");

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void update()
		{
		pannelPression.update();
		pannelAltitude.update();
		pannelTemperature.update();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	protected void geometry()
		{
		Box boxV = Box.createVerticalBox();
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelPression);
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelAltitude);
		boxV.add(Box.createVerticalStrut(15));
		boxV.add(pannelTemperature);
		boxV.add(Box.createVerticalStrut(15));

		//boxV.setBackground(Color.BLUE);

		setLayout(new BorderLayout());
		add(boxV, BorderLayout.CENTER);
		}

	protected void apparence()
		{
		//setBackground(Color.GREEN);
		}

	protected void control()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private AfficheurServiceMOO afficheurServiceMOO;

	// Tools
	private JPanelEvent pannelPression;
	private JPanelEvent pannelAltitude;
	private JPanelEvent pannelTemperature;


	}
