# **kin-dynamic-datasource-starter**
动态数据源切换实现, 详细看`org.kin.framework.springboot.jdbc.DynamicDataSourceProperties`
  和`org.kin.framework.springboot.jdbc.UseDataSource`. 原理是基于`org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource`+`AOP`实现