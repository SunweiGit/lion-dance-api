package cn.liondance.liondanceapi.enums;

/**
 * @author sunwei
 */

public enum ExecutionStatus {
    readable("readable"), locked("locked"), unreadable("unreadable");

    public String value;

    ExecutionStatus(String value) {
        this.value = value;
    }

}
