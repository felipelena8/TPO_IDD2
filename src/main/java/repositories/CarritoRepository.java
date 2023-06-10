package repositories;

import jakarta.annotation.PostConstruct;
import models.Item;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class CarritoRepository {

    private static final String key = "item";
    private RedisTemplate<String, Item> redisTemplate;
    private HashOperations hashOperations;

    public CarritoRepository(RedisTemplate<String, Item> redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }
    //public Item findBy

}
