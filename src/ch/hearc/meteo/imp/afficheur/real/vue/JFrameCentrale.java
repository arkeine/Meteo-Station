
package ch.hearc.meteo.imp.afficheur.real.vue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JFrameCentrale extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameCentrale()
		{
		isInstanced = false;

		geometry();
		control();
		apparence();
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	//Constructor singleton
	public static JFrameCentrale getInstance()
		{
		//Constructeur appellé une seule fois
		if (!isInstanced)
			{
			jframeCentrale = new JFrameCentrale();
			isInstanced = true;
			}

		return jframeCentrale;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//TODO
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle("Station Météo");

		setSize(500, 550);
		setResizable(false);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private static boolean isInstanced;
	private static JFrameCentrale jframeCentrale;
	private JTabbedPane tabbedPane;

	}
