package top.shahow.mybatisdynamicquery.service;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.shahow.mybatisdynamicquery.entity.DynamicQueryConfig;
import top.shahow.mybatisdynamicquery.entity.DynamicQueryRequest;
import top.shahow.mybatisdynamicquery.mapper.DynamicQueryMapper;
import top.shahow.mybatisdynamicquery.utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author shahow
 * @date 2023-02-18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicQueryService {
    private final DynamicQueryMapper dynamicQueryMapper;

    public Object dynamicQuery(DynamicQueryRequest request) {
        DynamicQueryConfig config = dynamicQueryMapper.selectConfigById(request.getId());
        Objects.requireNonNull(config, "Configuration id[" + request.getId() + "] not found!");
        return queryOne(config, queryDB(config, request, this::queryMock));
    }

    private List<Map<String, Object>> queryDB(DynamicQueryConfig config, DynamicQueryRequest request,
                                              Function<DynamicQueryConfig, List<Map<String, Object>>> nextQueryFunction) {
        if (DynamicQueryConfig.StatusType.USING.equals(config.getStatus())) {
            return dynamicQueryMapper.dynamicSelect(
                    Optional.ofNullable(querySql(config)).orElseThrow(() -> new NullPointerException("The sql is null!")),
                    queryParams(config, request));
        }
        return Optional.ofNullable(nextQueryFunction).orElseThrow(() -> new NullPointerException("The Next query functon is null!")).apply(config);
    }

    private List<Map<String, Object>> queryMock(DynamicQueryConfig config) {
        if (DynamicQueryConfig.StatusType.MOCK.equals(config.getStatus())) {
            try {
                return ObjectMapperUtils.from(config.getQueryMock(), new TypeReference<>() {
                });
            } catch (IOException e) {
            }
        }
        return Collections.emptyList();
    }

    private Object queryOne(DynamicQueryConfig config, List<Map<String, Object>> result) {
        if (DynamicQueryConfig.ResultType.COLLECTION.equals(config.getCollection())) {
            return Optional.ofNullable(result).orElse(Collections.emptyList());
        } else {
            return Optional.ofNullable(result).filter(item -> !item.isEmpty()).map(item -> item.get(0)).orElse(null);
        }
    }

    private String querySql(DynamicQueryConfig config) {
        return config.getQuerySql();
    }

    private Map<String, Object> queryParams(DynamicQueryConfig config, DynamicQueryRequest request) {
        Map<String, Object> queryParams = new HashMap<>();
        Set<String> params = Optional.ofNullable(config.getParams()).map(ps -> ps.split(",")).map(Arrays::asList)
                .orElse(Collections.emptyList()).stream().collect(Collectors.toSet());
        Optional.ofNullable(request.getParams()).orElse(Collections.emptyMap()).entrySet().stream()
                .filter(entry -> params.contains(entry.getKey())).forEach(entry -> {
                    queryParams.put(entry.getKey(), entry.getValue());
                });
        log.debug("[dynamic query] params => {}", queryParams);
        return queryParams;
    }
}
