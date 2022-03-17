package com.junmoyu.venus.example.single.boot.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * venus-example-single-boot 项目测试表
 *
 * @author moyu.jun
 * @date 2022/3/17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "single_boot_table")
public class SingleBootTable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_CODE = "code";

    public static final String COL_SECRET = "secret";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_ICON = "icon";

    public static final String COL_BANNER = "banner";

    public static final String COL_SORT = "sort";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "code")
    private String code;

    @TableField(value = "secret")
    private String secret;

    @TableField(value = "description")
    private String description;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "banner")
    private String banner;

    @TableField(value = "sort")
    private Integer sort;

    @TableField(value = "is_deleted")
    private Boolean deleted;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;
}