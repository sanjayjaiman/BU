/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sanjay
 */
 public interface algorithmInterface {
    public boolean getNext(LinkedHashMap< String, Integer> pathMap, String shortestPathStr, String x,
                String end, directDistanceNode []adjacent, int index, boolean print);

    public void findPath(String start, String end , boolean print);
}
