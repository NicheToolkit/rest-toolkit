package io.github.nichetoolkit.rest.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>ServerConfig</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Builder
@AllArgsConstructor
public class ServerConfig {
    @JsonIgnore
    public static final String IP_ADDRESS = "spring.cloud.client.ip-address";
    @JsonIgnore
    public static final String SERVER_PORT = "server.port";
    @JsonIgnore
    public static final String APP_NAME = "spring.application.name";

    private String ip;
    private String port;
    private String name;

    public ServerConfig() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toServer() {
        return name + "/" + ip + ":" + name + ":" + port;
    }
}
