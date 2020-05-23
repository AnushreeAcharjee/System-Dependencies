package com.apple.algorithm;

import java.util.*;

public class Graph
{
    private Map<String, List<String>> map;
    private List<String> adj;  // Adjacency Lists

    private Map<String, Integer> inEdge = new HashMap<>();
    private Map<String, Integer> outEdge = new HashMap<>();

    public Graph() {
        map = new HashMap<>();
    }

    public void addEdge(String source, String dest) {
        //source depends on dest
        inEdge.put(dest, inEdge.getOrDefault(dest, 0) + 1);
        outEdge.put(source, outEdge.getOrDefault(source, 0) + 1);

        map.get(source).add(dest);
    }

    public void addVertex(String name){
        adj =  new ArrayList<>();
        if(!map.containsKey(name)){
            map.put(name, adj);
        }
    }

    public void deleteVertex(String key) {
        List<String> values = map.get(key);
        for(String val : values){
            if(inEdge.containsKey(val)){
                int cnt = inEdge.get(val);
                inEdge.put(val, cnt - 1);
            }
        }
        map.remove(key);
    }

    public int getInEdgeCount(String key){
        if(inEdge.containsKey(key)){
            return inEdge.get(key);
        }
        return 0;
    }

    public int getOutEdgeCount(String key){
        if(outEdge.containsKey(key)){
            return outEdge.get(key);
        }
        return 0;
    }

    public List<String> getNeighbours(String key) {
        return map.getOrDefault(key, null);
    }


}
