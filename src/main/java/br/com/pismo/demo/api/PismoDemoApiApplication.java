/*
 * PismoDemoApiApplication.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api;

import br.com.pismo.demo.api.auth.User;
import br.com.pismo.demo.api.util.Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.pismo.demo.api.auth.UserRepository;

import java.util.Optional;

/**
 * The main class to start the application.
 */
@SpringBootApplication(scanBasePackages = {"br.com.pismo.demo.api"})
public class PismoDemoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PismoDemoApiApplication.class, args);
    }

    /**
     * Init the {@link UserRepository} to create admin user on the first start application.
     *
     * @param userRepository
     * @return
     */
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> initUsers(userRepository);
    }

    /**
     * Create a user admin.
     *
     * @param userRepository
     */
    private void initUsers(UserRepository userRepository) {
        Optional<User> userOptional = userRepository.findUserByNameAndEmail("admin", "adminpismo@gmail.com");
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setName("admin");
            user.setEmail("adminpismo@gmail.com");
            user.setPassword(Util.encrypt("admin@123"));
            userRepository.save(user);
        }
    }
}
