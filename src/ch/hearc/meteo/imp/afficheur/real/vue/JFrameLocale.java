
package ch.hearc.meteo.imp.afficheur.real.vue;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class JFrameLocale extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameLocale()
		{
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public JMenu getMenuInterface()
	{
	return menuInterface;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setMenuConfig(List<String> portsCom)
	{
		menuConfig.setEnabled(!portsCom.isEmpty());
		for(String port:portsCom)
			{
			menuConfig.add(port);
			}
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		JMenuBar menuBar = new JMenuBar();

		menuConfig = new JMenu("Ports COM");
		menuConfig.setEnabled(false);

		menuInterface = new JMenu("Interfaces réseau");
		addInterfacesToMenu();

		menuBar.add(menuConfig);
		menuBar.add(menuInterface);
		setJMenuBar(menuBar);
		}

	private void addInterfacesToMenu()
		{
		try
			{
			Enumeration<NetworkInterface> nets;
			nets = NetworkInterface.getNetworkInterfaces();
			for(NetworkInterface netint:Collections.list(nets))
				{
				menuInterface.add(new JMenuItem(netint.getName() + " : " + netint.getDisplayName()));
				}
			}
		catch (SocketException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle("Station Météo");

		setSize(500, 550);
		//		setResizable(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	//private JTabbedPane tabbedPane;
	private JMenu menuConfig;
	private JMenu menuInterface;
	}
