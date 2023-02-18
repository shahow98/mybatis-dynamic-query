package top.shahow.mybatisdynamicquery.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;


/**
 * @author shahow
 * @date 2023-02-18
 */
@Data
public class DynamicQueryRequest {
    @NotNull(message = "id不能为空")
    private Long id;

    private Map<String, @NotNull(message = "参数值不能为空") String> params;
}
