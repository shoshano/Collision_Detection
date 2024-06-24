package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyFrame extends JComponent implements ChangeListener, ActionListener {

    private int WIDTH;
    private int HEIGHT;
    private int BALLSPANELWIDTH;
    private int BALLSPANELHEIGHT;
    public int numberOfBalls;
    boolean symulationStart;
    boolean ballsSizeRandom;
    public Ball[] balls;

    private JPanel BallsPanel;
    private JPanel ButtonsPanel;
    private JLabel menuLabel;
    private JLabel nrOfBallsLabel;
    private JSlider nrOfBallsSlider;
    private JButton startButton;
    private JRadioButton sizeButton;
    JFrame gui;
    BallsPanel ballspanel;



    MyFrame(){
        WIDTH = 1200;
        HEIGHT = 700;
        BALLSPANELWIDTH = 220;
        symulationStart = false;
        ballsSizeRandom = false;


        gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Java Projekt");
        gui.setPreferredSize(new Dimension(WIDTH+5, HEIGHT+30));
        gui.setResizable(false);
        gui.getContentPane().add(this);

        //Panel
        ButtonsPanel = new JPanel();
        this.add(ButtonsPanel);

        ButtonsPanel.setLayout(new GridLayout(7, 1));
        ButtonsPanel.setBounds(0, 0, 220, 700);
        ButtonsPanel.setBorder(
                BorderFactory.createEtchedBorder(BevelBorder.LOWERED));

        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

        //menu label
        Font font =  new Font("Arial", Font.BOLD, 30);
        menuLabel = new JLabel("Menu");
        //menuLabel.setBounds(80, 20, 60, 30);
        menuLabel.setFont(font);

        //Slider
        nrOfBallsSlider = new JSlider(0, 10, 3);
        nrOfBallsSlider.setSize(160, 50);
        //nrOfBallsSlider.setBounds(20, 120, 160, 60 );
        nrOfBallsSlider.setPaintTicks(true);
        nrOfBallsSlider.setMinorTickSpacing(1);
        nrOfBallsSlider.setMajorTickSpacing(5);
        nrOfBallsSlider.setPaintLabels(true);
        nrOfBallsSlider.setFont(new Font("Arial", Font.PLAIN, 10));
        nrOfBallsSlider.addChangeListener(this);

        // Slider label
        nrOfBallsLabel = new JLabel("Liczba kulek:  " + nrOfBallsSlider.getValue());
        //nrOfBallsLabel.setBounds(50, 180, 100, 30);
        nrOfBallsLabel.setFont(new Font("Arial", Font.BOLD, 15));

        // Radiobutton
        sizeButton = new JRadioButton("losowy rozmiar kulek");
        //sizeButton.setBounds(30, 220, 120, 60 );
        nrOfBallsSlider.setFont(new Font("Arial", Font.BOLD, 10));

        //start button
        startButton = new JButton("Start");
        startButton.setSize(100, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setText("Start");
        startButton.addActionListener(this);



        ButtonsPanel.add(menuLabel);

        ButtonsPanel.add(nrOfBallsSlider);

        ButtonsPanel.add(nrOfBallsLabel);

        ButtonsPanel.add(sizeButton);

        ButtonsPanel.add(startButton);

        ButtonsPanel.validate();

        numberOfBalls = nrOfBallsSlider.getValue();

        ballspanel = new BallsPanel(numberOfBalls, sizeButton.isSelected());

    }

    @Override
    public void stateChanged(ChangeEvent e){
        nrOfBallsLabel.setText("Liczba kulek: " + nrOfBallsSlider.getValue());
        if(!symulationStart) numberOfBalls = nrOfBallsSlider.getValue();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(!symulationStart){
            ballspanel = new BallsPanel(numberOfBalls, sizeButton.isSelected());
            this.add(ballspanel);
            this.validate();
            ballspanel.start(60);
        }
        symulationStart = true;

    }

}
