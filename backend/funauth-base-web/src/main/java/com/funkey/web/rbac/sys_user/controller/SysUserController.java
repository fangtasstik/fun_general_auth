package com.funkey.web.rbac.sys_user.controller;

import com.funkey.utils.Result;
import com.funkey.web.rbac.sys_user.entity.SysUser;
import com.funkey.web.rbac.sys_user.repository.SysUserRepository;
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
@RequestMapping("/api/rbac/sys-users")
public class SysUserController {

    private final SysUserRepository sysUserRepository;

    public SysUserController(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @GetMapping
    public Result<List<SysUser>> list() {
        return Result.ok(sysUserRepository.findAll());
    }

    @GetMapping("/{id}")
    public Result<SysUser> get(@PathVariable Long id) {
        return sysUserRepository.findById(id)
            .map(Result::ok)
            .orElseGet(() -> Result.<SysUser>fail().message("数据不存在"));
    }

    @PostMapping
    public Result<SysUser> create(@RequestBody SysUser sysUser) {
        return Result.ok(sysUserRepository.save(sysUser));
    }

    @PutMapping("/{id}")
    public Result<SysUser> update(@PathVariable Long id, @RequestBody SysUser sysUser) {
        if (!sysUserRepository.existsById(id)) {
            return Result.<SysUser>fail().message("数据不存在");
        }
        sysUser.setUserId(id);
        return Result.ok(sysUserRepository.save(sysUser));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (!sysUserRepository.existsById(id)) {
            Result<Void> result = Result.fail();
            return result.message("数据不存在");
        }
        sysUserRepository.deleteById(id);
        Result<Void> result = Result.ok();
        return result.message("删除成功");
    }
}


