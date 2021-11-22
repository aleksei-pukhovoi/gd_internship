package collections.pattern.loadbalance;

import java.util.Objects;

public class Server implements Comparable<Server> {
   private String ip;
   private Integer weight;

    public Server(String ip, Integer weight) {
        this.ip = ip;
        this.weight = weight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Server o) {
        return o.getWeight().compareTo(this.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equals(ip, server.ip) && Objects.equals(weight, server.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, weight);
    }
}
