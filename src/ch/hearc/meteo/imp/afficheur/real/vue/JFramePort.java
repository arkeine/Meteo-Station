
package ch.hearc.meteo.imp.afficheur.real.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class JFramePort extends JDialog
    {

    /*------------------------------------------------------------------*\
    |*                            Constructeurs                            *|
    \*------------------------------------------------------------------*/

    public JFramePort(Component component, Component parent)
        {
        this.component = component;
        this.parent = parent;
        geometry();
        control();
        appearance();
        }

    public JFramePort(Component component)
        {
        this(component, null);
        }

    /*------------------------------------------------------------------*\
    |*                            Methodes Public                            *|
    \*------------------------------------------------------------------*/

    /*------------------------------*\
    |*                Set                *|
    \*------------------------------*/

    /*------------------------------*\
    |*                Get                *|
    \*------------------------------*/

    public boolean isOkeyPressed()
        {
        return closeState;
        }

    public boolean isCancelPressed()
        {
        return !closeState;
        }

    public Component getComponent()
        {
        return this.component;
        }

    /*------------------------------------------------------------------*\
    |*                            Methodes Private                       *|
    \*------------------------------------------------------------------*/

    private void geometry()
        {
        // JComponent : Instanciation
        buttonStart = new JButton("Start");

            // Layout : Specification
            {
            BorderLayout borderLayout = new BorderLayout();
            setLayout(borderLayout);
            }

            {
            JPanel panelButton = new JPanel();
            panelButton.setLayout(new FlowLayout());

            panelButton.add(buttonStart);

            add(panelButton, BorderLayout.SOUTH);
            }

        if (component != null)
            {
            add(component, BorderLayout.CENTER);
            }
        }

    private void control()
        {
        buttonStart.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                    {
                    closeState = true;
                    dispose();
                    }
            });
        }

    private void appearance()
        {
        setSize(200, 300);
        setLocationRelativeTo(parent);
        setModal(true);
        }

    /*------------------------------------------------------------------*\
    |*                            Attributs Private                        *|
    \*------------------------------------------------------------------*/

    // Tools
    private Component component;
    private Component parent;
    private JButton buttonStart;
    private boolean closeState;

    }
