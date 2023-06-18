package config;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConnectionPool {
    private static RedisConnectionPool instancia = null;
    private final String url = "redis://default:uade1234@redis-19782.c8.us-east-1-3.ec2.cloud.redislabs.com:19782";
    private JedisPoolConfig poolConfig = new JedisPoolConfig();
    private RedisConnectionPool() {
    }

    public static RedisConnectionPool getInstancia() {
        if (instancia == null) {
            instancia = new RedisConnectionPool();
        }
        return instancia;
    }

    public JedisPool getConnection() {
        return new JedisPool(poolConfig, url);
    }

}
