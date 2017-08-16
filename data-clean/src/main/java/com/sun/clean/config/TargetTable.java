package com.sun.clean.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @authur sj.
 */
@Component
@ConfigurationProperties(prefix = "import.table")
//@PropertySource("classpath:importTable.properties")
public class TargetTable {

    private List<String> names = new ArrayList<>();
    private List<String> rentalCheckCities = new ArrayList<>();

    public List<String> getRentalCheckCities()
    {
        return rentalCheckCities;
    }

    public void setRentalCheckCities(List<String> rentalCheckCities)
    {
        this.rentalCheckCities = rentalCheckCities;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
