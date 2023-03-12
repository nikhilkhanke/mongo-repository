package com.nk.mongo.enums;

public enum MongoFilterOperationEnum {

    EQUAL("eq"),
    EQUAL_I("eqi"),
    NOT_EQUAL("neq"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
    INA("ina"),
    NOT_IN("nin"),
    BETWEEN("btn"),
    CONTAINS("like"),
    NOT_CONTAINS("notLike"),
    IS_NULL("isnull"),
    IS_NOT_NULL("isnotnull"),
    START_WITH("startwith"),
    END_WITH("endwith"),
    IS_EMPTY("isempty"),
    IS_NOT_EMPTY("isnotempty"),
    JOIN("jn"),
    IS("is"),
    IB("ib");


    private final String value;

    MongoFilterOperationEnum(String value) {
        this.value = value;
    }

    public static MongoFilterOperationEnum fromValue(String value) {
        for (MongoFilterOperationEnum op : MongoFilterOperationEnum.values()) {
            //Case insensitive operation name
            if (String.valueOf(op.value).equalsIgnoreCase(value)) {
                return op;
            }
        }
        return null;
    }

}