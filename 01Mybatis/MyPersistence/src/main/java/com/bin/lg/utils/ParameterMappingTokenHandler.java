package com.bin.lg.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bin72
 */
@Data
public class ParameterMappingTokenHandler implements TokenHandler {
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    /**
     *
     * @param content 参数名称 #{id} #{username}
     * @return ?
     */
    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        return new ParameterMapping(content);
    }



}
