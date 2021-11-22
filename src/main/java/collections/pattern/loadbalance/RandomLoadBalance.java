package collections.pattern.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {

    @Override
    public String getServer(String clientIp) {
        List<String> servers = new ArrayList<>(IpPool.ipMap.keySet());
        int randomIndex = new Random().nextInt(servers.size());
        return servers.get(randomIndex);
    }
}
