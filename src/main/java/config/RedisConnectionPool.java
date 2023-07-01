package config;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConnectionPool {
    private static RedisConnectionPool instancia = null;
    private final String url = "redis://tpobd2user:BD.cassandra1@redis-17283.c266.us-east-1-3.ec2.cloud.redislabs.com:17283";
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
