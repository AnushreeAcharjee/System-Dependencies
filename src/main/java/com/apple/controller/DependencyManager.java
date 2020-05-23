package com.apple.controller;

import java.util.List;

/**
 * Created by anushree on 23 May, 2020
 */
public interface DependencyManager {

     void addDependency(String source, List<String> dependencies);
     void installComponent(String component);
     void removeComponent(String component);
     void listInstalledComponents();
}
