package collections.pattern.loadbalance;

import java.util.HashMap;
import java.util.Map;

public class IpPool {
    public static Map<String, Integer> ipMap = new HashMap<>();

    static {
        ipMap.put("192.168.1.1", 1);
        ipMap.put("192.168.1.2", 2);
        ipMap.put("192.168.1.3", 3);
        ipMap.put("192.168.1.4", 4);
        ipMap.put("192.168.1.5", 5);
        ipMap.put("192.168.1.6", 6);
        ipMap.put("192.168.1.7", 7);
        ipMap.put("192.168.1.8", 8);
        ipMap.put("192.168.1.9", 9);
        ipMap.put("192.168.1.10", 10);
    }
}

