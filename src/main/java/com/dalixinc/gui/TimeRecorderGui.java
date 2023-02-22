package com.dalixinc.gui;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * @author Daleman © 2023
 *
 * The Main GUI class for displaying a Work Time Recorder in a Jframe, which can then be incorporated in WebSwing
 */
public class TimeRecorderGui {

    private static String FRAME_TITLE = "Work Time Recorder";
    private JFrame mainFrame;
    private JPanel parent;

    public TimeRecorderGui() {
        EventQueue.invokeLater(this::createAndShowGui);
    }

    private void createAndShowGui() {

        try {
            //UIManager.setLookAndFeel( new FlatDarkLaf());
            UIManager.setLookAndFeel( new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        mainFrame = new JFrame(FRAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        parent = (JPanel)mainFrame.getContentPane(); // TODO - This may be a brittle cast - consider setting content pane
        ProjectsPanel panProjects = new ProjectsPanel();

        JMenuBar menubar = createMenuBar(panProjects);
        menubar.setBorder(new BevelBorder(BevelBorder.RAISED));

        mainFrame.setJMenuBar(menubar);

        //Test Data
        panProjects.addProject(new ProjectPanel());
        panProjects.addProject(new ProjectPanel());
        ProjectPanel panny = new ProjectPanel();
        panny.setBackground(Color.GREEN);
        panProjects.addProject(panny);
        panProjects.addProject(new ProjectPanel());

        parent.add(panProjects);

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    /**
     * I need to come up with a neater genericised menu builder with "model" objects
     *
     * @param hostpanel a reference to a JPanel object where projects will be added and removed
     * @return The MenuBar for the frame to use
     */
    private JMenuBar createMenuBar(JPanel hostpanel) {

        ProjectsPanel pp = (ProjectsPanel)hostpanel; // TODO Another crappy cast to eliminate

        JMenuBar jmb = new JMenuBar();

        // Action Menu
        JMenu menActions = new JMenu("Actions");
        jmb.add(menActions);

        JMenuItem miAddProj = new JMenuItem("Add Project");
        miAddProj.addActionListener(e -> {
            pp.addProject(new ProjectPanel());
            System.out.println("Adding Project");
            mainFrame.revalidate();
            mainFrame.pack();
            mainFrame.repaint();

        });

        menActions.add(miAddProj);

        menActions.addSeparator();

        JMenuItem miDelProj = new JMenuItem("Delete Project");
        miDelProj.addActionListener(e -> {
            java.util.List<ProjectPanel> panels = pp.getProjectPanels();
            pp.deleteProject(panels.remove(panels.size() - 1));
            System.out.println("Deleting Project");
            mainFrame.revalidate();
            mainFrame.pack();
            mainFrame.repaint();

        });

        menActions.add(miDelProj);

        // Theme Menu
        JMenu menTheme = new JMenu("Theme");
        jmb.add(menTheme);

        ButtonGroup bg = new ButtonGroup(  );

        JRadioButton butWindows = new JRadioButton("Windows Theme");
        butWindows.addActionListener(e -> LNFSetter("windows"));
        bg.add(butWindows);

        JRadioButton butJava = new JRadioButton("Java Theme");
        butJava.addActionListener(e -> LNFSetter("java"));
        bg.add(butJava);

        JRadioButton butLight = new JRadioButton("Light Theme");
        butLight.addActionListener(e -> LNFSetter("light"));
        bg.add(butLight);
        butLight.setSelected(true);

        JRadioButton butDark = new JRadioButton("Dark Theme");
        butDark.addActionListener(e -> LNFSetter("dark"));
        bg.add(butDark);

        menTheme.add(butWindows);
        menTheme.add(butJava);
        menTheme.addSeparator();
        menTheme.add(butLight);
        menTheme.add(butDark);

        return jmb;
    }

    private void LNFSetter(String theme) {

        try {
            switch (theme) {
                case "windows":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                case "java":
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    break;
                case "light":
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case "dark":
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                default:
                    System.out.println("Unable to set that theme");
            }
            SwingUtilities.updateComponentTreeUI(mainFrame);
            mainFrame.revalidate();
        } catch (Exception e) {
            System.out.println("Freakout Error - unable to set LAF");
        }
    }

    public static void main(String[] args) {
        TimeRecorderGui MainGui= new TimeRecorderGui();
    }

}

