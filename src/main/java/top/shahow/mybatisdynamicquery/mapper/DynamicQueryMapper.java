package top.shahow.mybatisdynamicquery.mapper;


import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.shahow.mybatisdynamicquery.entity.DynamicQueryConfig;

import java.util.List;
import java.util.Map;

/**
 * @author shahow
 * @date 2023-02-18
 */
@Mapper
public interface DynamicQueryMapper {

    @Results(id = "DynamicQueryConfigMap", value = {@Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "queryName", column = "query_name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "querySql", column = "query_sql", jdbcType = JdbcType.VARCHAR),
            @Result(property = "collection", column = "collection", jdbcType = JdbcType.INTEGER),
            @Result(property = "status", column = "status", jdbcType = JdbcType.VARCHAR),
            @Result(property = "remark", column = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(property = "params", column = "params", jdbcType = JdbcType.VARCHAR),
            @Result(property = "queryMock", column = "query_mock", jdbcType = JdbcType.VARCHAR)})
    @Select("SELECT id, query_name, query_sql, collection, status, remark, params, query_mock "
            + "FROM dynamic_query_config WHERE id = #{id} AND status <> '0SX'")
    DynamicQueryConfig selectConfigById(Long id);

    @SelectProvider(type = DynamicSelectBuilder.class, method = "buildDynamicSelect")
    List<Map<String, Object>> dynamicSelect(@Param("querySql") String querySql,
            @Param("params") Map<String, Object> params);
}
