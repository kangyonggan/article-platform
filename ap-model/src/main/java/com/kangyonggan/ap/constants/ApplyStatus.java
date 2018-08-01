package com.kangyonggan.ap.constants;

import lombok.Getter;

/**
 * 申请状态
 *
 * @author kangyonggan
 * @since 6/6/18
 */
public enum ApplyStatus {

    /**
     * 待审批
     */
    APPLY("APPLY", "待审批"),

    /**
     * 已拒绝
     */
    REJECT("REJECT", "已拒绝"),

    /**
     * 审批通过
     */
    COMPLETE("COMPLETE", "审批通过");

    /**
     * 状态代码
     */
    @Getter
    private final String code;

    /**
     * 状态名称
     */
    @Getter
    private final String name;

    ApplyStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
