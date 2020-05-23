package com.apple.controller;

import com.apple.algorithm.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public class DependencyManagerImpl implements DependencyManager {
    private  List<String> installedComp = new ArrayList<>();
    private Graph graph = new Graph();
    @Override
    public void addDependency(String source, List<String> dependencies) {
        //add vertices, if does not exist
        graph.addVertex(source);
        for(String dependency : dependencies){
            graph.addVertex(dependency);
            //add edge to graph
            graph.addEdge(source, dependency);
        }
    }

    @Override
    public void installComponent(String component) {
        //add in installedComp, if not present
        if (installedComp.contains(component)){
            System.out.println(component +" is already installed.");
        }else{
            //check its dependencies from graph
            List<String> dependencies = graph.getNeighbours(component);
            if(dependencies != null && dependencies.size() != 0){
                for(String dependency : dependencies){
                    addToInstalledList(component);
                    System.out.println("Installing "+ dependency);
                }
            }else{
                System.out.println("Installing "+ component);
                addToInstalledList(component);
            }

        }
    }

    private void addToInstalledList(String component) {
        //add dependencies, if not present in installedComp
        if(!installedComp.contains(component)){
            installedComp.add(component);
        }
    }

    @Override
    public void removeComponent(String component) {
        //check inEdgeCount
        int inEdgeCount = graph.getInEdgeCount(component);
        //if 0, means no one depends on it, remove
        if(inEdgeCount == 0){
            System.out.println("Removing "+ component);
            //remove from installed list
            installedComp.remove(component);
            //remove from graph
            graph.deleteVertex(component);
        }else{
            //else say still needed
            System.out.println(component + " is still needed.");
        }

    }

    @Override
    public void listInstalledComponents() {
        for(String component: installedComp){
            System.out.println(component);
        }
    }
}
