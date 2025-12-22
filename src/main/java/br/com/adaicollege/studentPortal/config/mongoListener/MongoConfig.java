package br.com.adaicollege.studentPortal.config.mongoListener;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .addCommandListener(new MongoQueryTimingListener())
                        .build()
        );
    }

}
