package com.uni.mihai.radu.view;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JButton save = new JButton("Save");
    private final JButton load = new JButton("Load");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        add(classNameLabel); add(classNameField); add(textLabel); add(textField); add(createButton); add(save); add(load);
        createButton.addActionListener(e -> {
            JComponent comp = createDynamicComponent(classNameField.getText());
            setComponentText(comp, textField.getText());
            frame.designPanel.addAtRandomLocation(comp);
        });
        save.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.showSaveDialog(this);
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(fc.getSelectedFile()+".xml"));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            XMLEncoder xmlEncoder = new XMLEncoder(bos);
            System.out.println(frame.designPanel.getProps().getComponents().getClass());
            xmlEncoder.writeObject(frame.designPanel.getComponents());
            xmlEncoder.close();
        });
        load.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fc.showOpenDialog(this);

        });
    }

    private JComponent createDynamicComponent(String className) {
        JComponent comp;
        Class clazz;
        try {
            clazz = Class.forName(className);
            comp = (JComponent) clazz.newInstance();
            return comp;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void setComponentText(JComponent comp, String text) {
        Method m = null;
        try {
            m = comp.getClass().getMethod("setText", String.class);
            if(m!=null){
                m.invoke(comp,text);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}