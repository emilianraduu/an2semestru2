package com.uni.mihai.radu.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel = new ControlPanel(this);
    DesignPanel designPanel = new DesignPanel(this);
    public MainFrame() {
        super("Swing Designer");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.add(controlPanel, BorderLayout.NORTH);
        super.add(designPanel, BorderLayout.EAST);
        super.setVisible(true);
        pack();
    }
}