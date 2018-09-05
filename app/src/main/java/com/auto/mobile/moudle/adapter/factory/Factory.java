package com.auto.mobile.moudle.adapter.factory;

import java.util.ArrayList;

/**
 * 头数据
 */
public class Factory {

    private String factoryName;//服务名称

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Factory(String factoryName) {
        this.factoryName = factoryName;
    }
}
