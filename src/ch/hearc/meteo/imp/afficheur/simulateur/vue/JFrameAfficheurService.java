package ch.hearc.meteo.imp.afficheur.simulateur.vue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.simulateur.moo.AfficheurServiceMOO;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;

public class JFrameAfficheurService extends JFrame {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameAfficheurService(AfficheurServiceMOO afficheurServiceMOO) {
		this.afficheurServiceMOO = afficheurServiceMOO;

		geometry();
		control();
		apparence();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refresh() {
		panelRoot.update();
	}

	public void updateMeteoServiceOptions(
			MeteoServiceOptions meteoServiceOptions) {
		panelRoot.updateMeteoServiceOptions(meteoServiceOptions);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry() {
		panelRoot = new JPanelRoot(afficheurServiceMOO);
		add(panelRoot);
	}

	private void control() {
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					afficheurServiceMOO.getMeteoServiceRemote().disconnect();
					setVisible(false);
				} catch (RemoteException | MeteoServiceException e1) {
					e1.printStackTrace();
				}

			}

		});
	}

	private void apparence() {
		setTitle(afficheurServiceMOO.getTitre());

		setSize(500, 550);
		setResizable(false);
		setVisible(true);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private AfficheurServiceMOO afficheurServiceMOO;

	// Tools
	private JPanelRoot panelRoot;

}
