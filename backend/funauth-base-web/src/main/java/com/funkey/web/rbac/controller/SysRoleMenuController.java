package com.funkey.web.rbac.controller;

import com.funkey.web.rbac.entity.SysRoleMenu;
import com.funkey.web.rbac.repository.SysRoleMenuRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
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
    public List<SysRoleMenu> list() {
        return sysRoleMenuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysRoleMenu> get(@PathVariable Long id) {
        return sysRoleMenuRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SysRoleMenu create(@RequestBody SysRoleMenu sysRoleMenu) {
        return sysRoleMenuRepository.save(sysRoleMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysRoleMenu> update(@PathVariable Long id, @RequestBody SysRoleMenu sysRoleMenu) {
        if (!sysRoleMenuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysRoleMenu.setRoleMenuId(id);
        return ResponseEntity.ok(sysRoleMenuRepository.save(sysRoleMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sysRoleMenuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysRoleMenuRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
