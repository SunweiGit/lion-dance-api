package cn.liondance.liondanceapi.enums;

/**
 * @author sunwei
 */

public enum OperateType {
    remove("remove"),
    update("update"),
    load("load"),
    create("create");
    private final String value;

    OperateType(String value) {
        this.value = value;
    }
}
