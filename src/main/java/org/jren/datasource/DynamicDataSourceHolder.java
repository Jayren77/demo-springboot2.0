package org.jren.datasource;

/**
 * 数据源动态选择器
 */
public class DynamicDataSourceHolder {
    /**
     * HOLDERS:维护当前线程
     */
    private static final ThreadLocal<DynamicDataSourceType> HOLDERS = new ThreadLocal<DynamicDataSourceType>();


    /**
     * 默认数据库
     * @param dataSourceType DynamicDataSourceType
     */
    public static void setContextHolder(DynamicDataSourceType dataSourceType) {
        if (dataSourceType == null) {
            HOLDERS.set(DynamicDataSourceType.DS1);
        } else {
            HOLDERS.set(dataSourceType);
        }
    }

    /**
     * @return DynamicDataSourceType
     */
    public static DynamicDataSourceType getDataSourceType() {
        return (DynamicDataSourceType) HOLDERS.get();
    }


}
