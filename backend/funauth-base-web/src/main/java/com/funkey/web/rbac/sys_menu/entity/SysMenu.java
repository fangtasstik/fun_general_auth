package com.funkey.web.rbac.sys_menu.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sys_menu")
public class SysMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "code", length = 64)
    private String code;

    @Column(name = "name", length = 36)
    private String name;

    @Column(name = "path", length = 36)
    private String path;

    @Column(name = "url", length = 128)
    private String url;

    @Column(name = "type", nullable = false, length = 2)
    private String type;

    @Column(name = "icon", length = 36)
    private String icon;

    @Column(name = "parent_name", length = 64)
    private String parentName;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}



