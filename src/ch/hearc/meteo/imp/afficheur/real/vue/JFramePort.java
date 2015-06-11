
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
        buttonOk = new JButton("ok");
        buttonCancel = new JButton("cancel");

            // Layout : Specification
            {
            BorderLayout borderLayout = new BorderLayout();
            setLayout(borderLayout);
            }

            {
            JPanel panelButton = new JPanel();
            panelButton.setLayout(new FlowLayout());

            panelButton.add(buttonOk);
            panelButton.add(buttonCancel);

            add(panelButton, BorderLayout.SOUTH);
            }

        if (component != null)
            {
            add(component, BorderLayout.CENTER);
            }
        }

    private void control()
        {
        buttonOk.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                    {
                    closeState = true;
                    dispose();
                    }
            });
        buttonCancel.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                    {
                    closeState = false;
                    dispose();
                    }
            });
        }

    private void appearance()
        {
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setModal(true);
        }

    /*------------------------------------------------------------------*\
    |*                            Attributs Private                        *|
    \*------------------------------------------------------------------*/

    // Tools
    private Component component;
    private Component parent;
    private JButton buttonOk;
    private JButton buttonCancel;
    private boolean closeState;

    }
