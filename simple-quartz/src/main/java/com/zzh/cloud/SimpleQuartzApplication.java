package com.zzh.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatEmbeddedWebappClassLoader;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

@SpringBootConfiguration
@ImportResource("applicationContext.xml")
public class SimpleQuartzApplication {

    private static final Logger logger = LoggerFactory.getLogger(SimpleQuartzApplication.class);
    @Value("${server.port}")
    private int port;
    @Value("${server.sessionTimeout}")
    private int sessionTimeout;

    public static void main(String[] args) {
        logger.info("简单Quartz微服务-" + System.getProperty("file.encoding"));
        SpringApplication.run(SimpleQuartzApplication.class, args);
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(port);
        factory.setSessionTimeout(Duration.ofSeconds(sessionTimeout));
        return factory;
    }
}
