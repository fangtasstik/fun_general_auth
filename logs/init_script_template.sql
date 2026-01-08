/*==============================================================*/
/* DBMS name:      MySQL 5.0                                   */
/* Created on:     2023-09-08 14:53:37                        */
/*==============================================================*/

drop table if exists sys_menu;

drop table if exists sys_role;

drop table if exists sys_role_menu;

drop table if exists sys_user;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_menu                                             */
/*==============================================================*/
create table sys_menu
(
   menu_id              int not null auto_increment comment '菜单id',
   parent_id            int comment '上级id',
   title                varchar(36) comment '菜单名称',
   code                 varchar(36) comment '权限字段',
   name                 varchar(36) comment '路由名称',
   path                 varchar(36) comment '路由地址',
   url                  varchar(128) comment '组件路径',
   type                 varchar(2) comment '菜单类型',
   icon                 varchar(36) comment '菜单图标',
   parent_name          varchar(36) comment '上级菜单名称',
   order_num            int comment '序号',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (menu_id)
);

/*==============================================================*/
/* Table: sys_role                                             */
/*==============================================================*/
create table sys_role
(
   role_id              int not null auto_increment comment '角色id',
   role_name            varchar(36) comment '角色名称',
   type                 varchar(4) comment '类型',
   remark               varchar(64) comment '描述',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (role_id)
);

/*==============================================================*/
/* Table: sys_role_menu                                        */
/*==============================================================*/
create table sys_role_menu
(
   role_menu_id         int not null auto_increment comment '主键',
   role_id              int comment '角色id',
   menu_id              int comment '菜单id',
   primary key (role_menu_id)
);

/*==============================================================*/
/* Table: sys_user                                             */
/*==============================================================*/
create table sys_user
(
   user_id              int not null auto_increment comment '用户id',
   username             varchar(36) comment '登录账户',
   password             varchar(128) comment '登录密码',
   phone                varchar(18) comment '电话',
   email                varchar(36) comment '邮箱',
   sex                  varchar(4) comment '性别',
   is_admin             tinyint comment '是否是超级管理员 1: 是 0: 否',
   is_account_non_expired tinyint comment '账户是否过期 1 未过期，0已过期',
   is_account_non_locked tinyint comment '账户是否被锁定(1 未锁定，0已锁定)',
   is_credentials_non_expired tinyint comment '密码是否过期(1 未过期，0已过期)',
   is_enabled           tinyint comment '账户是否可用(1 可用，0 被禁用户)',
   nick_name            varchar(36) comment '昵称',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (user_id)
);

/*==============================================================*/
/* Table: sys_user_role                                        */
/*==============================================================*/
create table sys_user_role
(
   user_role_id         int not null auto_increment comment '主键',
   user_id              int comment '用户id',
   role_id              int comment '角色id',
   primary key (user_role_id)
);