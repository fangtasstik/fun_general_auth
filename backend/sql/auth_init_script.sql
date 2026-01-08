-- =============================================
-- RBAC权限管理系统 MySQL数据库脚本
-- MySQL Version: 5.7+ / 8.0+
-- Character Set: utf8mb4
-- Collation: utf8mb4_unicode_ci
-- Storage Engine: InnoDB
-- =============================================

-- 创建数据库（可选）
CREATE DATABASE IF NOT EXISTS rbac_system
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE rbac_system;

-- =============================================
-- 1. 用户表
-- =============================================
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(64) NOT NULL COMMENT '登录账户',
    password VARCHAR(128) NOT NULL COMMENT '登录密码',
    phone VARCHAR(13) DEFAULT NULL COMMENT '用户电话',
    email VARCHAR(36) DEFAULT NULL COMMENT '邮箱',
    sex VARCHAR(2) DEFAULT '0' COMMENT '性别：0-男，1-女',
    is_admin TINYINT(1) DEFAULT 0 COMMENT '是否为超级管理员：1-是，0-否',
    is_account_non_expired TINYINT(1) DEFAULT 1 COMMENT '帐户是否过期：1-未过期，0-已过期',
    is_account_non_locked TINYINT(1) DEFAULT 1 COMMENT '帐户是否被锁定：1-未锁定，0-已锁定',
    is_credentials_non_expired TINYINT(1) DEFAULT 1 COMMENT '密码是否过期：1-未过期，0-已过期',
    is_enabled TINYINT(1) DEFAULT 1 COMMENT '帐户是否可用：1-可用，0-删除用户',
    nick_name VARCHAR(36) DEFAULT NULL COMMENT '姓名',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_username (username),
    KEY idx_phone (phone),
    KEY idx_email (email),
    KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- =============================================
-- 2. 角色表
-- =============================================
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    role_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    role_name VARCHAR(64) NOT NULL COMMENT '角色名称',
    type VARCHAR(10) DEFAULT NULL COMMENT '扩展字段',
    remark VARCHAR(128) DEFAULT NULL COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (role_id),
    UNIQUE KEY uk_role_name (role_name),
    KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- =============================================
-- 3. 菜单表
-- =============================================
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
    menu_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    parent_id INT(11) UNSIGNED DEFAULT 0 COMMENT '父级菜单ID，0表示顶级菜单',
    title VARCHAR(64) NOT NULL COMMENT '菜单名称',
    code VARCHAR(64) DEFAULT NULL COMMENT '权限字段',
    name VARCHAR(36) DEFAULT NULL COMMENT '路由name',
    path VARCHAR(36) DEFAULT NULL COMMENT '路由path',
    url VARCHAR(128) DEFAULT NULL COMMENT '组件路径',
    type VARCHAR(2) NOT NULL DEFAULT '1' COMMENT '类型：0-目录，1-菜单，2-按钮',
    icon VARCHAR(36) DEFAULT NULL COMMENT '菜单图标',
    parent_name VARCHAR(64) DEFAULT NULL COMMENT '上级菜单名称',
    order_num INT(11) DEFAULT 0 COMMENT '序号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (menu_id),
    KEY idx_parent_id (parent_id),
    KEY idx_order_num (order_num)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';

