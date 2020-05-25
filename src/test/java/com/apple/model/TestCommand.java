package com.apple.model;

import com.apple.algorithm.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by dell on 25 May, 2020
 */
public class TestCommand {
    ApplicationContext applicationContext;

    @BeforeEach
    public void init(){
        applicationContext = new ApplicationContext(new ArrayList<>(), new Graph());
    }

    @Test
    public void testDependCommand(){
        String line = "DEPEND TELNET TCPIP NETCARD";
        Command.DEPEND.apply(applicationContext, line);

        Graph graph = applicationContext.getGraph();

        List<String> neighbours = graph.getNeighbours("TELNET");
        assertEquals("TCPIP", neighbours.get(0));
        assertEquals("NETCARD", neighbours.get(1));

        assertEquals(1, graph.getInEdgeCount("TCPIP"));
        assertEquals(1, graph.getInEdgeCount("NETCARD"));

        assertEquals(2, graph.getOutEdgeCount("TELNET"));
    }
    
}
