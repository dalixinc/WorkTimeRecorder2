package com.dalixinc.gui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daleman ©2023
 *
 * The main rid Panel which holds line items of Projects which each have tasks
 */
public class ProjectsPanel extends JPanel {

    /** Contains a list of Project Panels, each of which encapsulates a Project object*/
    List<ProjectPanel> projectPanels= new ArrayList<>();

    public ProjectsPanel() {
        setLayout(new GridLayout(0, 1));
    }

    /** Returns the list of Projects this gui maintains */
    public List<ProjectPanel> getProjectPanels() {
        return projectPanels;
    }

    public void addProject(ProjectPanel project) {
        projectPanels.add(project);
        this.add(project);
    }

    public void deleteProject(ProjectPanel project) {
        // TODO - other way to access - projectPanels.remove(projectPanels.size() - 1);
        // This appears to do DIDDLY - this.getLayout().removeLayoutComponent(project);
        this.remove(project);
        System.out.println("Component Removed");
        projectPanels.remove(project);

    }
}