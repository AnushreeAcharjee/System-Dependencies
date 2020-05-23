package com.apple;

import com.apple.algorithm.Graph;
import com.apple.controller.DependencyManagerImpl;
import com.apple.model.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public class Application {
    public static void main(String... arg){
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            ClassLoader classLoader = new Application().getClass().getClassLoader();
            File file = new File(classLoader.getResource("input.txt").getFile());
             fr=new FileReader(file);
            br=new BufferedReader(fr);

            String line = br.readLine();
            DependencyManagerImpl dependencyManager = new DependencyManagerImpl();
            while(line != null)
            {
                String[] toks = line.split("\\s+");
                String command = toks[0];
                int length = toks.length;
                if(command.equalsIgnoreCase(Command.DEPEND.name())){
                    String source = toks[1];
                    List<String> dependencies = new ArrayList<>();
                    for(int i = 2; i < length; i++){
                        dependencies.add(toks[i]);
                    }
                    dependencyManager.addDependency(source, dependencies);
                    System.out.println(line);
                }else if(command.equalsIgnoreCase(Command.INSTALL.name())){
                    System.out.println(line);
                    dependencyManager.installComponent(toks[1]);
                }else if(command.equalsIgnoreCase(Command.LIST.name())){
                    System.out.println(line);
                    dependencyManager.listInstalledComponents();
                }else if(command.equalsIgnoreCase(Command.REMOVE.name())){
                    System.out.println(line);
                    dependencyManager.removeComponent(toks[1]);
                }else if(command.equalsIgnoreCase(Command.END.name())){
                    System.out.println(line);
                }else{
                    System.out.println("Invalid command");
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
