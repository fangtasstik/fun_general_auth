-- =============================================
-- RBAC access control system MySQL script
-- MySQL Version: 5.7+
-- Character Set: utf8mb4
-- Collation: utf8mb4_unicode_ci
-- Storage Engine: InnoDB
-- =============================================

-- Create database (optional)
CREATE DATABASE IF NOT EXISTS fun_auth_db
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE fun_auth_db;

-- =============================================
-- 1. Users table
-- =============================================
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'User ID',
    username VARCHAR(64) NOT NULL COMMENT 'Login account',
    password VARCHAR(128) NOT NULL COMMENT 'Login password',
    phone VARCHAR(13) DEFAULT NULL COMMENT 'Phone',
    email VARCHAR(36) DEFAULT NULL COMMENT 'Email',
    sex VARCHAR(2) DEFAULT '0' COMMENT 'Gender: 0=Male, 1=Female',
    is_admin TINYINT(1) DEFAULT 0 COMMENT 'Is super admin: 1=Yes, 0=No',
    is_account_non_expired TINYINT(1) DEFAULT 1 COMMENT 'Account expired: 1=No, 0=Yes',
    is_account_non_locked TINYINT(1) DEFAULT 1 COMMENT 'Account locked: 1=No, 0=Yes',
    is_credentials_non_expired TINYINT(1) DEFAULT 1 COMMENT 'Password expired: 1=No, 0=Yes',
    is_enabled TINYINT(1) DEFAULT 1 COMMENT 'Account enabled: 1=Yes, 0=Deleted',
    nick_name VARCHAR(36) DEFAULT NULL COMMENT 'Display name',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Created time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Updated time',
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_username (username),
    KEY idx_phone (phone),
    KEY idx_email (email),
    KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Users';

-- =============================================
-- 2. Roles table
-- =============================================
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    role_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Role ID',
    role_name VARCHAR(64) NOT NULL COMMENT 'Role name',
    type VARCHAR(10) DEFAULT NULL COMMENT 'Extension field',
    remark VARCHAR(128) DEFAULT NULL COMMENT 'Remarks',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Created time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Updated time',
    PRIMARY KEY (role_id),
    UNIQUE KEY uk_role_name (role_name),
    KEY idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Roles';

