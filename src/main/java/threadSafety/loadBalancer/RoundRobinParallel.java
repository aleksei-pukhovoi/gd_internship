package threadSafety.loadBalancer;

import collections.pattern.loadbalance.IpPool;
import collections.pattern.loadbalance.LoadBalance;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinParallel implements LoadBalance {
    private int position;

    @Override
    public String getServer(String clientIp) {
        List<String> servers = new ArrayList<>(IpPool.ipMap.keySet());
        synchronized (this) {
            if (position > servers.size() - 1) {
                position = 0;
            }
            return servers.get(position++);
        }
    }
}
