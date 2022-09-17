package org.kin.springboot.jdbc;

/**
 * 缓存线程使用的数据源上下文
 * @author huangjianqin
 * @date 2022/9/17
 */
public final class DynamicDataSourceContextHolder {
    private DynamicDataSourceContextHolder() {
    }

    /** 线程本地的数据源key */
    private static final ThreadLocal<String> DATASOURCE_KEY_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程使用的数据源key
     * @param key 数据源key
     */
    public static void setDataSourceKey(String key){
        DATASOURCE_KEY_THREAD_LOCAL.set(key);
    }

    /**
     * 获取当前线程使用的数据源key
     */
    public static String getDataSourceKey(){
        return DATASOURCE_KEY_THREAD_LOCAL.get();
    }

    /**
     * 移除当前线程使用的数据源key
     */
    public static void removeDataSourceKey(){
        DATASOURCE_KEY_THREAD_LOCAL.remove();
    }
}
