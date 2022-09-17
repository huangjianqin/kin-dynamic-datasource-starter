package org.kin.springboot.jdbc;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * @author huangjianqin
 * @date 2022/9/17ya
 */
public final class DynamicDataSource extends AbstractRoutingDataSource {
    /** 默认主数据源key */
    private final String primary;
    /** 是否严格匹配数据源 */
    private final boolean strict;

    public DynamicDataSource(String primary, boolean strict) {
        if(!StringUtils.hasText(primary)){
            throw new IllegalArgumentException("primary datasource name is not blank");
        }

        this.primary = primary;
        this.strict = strict;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DynamicDataSourceContextHolder.getDataSourceKey();
        if(StringUtils.hasText(key)){
            //设置了数据源key, 则使用
            if(strict && !getResolvedDataSources().containsKey(key)){
                //检查是否数据源是否合法
                throw new IllegalStateException("cannot find target DataSource key [" + key + "]");
            }
            return key;
        }
        //否则, 回退到默认主数据源key
        return primary;
    }
}
