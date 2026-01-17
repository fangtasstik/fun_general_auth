package com.funkey.web.rbac.sys_user.repository;

import com.funkey.web.rbac.sys_user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}


