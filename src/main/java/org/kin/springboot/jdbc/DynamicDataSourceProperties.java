package org.kin.springboot.jdbc;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 动态数据源配置
 * @author huangjianqin
 * @date 2022/9/17
 */
@ConfigurationProperties("spring.datasource.dynamic")
public final class DynamicDataSourceProperties {
    /** 是否开启动态数据源 */
    private boolean enable;
    /** 默认主数据源 */
    private String primary;
    /** 严格匹配数据源, 默认为false. 未匹配到数据源时, true则抛异常, false则使用默认主数据源 */
    private boolean strict;
    /** 各数据源配置 */
    private Map<String, DataSourceProperties> dataSources;

    //setter && getter

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public Map<String, DataSourceProperties> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }
}
