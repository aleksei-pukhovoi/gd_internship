package collections.pattern.loadbalance;

import java.util.*;
import java.util.stream.Collectors;

public class WeightLoadBalance implements LoadBalance {

    private static PriorityQueue<Server> servers = getServers();

    @Override
    public String getServer(String clientIp) {
        if(servers.size() == 0 ) {
            servers = getServers();
        }
            return Objects.requireNonNull(servers.poll()).getIp();
    }

    private static PriorityQueue<Server> getServers() {
        return IpPool.ipMap.entrySet().stream()
                .map(entry -> new Server(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(PriorityQueue<Server>::new));
    }
}
