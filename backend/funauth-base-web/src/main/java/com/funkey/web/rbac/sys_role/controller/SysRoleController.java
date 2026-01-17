package com.funkey.web.rbac.sys_role.controller;

import com.funkey.utils.Result;
import com.funkey.web.rbac.sys_role.dto.RoleParm;
import com.funkey.web.rbac.sys_role.entity.SysRole;
import com.funkey.web.rbac.sys_role.repository.SysRoleRepository;
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
@RequestMapping("/api/rbac/sys-roles")
public class SysRoleController {

    private final SysRoleRepository sysRoleRepository;

    public SysRoleController(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    // QUERY

    @GetMapping
    public Result<List<SysRole>> list() {
        return Result.ok(sysRoleRepository.findAll());
    }

    @GetMapping("/{id}")
    public Result<SysRole> get(@PathVariable Long id) {
        return sysRoleRepository.findById(id)
            .map(Result::ok)
            .orElseGet(() -> Result.<SysRole>fail().message("Data not found"));
    }

    // COMMAND

    @PostMapping
    public Result<SysRole> create(@RequestBody SysRole sysRole) {
        return Result.ok(sysRoleRepository.save(sysRole));
    }

    @PutMapping("/{id}")
    public Result<SysRole> update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        if (!sysRoleRepository.existsById(id)) {
            return Result.<SysRole>fail().message("Data not found");
        }
        sysRole.setRoleId(id);
        return Result.ok(sysRoleRepository.save(sysRole));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (!sysRoleRepository.existsById(id)) {
            Result<Void> result = Result.fail();
            return result.message("Data not found");
        }
        sysRoleRepository.deleteById(id);
        Result<Void> result = Result.ok();
        return result.message("Data Deleted");
    }
}


