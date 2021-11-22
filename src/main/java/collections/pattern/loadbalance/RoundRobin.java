package collections.pattern.loadbalance;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin implements LoadBalance {
    private static Integer position = 0;

    @Override
    public String getServer(String clientIp) {
        List<String> servers = new ArrayList<>(IpPool.ipMap.keySet());
        if (position > servers.size() - 1) {
            position = 0;
        }
        String target = servers.get(position);
        position++;

        return target;
    }
}
