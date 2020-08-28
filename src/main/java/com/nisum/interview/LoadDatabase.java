package com.nisum.interview;

import com.nisum.interview.model.Phone;
import com.nisum.interview.model.User;
import com.nisum.interview.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            User juan = new User("Juan Rodriguez", "juan@rodriguez.org", "hunter2");
            juan.getPhones().add(new Phone("099 999 999", "1", "+098"));
            juan.getPhones().add(new Phone("099 999 998", "1", "+098"));

            log.info("Preloading " + repository.save(juan));

            User gabriel = new User("Gabriel Oest", "gabriel@oest.com", "pswD1234");
            gabriel.getPhones().add(new Phone("099 999 997", "1", "+098"));
            gabriel.getPhones().add(new Phone("099 999 996", "1", "+098"));
            log.info("Preloading " + repository.save(gabriel));
        };
    }
}
