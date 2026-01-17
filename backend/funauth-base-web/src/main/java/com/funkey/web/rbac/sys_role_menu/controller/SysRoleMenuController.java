package com.funkey.web.rbac.sys_role_menu.controller;

import com.funkey.utils.Result;
import com.funkey.web.rbac.sys_role_menu.entity.SysRoleMenu;
import com.funkey.web.rbac.sys_role_menu.repository.SysRoleMenuRepository;
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
@RequestMapping("/api/rbac/sys-role-menus")
public class SysRoleMenuController {

    private final SysRoleMenuRepository sysRoleMenuRepository;

    public SysRoleMenuController(SysRoleMenuRepository sysRoleMenuRepository) {
        this.sysRoleMenuRepository = sysRoleMenuRepository;
    }

    @GetMapping
    public Result<List<SysRoleMenu>> list() {
        return Result.ok(sysRoleMenuRepository.findAll());
    }

    @GetMapping("/{id}")
    public Result<SysRoleMenu> get(@PathVariable Long id) {
        return sysRoleMenuRepository.findById(id)
            .map(Result::ok)
            .orElseGet(() -> Result.<SysRoleMenu>fail().message("Data not found"));
    }

    @PostMapping
    public Result<SysRoleMenu> create(@RequestBody SysRoleMenu sysRoleMenu) {
        return Result.ok(sysRoleMenuRepository.save(sysRoleMenu));
    }

    @PutMapping("/{id}")
    public Result<SysRoleMenu> update(@PathVariable Long id, @RequestBody SysRoleMenu sysRoleMenu) {
        if (!sysRoleMenuRepository.existsById(id)) {
            return Result.<SysRoleMenu>fail().message("Data not found");
        }
        sysRoleMenu.setRoleMenuId(id);
        return Result.ok(sysRoleMenuRepository.save(sysRoleMenu));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (!sysRoleMenuRepository.existsById(id)) {
            Result<Void> result = Result.fail();
            return result.message("Data not found");
        }
        sysRoleMenuRepository.deleteById(id);
        Result<Void> result = Result.ok();
        return result.message("Data Deleted");
    }
}