-- =============================================
-- 4. 用户角色关联表
-- =============================================
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    user_role_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id INT(11) UNSIGNED NOT NULL COMMENT '用户ID',
    role_id INT(11) UNSIGNED NOT NULL COMMENT '角色ID',
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uk_user_role (user_id, role_id),
    KEY idx_user_id (user_id),
    KEY idx_role_id (role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES sys_user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES sys_role (role_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- =============================================
-- 5. 角色菜单关联表
-- =============================================
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu (
    role_menu_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_id INT(11) UNSIGNED NOT NULL COMMENT '角色ID',
    menu_id INT(11) UNSIGNED NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (role_menu_id),
    UNIQUE KEY uk_role_menu (role_id, menu_id),
    KEY idx_role_id (role_id),
    KEY idx_menu_id (menu_id),
    CONSTRAINT fk_role_menu_role FOREIGN KEY (role_id) REFERENCES sys_role (role_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_role_menu_menu FOREIGN KEY (menu_id) REFERENCES sys_menu (menu_id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';

-- =============================================
-- 插入初始数据示例
-- =============================================

-- 插入超级管理员用户（密码通常需要加密，这里仅为示例）
INSERT INTO sys_user (username, password, nick_name, is_admin, phone, email) VALUES
('admin', 'hashed_password_here', '超级管理员', 1, '13800138000', 'admin@example.com');

-- 插入基础角色
INSERT INTO sys_role (role_name, remark) VALUES
('ROLE_ADMIN', '系统管理员'),
('ROLE_USER', '普通用户'),
('ROLE_GUEST', '访客');

-- 插入基础菜单
INSERT INTO sys_menu (parent_id, title, code, type, icon, order_num) VALUES
(0, '系统管理', 'system', '0', 'setting', 1),
(1, '用户管理', 'system:user', '1', 'user', 1),
(1, '角色管理', 'system:role', '1', 'team', 2),
(1, '菜单管理', 'system:menu', '1', 'menu', 3),
(2, '添加用户', 'system:user:add', '2', NULL, 1),
(2, '编辑用户', 'system:user:edit', '2', NULL, 2),
(2, '删除用户', 'system:user:delete', '2', NULL, 3);

-- 关联超级管理员与管理员角色
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1);

-- 授予管理员角色所有菜单权限
INSERT INTO sys_role_menu (role_id, menu_id) 
SELECT 1, menu_id FROM sys_menu;

-- =============================================
-- 创建视图（可选）
-- =============================================

-- 用户角色视图
CREATE OR REPLACE VIEW v_user_roles AS
SELECT 
    u.user_id,
    u.username,
    u.nick_name,
    GROUP_CONCAT(r.role_name) as roles
FROM sys_user u
LEFT JOIN sys_user_role ur ON u.user_id = ur.user_id
LEFT JOIN sys_role r ON ur.role_id = r.role_id
WHERE u.is_enabled = 1
GROUP BY u.user_id;

-- 角色权限视图
CREATE OR REPLACE VIEW v_role_permissions AS
SELECT 
    r.role_id,
    r.role_name,
    m.menu_id,
    m.title,
    m.code,
    m.type
FROM sys_role r
LEFT JOIN sys_role_menu rm ON r.role_id = rm.role_id
LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id
ORDER BY r.role_id, m.order_num;

-- =============================================
-- 存储过程（可选）
-- =============================================

DELIMITER //

-- 获取用户所有权限的存储过程
CREATE PROCEDURE sp_get_user_permissions(IN p_user_id INT)
BEGIN
    SELECT DISTINCT
        m.menu_id,
        m.parent_id,
        m.title,
        m.code,
        m.path,
        m.url,
        m.type,
        m.icon,
        m.order_num
    FROM sys_user u
    INNER JOIN sys_user_role ur ON u.user_id = ur.user_id
    INNER JOIN sys_role_menu rm ON ur.role_id = rm.role_id
    INNER JOIN sys_menu m ON rm.menu_id = m.menu_id
    WHERE u.user_id = p_user_id 
        AND u.is_enabled = 1
    ORDER BY m.parent_id, m.order_num;
END//

-- 递归获取菜单树的存储过程
CREATE PROCEDURE sp_get_menu_tree(IN p_parent_id INT)
BEGIN
    WITH RECURSIVE menu_tree AS (
        SELECT 
            menu_id,
            parent_id,
            title,
            code,
            path,
            url,
            type,
            icon,
            order_num,
            0 as level
        FROM sys_menu
        WHERE parent_id = p_parent_id
        
        UNION ALL
        
        SELECT 
            m.menu_id,
            m.parent_id,
            m.title,
            m.code,
            m.path,
            m.url,
            m.type,
            m.icon,
            m.order_num,
            mt.level + 1
        FROM sys_menu m
        INNER JOIN menu_tree mt ON m.parent_id = mt.menu_id
    )
    SELECT * FROM menu_tree ORDER BY level, order_num;
END//

DELIMITER ;

-- =============================================
-- 索引优化建议
-- =============================================

-- 为经常查询的字段添加索引
-- ALTER TABLE sys_user ADD INDEX idx_is_enabled (is_enabled);
-- ALTER TABLE sys_menu ADD INDEX idx_type (type);
-- ALTER TABLE sys_menu ADD INDEX idx_code (code);

-- =============================================
-- 权限说明
-- =============================================
/*
权限设计说明：
1. 用户(User) <-> 角色(Role)：多对多关系
2. 角色(Role) <-> 菜单/权限(Menu)：多对多关系
3. 菜单类型：
   - 0: 目录（一级菜单）
   - 1: 菜单（二级菜单）
   - 2: 按钮（功能权限）
4. 权限字段(code)格式：
   - 模块:资源:操作
   - 例如：system:user:add 表示系统模块-用户资源-添加操作
*/

-- =============================================
-- 注意事项
-- =============================================
/*
1. 所有表使用InnoDB引擎，支持事务和外键
2. 字符集使用utf8mb4，支持emoji等特殊字符
3. 时间字段使用DATETIME而不是TIMESTAMP，避免2038年问题
4. 密码字段需要加密存储（BCrypt、SHA256等）
5. 建议定期备份数据库
6. 生产环境建议添加数据审计字段（创建人、修改人等）
*/