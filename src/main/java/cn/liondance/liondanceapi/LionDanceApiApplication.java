package cn.liondance.liondanceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunwei
 */
@SpringBootApplication
@RestController
@EnableJpaAuditing
@EnableAsync
public class LionDanceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LionDanceApiApplication.class, args);
    }

}
