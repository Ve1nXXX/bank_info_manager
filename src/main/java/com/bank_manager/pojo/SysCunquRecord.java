package com.bank_manager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 存取款记录表
 *
 * @author xt
 */
@Data
@TableName("sys_cunqu_record")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysCunquRecord implements Serializable {
    /**
     * 序号
     */
    //@TableId(type = IdType.ASSIGN_ID)
    //@JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addtime;


}
