package com.funkey.web.rbac.controller;

import com.funkey.web.rbac.entity.SysMenu;
import com.funkey.web.rbac.repository.SysMenuRepository;
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
@RequestMapping("/api/rbac/sys-menus")
public class SysMenuController {

    private final SysMenuRepository sysMenuRepository;

    public SysMenuController(SysMenuRepository sysMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
    }

    @GetMapping
    public List<SysMenu> list() {
        return sysMenuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysMenu> get(@PathVariable Long id) {
        return sysMenuRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SysMenu create(@RequestBody SysMenu sysMenu) {
        return sysMenuRepository.save(sysMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysMenu> update(@PathVariable Long id, @RequestBody SysMenu sysMenu) {
        if (!sysMenuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysMenu.setMenuId(id);
        return ResponseEntity.ok(sysMenuRepository.save(sysMenu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sysMenuRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysMenuRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
