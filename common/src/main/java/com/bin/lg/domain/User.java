package com.bin.lg.domain;

import com.bin.lg.enums.CountryCode;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;

    // private CountryCode countryCode;
    private CountryCode countryName;
}
