package cn.liondance.liondanceapi.enums;

/**
 * @author sunwei
 */

public enum ExecutionStatus {
    readable("1"), locked("2"), unreadable("3");

    public String value;

    ExecutionStatus(String value) {
        this.value = value;
    }

}
