package collections.pattern.loadbalance;

import java.util.ArrayList;
import java.util.List;

public class IpHash implements LoadBalance {

    @Override
    public String getServer(String clientIp) {
        if (clientIp == null) {
            clientIp = "127.0.0.1";
        }
        List<String> servers = new ArrayList<>(IpPool.ipMap.keySet());
        int index = clientIp.hashCode() % servers.size();
        return servers.get(index);
    }
}
