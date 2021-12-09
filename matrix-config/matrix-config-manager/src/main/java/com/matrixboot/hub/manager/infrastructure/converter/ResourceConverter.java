package com.matrixboot.hub.manager.infrastructure.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrixboot.hub.manager.domain.value.Resources;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * <p>
 * create in 2021/9/17 10:42 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Converter
public class ResourceConverter implements AttributeConverter<Resources, String> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public String convertToDatabaseColumn(Resources attribute) {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public Resources convertToEntityAttribute(String dbData) {
        if (!StringUtils.hasLength(dbData)) {
            return new Resources();
        }

        return objectMapper.readValue(dbData, Resources.class);
    }
}
