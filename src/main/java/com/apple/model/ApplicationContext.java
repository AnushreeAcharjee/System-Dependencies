package com.apple.model;

import com.apple.algorithm.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by anushree on 25 May, 2020
 */
@Data
@AllArgsConstructor
public class ApplicationContext {
    private List<String> installedComp;
    private Graph graph;
}
