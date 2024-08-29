package io.github.nichetoolkit.rest.identity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * <code>ServerConfig</code>
 * <p>The type server config class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see lombok.Data
 * @see java.lang.SuppressWarnings
 * @since Jdk1.8
 */
@Data
@SuppressWarnings("SameNameButDifferent")
public class ServerConfig {
    /**
     * <code>IP_ADDRESS</code>
     * {@link java.lang.String} <p>the constant <code>IP_ADDRESS</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String IP_ADDRESS = "spring.cloud.client.ip-address";
    /**
     * <code>SERVER_PORT</code>
     * {@link java.lang.String} <p>the constant <code>SERVER_PORT</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String SERVER_PORT = "server.port";
    /**
     * <code>APP_NAME</code>
     * {@link java.lang.String} <p>the constant <code>APP_NAME</code> field.</p>
     * @see java.lang.String
     * @see com.fasterxml.jackson.annotation.JsonIgnore
     */
    @JsonIgnore
    public static final String APP_NAME = "spring.application.name";

    private String ip;
    private String port;
    private String name;

    /**
     * <code>ServerConfig</code>
     * Instantiates a new server config.
     */
    public ServerConfig() {
    }

    /**
     * <code>ServerConfig</code>
     * Instantiates a new server config.
     * @param ip   {@link java.lang.String} <p>the ip parameter is <code>String</code> type.</p>
     * @param port {@link java.lang.String} <p>the port parameter is <code>String</code> type.</p>
     * @param name {@link java.lang.String} <p>the name parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ServerConfig(String ip, String port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    /**
     * <code>toServer</code>
     * <p>the server method.</p>
     * @return {@link java.lang.String} <p>the server return object is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public String toServer() {
        return name + "/" + ip + ":" + name + ":" + port;
    }
}
