
package ch.hearc.meteo.imp.afficheur.real.vue.central;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.hearc.meteo.imp.afficheur.real.data.Station;

public class JLabelTabTitle extends JPanel
	{

	public JLabelTabTitle(final JTabbedPane tabbedPane, final JPanel panel, final Station station)
		{
		setLayout(new BorderLayout());
		setOpaque(false);

		JLabel titleLbl = new JLabel(station.getTitre());
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		JButton closeButton = new JButton("x");
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent e)
					{
					tabbedPane.remove(panel);
//					try
//						{
//						station.getMeteoServiceRemote().stop();
//						}
//					catch (RemoteException e1)
//						{
//						e1.printStackTrace();
//						}
					}
			});

		add(closeButton, BorderLayout.EAST);
		add(titleLbl, BorderLayout.CENTER);

		setMinimumSize(new Dimension(100, 50));
		}

	public JLabelTabTitle(JTabbedPane tabbedPane, JPanel panel, String title)
		{
		setLayout(new BorderLayout());
		setOpaque(false);

		JLabel titleLbl = new JLabel(title);
		titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		add(titleLbl, BorderLayout.CENTER);

		setMinimumSize(new Dimension(100, 50));
		}
	}
