package view.controls;


import controller.Controller;
import enums.GameState;

import javax.swing.*;

/**
 * Created by Cregnacht on 2015-03-23.
 */
//public class LevelSelectPanel
public class LevelSelectPanel extends javax.swing.JPanel
{

    public LevelSelectPanel()
    {
        initComponent();

        // CODE GOES HERE
        setVisible(false);
    }

    private void initComponent()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        titleLabel = new javax.swing.JLabel();
        level1Button = new javax.swing.JButton();
        level2Button = new javax.swing.JButton();
        level3Button = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(1000, 1000));
        setSize(new java.awt.Dimension(1000, 1000));
        setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("PHYZBLOK Level select");
        titleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 840;
        gridBagConstraints.ipady = 200;
        add(titleLabel, gridBagConstraints);

        level1Button.setText("level 1");
        level1Button.setMaximumSize(new java.awt.Dimension(98, 29));
        level1Button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                level1ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        add(level1Button, gridBagConstraints);

        level2Button.setText("Level 2");
        level2Button.setMaximumSize(new java.awt.Dimension(98, 29));
        level2Button.setPreferredSize(new java.awt.Dimension(98, 29));
        level2Button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                level2ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        add(level2Button, gridBagConstraints);

        level3Button.setText("Level 3");
        level3Button.setMaximumSize(new java.awt.Dimension(98, 29));
        level3Button.setPreferredSize(new java.awt.Dimension(98, 29));
        level3Button.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                level3ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        add(level3Button, gridBagConstraints);
    }// </editor-fold>





    private void level1ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        final Controller c = Controller.getInstance();
        c.setState(GameState.LOADING);
        c.setCurrentLevel(0);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller.getInstance().startCurrentLevel();
            }
        });
    }

    private void level2ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        final Controller c = Controller.getInstance();
        c.setState(GameState.LOADING);
        c.setCurrentLevel(1);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller.getInstance().startCurrentLevel();
            }
        });
    }

    private void level3ButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        final Controller c = Controller.getInstance();
        c.setState(GameState.LOADING);
        c.setCurrentLevel(2);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Controller.getInstance().startCurrentLevel();
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton level2Button;
    private javax.swing.JButton level1Button;
    private javax.swing.JButton level3Button;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration

}
