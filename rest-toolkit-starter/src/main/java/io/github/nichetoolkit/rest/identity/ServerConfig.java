package io.github.nichetoolkit.rest.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * <p>ServerConfig</p>
 * @author Cyan (snow22314 @ outlook.com)
 * @version v1.0.0
 */
@Data
@SuppressWarnings("SameNameButDifferent")
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

    public ServerConfig(String ip, String port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    public String toServer() {
        return name + "/" + ip + ":" + name + ":" + port;
    }
}
