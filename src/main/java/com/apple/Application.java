package com.apple;

import com.apple.algorithm.Graph;
import com.apple.controller.CommandFactory;
import com.apple.model.ApplicationContext;
import com.apple.model.Command;

import java.io.*;
import java.util.ArrayList;

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
            ApplicationContext applicationContext = new ApplicationContext(new ArrayList<>(), new Graph());
            while(line != null)
            {
                System.out.println(line);
                String[] toks = line.split("\\s+");
                String command = toks[0];

                Command targetOperation = CommandFactory
                        .getCommand(command.toLowerCase())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Command"));
                targetOperation.apply(applicationContext, line);

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
