package com.bank_manager.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局分页数据对象模型
 * @param <T>
 */
@Data
@Builder
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 4348683076964072685L;
    private Long count;
    private T result;
}
