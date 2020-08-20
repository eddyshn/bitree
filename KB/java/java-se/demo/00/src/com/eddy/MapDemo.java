package com.eddy;

import jdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public void Test() {
        Map<String, Integer> map = new HashMap<>();

        map.put("eddy", 38);
        map.put("eddy2", 37);

        System.out.println("--------------------contains---------------------------");
        if (map.containsKey("eddy")){
            System.out.println("contains eddy");
        }

        if (map.containsValue(38)){
            System.out.println("contains 38");
        }
        System.out.println("--------------------get---------------------------");
        System.out.println(map.get("eddy"));

        System.out.println("--------------------foreach---------------------------");
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        map.remove("eddy2");
        map.clear();

    }
}
