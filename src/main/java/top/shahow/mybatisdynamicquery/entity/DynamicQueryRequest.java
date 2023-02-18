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
    @NotNull(message = "id is not null!")
    private Long id;

    private Map<String, @NotNull(message = "params is not null") String> params;
}
