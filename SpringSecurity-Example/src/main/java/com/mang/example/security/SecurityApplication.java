package com.mang.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
                "com\\.mang\\.example\\.security\\.forTest\\.config\\.V2\\..*",
                "com\\.mang\\.example\\.security\\.forTest\\.config\\.V3\\..*",
                "com\\.mang\\.example\\.security\\.forTest\\.config\\.V1\\..*",
                "com\\.mang\\.example\\.security\\.forTest\\.config\\.V4\\..*",

                "com\\.mang\\.example\\.security\\.app\\..*",
                "com\\.mang\\.example\\.security\\.enums\\..*",
                "com\\.mang\\.example\\.security\\.exception\\..*",
                "com\\.mang\\.example\\.security\\.forTest\\..*",
        })
})

//redis 설정
//@EnableRedisHttpSession
public class SecurityApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(SecurityApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
