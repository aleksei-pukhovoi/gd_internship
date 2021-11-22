package collections.pattern.loadbalance;

import java.util.*;

public class WeightRandom implements LoadBalance {
    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        for (String server : servers) {
            Integer weight = IpPool.ipMap.get(server);
            if (weight != null && weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(server);
                }
            }
        }
        int index = new Random().nextInt(serverList.size());
        return serverList.get(index);
    }
}
