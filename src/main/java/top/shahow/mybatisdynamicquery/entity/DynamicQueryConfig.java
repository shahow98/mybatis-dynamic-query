package top.shahow.mybatisdynamicquery.entity;

import lombok.Data;

/**
 * @author shahow
 * @date 2023-02-18
 */
@Data
public class DynamicQueryConfig {
    public interface ResultType {
        Integer COLLECTION = 1;

        Integer OBJECT = 0;
    }

    public interface StatusType {
        String MOCK = "MOCK";

        String USING = "USING";

        String STOP = "STOP";
    }

    private Long id;
    /**
     * 查询名称(唯一)
     */
    private String queryName;
    /**
     * sql查询模板
     */
    private String querySql;
    /**
     * 是否集合;0/1(否-实体/是-实体数组)
     */
    private Integer collection;
    /**
     * 状态;USING/STOP/MOCK(在用/停用/模拟)
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 模板参数名;多个逗号隔开
     */
    private String params;
    /**
     * 模拟数据(json)
     */
    private String queryMock;
}
