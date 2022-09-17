package org.kin.springboot.jdbc;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author huangjianqin
 * @date 2022/9/17
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class, name = {"com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure"})
@ConditionalOnProperty(prefix = "spring.datasource.dynamic", name = {"enabled"}, havingValue = "true", matchIfMissing = true)
public class DynamicDataSourceAutoConfiguration {
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @Primary
    public DataSource dynamicDataSource(DynamicDataSourceProperties config){
        String primary = config.getPrimary();
        boolean strict = config.isStrict();
        Map<String, DataSourceProperties> dataSourceConfigs = config.getDataSources();
        if(Objects.isNull(dataSourceConfigs) || dataSourceConfigs.isEmpty()){
            throw new IllegalStateException("cannot find any  dataSource config");
        }

        Map dataSourceMap = new HashMap<>(dataSourceConfigs.size());
        dataSourceConfigs.forEach((k, v) -> {
            dataSourceMap.put(k, v.initializeDataSourceBuilder().build());
        });

        DynamicDataSource dynamicDataSource = new DynamicDataSource(primary, strict);
        //设置所有注册的datasource
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        //设置默认的datasource
        Object defaultDataSource = dataSourceMap.get(primary);
        if(Objects.isNull(defaultDataSource)){
            throw new IllegalArgumentException("primary dataSource is not found");
        }
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

        return dynamicDataSource;
    }

    @Bean
    public UseDataSourceAspect useDataSourceAspect(){
        return new UseDataSourceAspect();
    }
}
