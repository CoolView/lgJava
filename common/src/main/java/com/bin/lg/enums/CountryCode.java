package com.bin.lg.enums;

/**
 * @author Administrator
 * @date 2021-11-22 15:42
 */

public enum CountryCode {
    CN("CN 中国"),
    US("US 美国"),
    RU("RU 俄罗斯"),
    GB("GB 英国"),
    FR("FR 法国");

    /**
     * 名称
     */
    private String codename;

    CountryCode(String codename) {
        this.codename = codename;
    }

    public String getCodename() {
        return codename;
    }
}