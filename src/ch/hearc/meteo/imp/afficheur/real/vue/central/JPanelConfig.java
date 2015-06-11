
package ch.hearc.meteo.imp.afficheur.real.vue.central;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JList;

import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelMain_A;

public class JPanelConfig extends JPanelMain_A
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelConfig()
		{
		super();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void init()
		{
		// Rien
		}

	@Override
	protected void geometry()
		{
		listPortsCom = new JList<String>();
		buttonRefresh = new JButton("Rafraichir");
		buttonShow = new JButton("Afficher");

			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);
			}


		add(listPortsCom, BorderLayout.CENTER);
		}

	@Override
	protected void control()
		{
		// TODO Auto-generated method stub
		}

	@Override
	protected void apparence()
		{
		// Rien
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	JList<String> listPortsCom;
	JButton buttonRefresh;
	JButton buttonShow;

	}
