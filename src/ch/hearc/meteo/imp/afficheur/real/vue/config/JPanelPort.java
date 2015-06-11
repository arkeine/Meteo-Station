package ch.hearc.meteo.imp.afficheur.real.vue.config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import ch.hearc.meteo.imp.com.port.MeteoPortDetectionService;
import ch.hearc.meteo.imp.com.real.MeteoServiceFactory;
import ch.hearc.meteo.imp.use.local.UseComplet;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;

public class JPanelPort extends JPanel {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelPort(MeteoPortDetectionService mpds) {
		this.mpds = mpds;

		geometry();
		apparence();
		control();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public String getPortCom()
	{
		return listPort.getSelectedValue();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry() {
		this.setLayout(new BorderLayout());

		defListModel = new DefaultListModel<String>();

		refreshList();
		// List<String> listPortMeteo = mpds.findListPortSerie();
		// for (String s : listPortMeteo)
		// defListModel.addElement(s);

		listPort = new JList<String>(defListModel);

		listPort.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listPort
				.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		refreshButton = new JButton("UPDATE");

		this.add(listPort, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		controlPanel.add(refreshButton);

		this.add(controlPanel, BorderLayout.SOUTH);

		TitledBorder border = BorderFactory
				.createTitledBorder("Available ports for start");

		this.setBorder(border);

	}

	private void apparence() {

	}

	private void control() {
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelPort.this.refreshList();
			}

		});
	}

	private void refreshList() {
		defListModel.clear();
		List<String> listPortMeteo = mpds.findListPortMeteo();

		for (String s : listPortMeteo)
			{
				defListModel.addElement(s);
				}

	}

	private void start() {
		if (!listPort.isSelectionEmpty()) {
			final String portName = listPort.getSelectedValue();

			// startMeteoService et affichage
			System.out.println(portName);
			new Thread(new Runnable() {

				@Override
				public void run() {
					MeteoService_I meteoService = (new MeteoServiceFactory())
							.create(portName);
					try {
						UseComplet.use(meteoService);
					} catch (MeteoServiceException e) {
						e.printStackTrace();
					}

				}
			}).start();


			// réactualiser la liste des ports disponibles
//			refreshList();
			defListModel.removeElement(portName);
		}

	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private MeteoPortDetectionService mpds;

	// Tools
	private JList<String> listPort;
	private JButton refreshButton;

	DefaultListModel<String> defListModel;
}
