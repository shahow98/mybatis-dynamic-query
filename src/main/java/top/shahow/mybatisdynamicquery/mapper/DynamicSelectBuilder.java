package top.shahow.mybatisdynamicquery.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Objects;

/**
 * @author shahow
 * @date 2023-02-18
 */
public class DynamicSelectBuilder {
    public static String buildDynamicSelect(@Param("querySql") String querySql,
            @Param("params") Map<String, Object> params) {
        Objects.requireNonNull(querySql);
        Objects.requireNonNull(params);
        String sql = querySql;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            sql = sql.replaceAll("#\\{ *" + param.getKey() + " *}", "#{ params." + param.getKey() + " }");
        }
        return sql;
    }
}
