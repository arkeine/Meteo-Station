package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.vue.panel.JPanelPort;
import ch.hearc.meteo.imp.com.port.MeteoPortDetectionService;

public class JFramePort extends JFrame {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFramePort() {
		geometry();
		control();
		apparence();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry() {
		add(new JPanelPort(new MeteoPortDetectionService()));
	}

	private void control() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void apparence() {
		setTitle("Select a port to start a MeteoStation");
		// frame.setMinimumSize(new Dimension(200,300));
		// frame.setPreferredSize(new Dimension(200,300));
		setSize(200, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools

}
