package org.afterfilm.afterfilm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.afterfilm.afterfilm.repository")
public class MyBatisConfig {
}
