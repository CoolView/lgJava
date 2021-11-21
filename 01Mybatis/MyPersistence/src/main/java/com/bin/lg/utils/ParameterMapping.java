package com.bin.lg.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * sql 中的参数 ${id}
 *
 * @author bin72
 * @date 2021/11/20 16:19
 */
@Data
@AllArgsConstructor
public class ParameterMapping {
    /**
     * ${id} 中的 id
     */
    private String content;
}
