package com.uni.mihai.radu.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyEditor;
import java.util.Random;

public class DesignPanel extends JPanel {
    public static final int W = 800, H = 600;
    private final MainFrame frame;
    JPanel props;
    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setLayout(null);
        JPanel components = new JPanel();
        components.add(new JButton("Button"));
        components.add(new JCheckBox());
        props = new JPanel();
        this.add(components);
        this.add(props);
    }
    public JPanel getProps(){
        return props;
    }
    public void addAtRandomLocation(JComponent comp) {
        Random r = new Random();
        int x = r.nextInt(W-1);
        int y = r.nextInt(H-1);
        int w = comp.getPreferredSize().width;
        int h = comp.getPreferredSize().height;
        comp.setBounds(x, y, w, h);
        comp.setToolTipText(comp.getClass().getName());
        this.add(comp);
        frame.repaint();
    }
}
