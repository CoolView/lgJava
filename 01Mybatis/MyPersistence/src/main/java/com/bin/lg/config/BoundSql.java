package com.bin.lg.config;

import com.bin.lg.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author bin72
 * @date 2021/11/20 12:48
 */
@Data
@AllArgsConstructor
public class BoundSql {
    /**
     * 解析过后的sql
     */
    private String sqlText;

    private List<ParameterMapping> parameterMappingList;
}
