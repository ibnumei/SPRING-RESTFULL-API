package msjavamicro.restfull;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@SpringBootApplication
public class MsJavaMicroResTfullApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsJavaMicroResTfullApiApplication.class, args);
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig() //
			.entryTtl(Duration.ofMinutes(5)) //
			.disableCachingNullValues()
			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
;

		return RedisCacheManager.builder(connectionFactory) //
			.cacheDefaults(config) //
			.build();
	}
}
