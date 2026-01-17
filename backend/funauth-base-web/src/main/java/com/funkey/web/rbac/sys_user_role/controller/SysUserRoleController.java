package com.funkey.web.rbac.sys_user_role.controller;

import com.funkey.utils.Result;
import com.funkey.web.rbac.sys_user_role.entity.SysUserRole;
import com.funkey.web.rbac.sys_user_role.repository.SysUserRoleRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rbac/sys-user-roles")
public class SysUserRoleController {

    private final SysUserRoleRepository sysUserRoleRepository;

    public SysUserRoleController(SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    @GetMapping
    public Result<List<SysUserRole>> list() {
        return Result.ok(sysUserRoleRepository.findAll());
    }

    @GetMapping("/{id}")
    public Result<SysUserRole> get(@PathVariable Long id) {
        return sysUserRoleRepository.findById(id)
            .map(Result::ok)
            .orElseGet(() -> Result.<SysUserRole>fail().message("Data not found"));
    }

    @PostMapping
    public Result<SysUserRole> create(@RequestBody SysUserRole sysUserRole) {
        return Result.ok(sysUserRoleRepository.save(sysUserRole));
    }

    @PutMapping("/{id}")
    public Result<SysUserRole> update(@PathVariable Long id, @RequestBody SysUserRole sysUserRole) {
        if (!sysUserRoleRepository.existsById(id)) {
            return Result.<SysUserRole>fail().message("Data not found");
        }
        sysUserRole.setUserRoleId(id);
        return Result.ok(sysUserRoleRepository.save(sysUserRole));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (!sysUserRoleRepository.existsById(id)) {
            Result<Void> result = Result.fail();
            return result.message("Data not found");
        }
        sysUserRoleRepository.deleteById(id);
        Result<Void> result = Result.ok();
        return result.message("Data Deleted");
    }
}


