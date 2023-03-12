package com.nk.mongo.model;

import com.nk.mongo.enums.MongoFilterOperationEnum;

public class MongoFilterCondition {
    private String field;
    private Object value;
    private MongoFilterOperationEnum operator;

    public MongoFilterCondition(String field, MongoFilterOperationEnum operator, Object value) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public MongoFilterOperationEnum getOperator() {
        return operator;
    }

    public void setOperator(MongoFilterOperationEnum operator) {
        this.operator = operator;
    }
}
