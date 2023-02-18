package top.shahow.mybatisdynamicquery.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author shahow
 * @date 2023-02-18
 */
public class ObjectMapperUtils {
    private static final ObjectMapper objectMapper = initObjectMapper();

    private static ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper =  new ObjectMapper(new JsonFactory());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    public static <T> String to(T src) throws JsonProcessingException {
        return objectMapper.writer().writeValueAsString(src);
    }

    public static <T, R> R from(T src, Class<R> distType) throws IOException {
        String srcJson = objectMapper.writer().writeValueAsString(src);
        JsonParser parser = objectMapper.getFactory().createParser(srcJson);
        return objectMapper.reader().readValue(parser, distType);
    }

    public static <T> T from(String srcJson, Class<T> distType) throws IOException {
        JsonParser parser = objectMapper.getFactory().createParser(srcJson);
        return objectMapper.reader().readValue(parser, distType);
    }

    public static <T> T from(String srcJson, TypeReference<T> typeReference) throws IOException {
        JsonParser parser = objectMapper.getFactory().createParser(srcJson);
        return objectMapper.reader().readValue(parser, typeReference);
    }
}
