package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringhelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringhelloApplication.class, args);
    }

}
/*public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World Start!");
    }
}*/