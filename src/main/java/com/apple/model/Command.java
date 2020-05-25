package com.apple.model;

import com.apple.algorithm.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public enum Command {

    DEPEND{
       @Override
       public void apply(ApplicationContext applicationContext, String line){
           Graph graph = applicationContext.getGraph();
           String[] toks = line.split("\\s+");
           String source = toks[1];
           //add vertices, if does not exist
           graph.addVertex(source);

           for(int i = 2; i < toks.length; i++){
               graph.addVertex(toks[i]);
               //add edge to graph
               graph.addEdge(source, toks[i]);
           }
        }
    }, INSTALL {
        @Override
        public void apply(ApplicationContext applicationContext, String inputLine) {
            List<String> installedComp = applicationContext.getInstalledComp();
            Graph graph = applicationContext.getGraph();
            String component = inputLine.split("\\s+")[1];
            //add in installedComp, if not present
            if (installedComp.contains(component)){
                System.out.println(component +" is already installed.");
            }else{
                //check its dependencies from graph
                List<String> dependencies = graph.getNeighbours(component);
                if(dependencies != null && dependencies.size() != 0){
                    for(String dependency : dependencies){
                        addToInstalledList(applicationContext, component);
                        System.out.println("Installing "+ dependency);
                    }
                }else{
                    System.out.println("Installing "+ component);
                    addToInstalledList(applicationContext, component);
                }

            }
        }
    }, REMOVE {
        @Override
        public void apply(ApplicationContext applicationContext, String inputLine) {
            List<String> installedComp = applicationContext.getInstalledComp();
            Graph graph = applicationContext.getGraph();
            String component = inputLine.split("\\s+")[1];
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
    }, LIST {
        @Override
        public void apply(ApplicationContext applicationContext, String inputLine) {
            for(String component: applicationContext.getInstalledComp()){
                System.out.println(component);
            }
        }
    }, END {
        @Override
        public void apply(ApplicationContext applicationContext, String inputLine) {

        }
    };
    public abstract void apply(ApplicationContext applicationContext, String inputLine);
    private static void addToInstalledList(ApplicationContext applicationContext, String component) {
        //add dependencies, if not present in installedComp
        List<String> installedComp = applicationContext.getInstalledComp();
        if(!installedComp.contains(component)){
            installedComp.add(component);
        }
        applicationContext.setInstalledComp(installedComp);
    }
}