-- =============================================
-- 3. Menus table
-- =============================================
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu (
    menu_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Menu ID',
    parent_id INT(11) UNSIGNED DEFAULT 0 COMMENT 'Parent menu ID, 0 means top-level',
    title VARCHAR(64) NOT NULL COMMENT 'Menu title',
    code VARCHAR(64) DEFAULT NULL COMMENT 'Permission code',
    name VARCHAR(36) DEFAULT NULL COMMENT 'Route name',
    path VARCHAR(36) DEFAULT NULL COMMENT 'Route path',
    url VARCHAR(128) DEFAULT NULL COMMENT 'Component path',
    type VARCHAR(2) NOT NULL DEFAULT '1' COMMENT 'Type: 0=Directory, 1=Menu, 2=Button',
    icon VARCHAR(36) DEFAULT NULL COMMENT 'Menu icon',
    parent_name VARCHAR(64) DEFAULT NULL COMMENT 'Parent menu name',
    order_num INT(11) DEFAULT 0 COMMENT 'Sort order',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Created time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Updated time',
    PRIMARY KEY (menu_id),
    KEY idx_parent_id (parent_id),
    KEY idx_order_num (order_num)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Menus';

-- =============================================
-- 4. User-role relation table
-- =============================================
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    user_role_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    user_id INT(11) UNSIGNED NOT NULL COMMENT 'User ID',
    role_id INT(11) UNSIGNED NOT NULL COMMENT 'Role ID',
    PRIMARY KEY (user_role_id),
    UNIQUE KEY uk_user_role (user_id, role_id),
    KEY idx_user_id (user_id),
    KEY idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='User-role relation';

-- =============================================
-- 5. Role-menu relation table
-- =============================================
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu (
    role_menu_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    role_id INT(11) UNSIGNED NOT NULL COMMENT 'Role ID',
    menu_id INT(11) UNSIGNED NOT NULL COMMENT 'Menu ID',
    PRIMARY KEY (role_menu_id),
    UNIQUE KEY uk_role_menu (role_id, menu_id),
    KEY idx_role_id (role_id),
    KEY idx_menu_id (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Role-menu relation';

-- =============================================
-- Seed data examples
-- =============================================

-- Insert super admin user (password should be hashed; example only)
INSERT INTO sys_user (username, password, nick_name, is_admin, phone, email) VALUES
('admin', 'hashed_password_here', 'Super Admin', 1, '13800138000', 'admin@example.com');

-- Insert base roles
INSERT INTO sys_role (role_name, remark) VALUES
('ROLE_ADMIN', 'System administrator'),
('ROLE_USER', 'Standard user'),
('ROLE_GUEST', 'Guest');

-- Insert base menus
INSERT INTO sys_menu (parent_id, title, code, type, icon, order_num) VALUES
(0, 'System Management', 'system', '0', 'setting', 1),
(1, 'User Management', 'system:user', '1', 'user', 1),
(1, 'Role Management', 'system:role', '1', 'team', 2),
(1, 'Menu Management', 'system:menu', '1', 'menu', 3),
(2, 'Add User', 'system:user:add', '2', NULL, 1),
(2, 'Edit User', 'system:user:edit', '2', NULL, 2),
(2, 'Delete User', 'system:user:delete', '2', NULL, 3);

-- Link super admin user to admin role
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1);

-- Grant admin role all menu permissions
INSERT INTO sys_role_menu (role_id, menu_id)
SELECT 1, menu_id FROM sys_menu;

-- =============================================
-- Views (optional)
-- =============================================

-- User roles view
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

-- Role permissions view
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
-- Stored procedures (optional)
-- =============================================

DELIMITER //

-- Get all permissions for a user
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

-- Get menu tree (MySQL 5.7 compatible version using temporary table)
CREATE PROCEDURE sp_get_menu_tree(IN p_parent_id INT)
BEGIN
    -- Create temporary table to store results
    CREATE TEMPORARY TABLE temp_menu_tree (
        menu_id INT,
        parent_id INT,
        title VARCHAR(64),
        code VARCHAR(64),
        path VARCHAR(36),
        url VARCHAR(128),
        type VARCHAR(2),
        icon VARCHAR(36),
        order_num INT,
        level INT
    );
    
    -- Insert root level
    INSERT INTO temp_menu_tree
    SELECT menu_id, parent_id, title, code, path, url, type, icon, order_num, 0
    FROM sys_menu 
    WHERE parent_id = p_parent_id;
    
    -- Loop to get all levels (max 10 levels to prevent infinite loop)
    SET @level = 0;
    WHILE @level < 10 AND ROW_COUNT() > 0 DO
        INSERT INTO temp_menu_tree
        SELECT m.menu_id, m.parent_id, m.title, m.code, m.path, m.url, m.type, m.icon, m.order_num, @level + 1
        FROM sys_menu m
        INNER JOIN temp_menu_tree t ON m.parent_id = t.menu_id AND t.level = @level
        WHERE NOT EXISTS (SELECT 1 FROM temp_menu_tree t2 WHERE t2.menu_id = m.menu_id);
        
        SET @level = @level + 1;
    END WHILE;
    
    -- Return results
    SELECT * FROM temp_menu_tree ORDER BY level, order_num;
    
    -- Clean up
    DROP TEMPORARY TABLE temp_menu_tree;
END//

DELIMITER ;

-- =============================================
-- Index optimization suggestions
-- =============================================

-- Add indexes for frequently queried fields
-- ALTER TABLE sys_user ADD INDEX idx_is_enabled (is_enabled);
-- ALTER TABLE sys_menu ADD INDEX idx_type (type);
-- ALTER TABLE sys_menu ADD INDEX idx_code (code);

-- =============================================
-- Permissions notes
-- =============================================
/*
Permission design notes:
1. User <-> Role: many-to-many
2. Role <-> Menu/Permission: many-to-many
3. Menu type:
   - 0: Directory (top-level)
   - 1: Menu (second-level)
   - 2: Button (action permission)
4. Permission code format:
   - module:resource:action
   - Example: system:user:add means module=system, resource=user, action=add
*/

-- =============================================
-- Notes
-- =============================================
/*
1. All tables use InnoDB engine for better performance
2. Character set uses utf8mb4 to support emoji and special characters
3. Use DATETIME instead of TIMESTAMP to avoid the 2038 limitation
4. Passwords should be stored hashed (BCrypt, SHA256, etc.)
5. Back up the database regularly
6. Foreign key constraints removed for better performance and scalability
7. Data integrity should be maintained at application level
8. Consider audit fields in production (created_by, updated_by, etc.)
*/
