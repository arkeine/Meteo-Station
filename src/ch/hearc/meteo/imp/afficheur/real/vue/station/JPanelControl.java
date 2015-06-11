
package ch.hearc.meteo.imp.afficheur.real.vue.station;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import ch.hearc.meteo.imp.afficheur.real.data.Station;
import ch.hearc.meteo.imp.afficheur.real.vue.structure.JPanelMain_A;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class JPanelControl extends JPanelMain_A
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControl()
		{
		super();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void setStation(Station station)
		{
//		super.setStation(station);//== this.station = station;
		this.station = station;
		this.meteoServiceRemote = station.getMeteoServiceRemote();

		setServiceControl();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Protected						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void geometry()
		{
		boutonStart = new JButton("Start");
		boutonStop = new JButton("Stop");
		boutonPause = new JToggleButton("Pause");

		boutonPause.setToolTipText("Affichage only");

		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalGlue());
		boxH.add(boutonStart);
		boxH.add(Box.createHorizontalStrut(15));
		boxH.add(boutonStop);
		boxH.add(Box.createHorizontalGlue());
		boxH.add(boutonPause);
		boxH.add(Box.createHorizontalGlue());

		setLayout(new BorderLayout());
		add(boxH, BorderLayout.CENTER);
		}

	@Override
	protected void apparence()
		{
		// setBackground(Color.YELLOW);
		}

	@Override
	protected void control()
		{
		// Rien au début
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void setServiceControl()
		{
		boutonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					try
						{
						long altitudeDT = 2000;
						long pressionDT = 3000;
						long temperatureDT = 1000;
						MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(altitudeDT, pressionDT, temperatureDT);

						meteoServiceRemote.start(meteoServiceOptions);

						enableStop();
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}

					}
			});

		boutonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					try
						{
						meteoServiceRemote.stop();

						enableStart();
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}

					}
			});

		boutonPause.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					station.setPause(!station.isPause());
					}
			});

		threadEtatBouton = new Thread(new Runnable()
			{

				@Override
				public void run()
					{
					while(true)
						{
						try
							{
							sleep(POOLING_ETAT_BOUTON_DT);
							updateEtatBouton();
							}
						catch (RemoteException e)
							{
							e.printStackTrace();
							}
						}
					}
			});
		threadEtatBouton.start();
		}

	private void updateEtatBouton() throws RemoteException
		{
		if (meteoServiceRemote.isRunning())
			{
			enableStop();
			}
		else
			{
			enableStart();
			}
		}

	private void enableStart()
		{
		boutonStop.setEnabled(false);
		boutonStart.setEnabled(true);
		}

	private void enableStop()
		{
		boutonStart.setEnabled(false);
		boutonStop.setEnabled(true);
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static void sleep(long delayMS)
		{
		//System.out.println("sleep main: "+delayMS);
		try
			{
			Thread.sleep(delayMS);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private MeteoServiceWrapper_I meteoServiceRemote;
	private Station station;

	// Tools
	private JButton boutonStart;
	private JButton boutonStop;
	private JToggleButton boutonPause;

	private Thread threadEtatBouton;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final long POOLING_ETAT_BOUTON_DT = 2000;

	@Override
	protected void init()
		{
		// TODO Auto-generated method stub

		}

	}
