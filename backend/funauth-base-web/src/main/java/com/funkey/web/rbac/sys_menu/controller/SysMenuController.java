package com.funkey.web.rbac.sys_menu.controller;

import com.funkey.utils.Result;
import com.funkey.web.rbac.sys_menu.entity.SysMenu;
import com.funkey.web.rbac.sys_menu.repository.SysMenuRepository;
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
@RequestMapping("/api/rbac/sys-menus")
public class SysMenuController {

    private final SysMenuRepository sysMenuRepository;

    public SysMenuController(SysMenuRepository sysMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
    }

    @GetMapping
    public Result<List<SysMenu>> list() {
        return Result.ok(sysMenuRepository.findAll());
    }

    @GetMapping("/{id}")
    public Result<SysMenu> get(@PathVariable Long id) {
        return sysMenuRepository.findById(id)
            .map(Result::ok)
            .orElseGet(() -> Result.<SysMenu>fail().message("数据不存在"));
    }

    @PostMapping
    public Result<SysMenu> create(@RequestBody SysMenu sysMenu) {
        return Result.ok(sysMenuRepository.save(sysMenu));
    }

    @PutMapping("/{id}")
    public Result<SysMenu> update(@PathVariable Long id, @RequestBody SysMenu sysMenu) {
        if (!sysMenuRepository.existsById(id)) {
            return Result.<SysMenu>fail().message("数据不存在");
        }
        sysMenu.setMenuId(id);
        return Result.ok(sysMenuRepository.save(sysMenu));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (!sysMenuRepository.existsById(id)) {
            Result<Void> result = Result.fail();
            return result.message("数据不存在");
        }
        sysMenuRepository.deleteById(id);
        Result<Void> result = Result.ok();
        return result.message("删除成功");
    }
}


