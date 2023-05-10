package com.bank_manager.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 全局分页dto参数封装
 */
@Data
public class PageDto {
    private Integer page;
    private Integer limit;

    public Page getPage() {
        if (page == null || limit == null) {
            return new Page(1, 10);
        }
        return new Page(page, limit);
    }
}
