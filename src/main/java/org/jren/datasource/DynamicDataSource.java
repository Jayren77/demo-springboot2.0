package org.jren.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static Map<Object,Object> datasourcesMap = new ConcurrentHashMap<>();



    /**
     * 重写该方法，自定义当前线程数据源类型
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSourceType();
    }

    /**
     * 返回当前线程的数据源
     * @return
     */
    public DataSource getCurrentDataSource(){
        return determineTargetDataSource();
    }
    /**
     * 初始化多数据源
     * @throws SQLException
     */
    @PostConstruct
    public void init() throws SQLException{

        super.setTargetDataSources(datasourcesMap);
    }
}
