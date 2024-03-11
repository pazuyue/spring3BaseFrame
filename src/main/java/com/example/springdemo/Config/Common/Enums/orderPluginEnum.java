package com.example.springdemo.Config.Common.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum orderPluginEnum {

    HmOrderTransfer(Type.HM, "HM转单"),
    CommonOrderTransfer(Type.Common, "通用转单");

    public String orderPluginType;
    public String remark;

    public static class Type {
        public static final String HM = "hm";
        public static final String Common = "common";
    }


}



