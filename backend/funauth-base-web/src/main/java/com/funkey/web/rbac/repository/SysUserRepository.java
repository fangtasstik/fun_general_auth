package com.funkey.web.rbac.repository;

import com.funkey.web.rbac.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}
