package collections.pattern.loadbalance;

public interface LoadBalance {

    String getServer(String clientIp);
}
