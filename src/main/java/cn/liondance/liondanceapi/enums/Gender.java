package cn.liondance.liondanceapi.enums;

public enum Gender {
    unknown("未知"),
    men("男"),
    women("女");
    public String value;

    Gender(String value) {
        this.value = value;
    }
}
