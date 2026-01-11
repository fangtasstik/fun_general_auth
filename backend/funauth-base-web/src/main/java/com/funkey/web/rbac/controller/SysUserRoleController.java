package com.funkey.web.rbac.controller;

import com.funkey.web.rbac.entity.SysUserRole;
import com.funkey.web.rbac.repository.SysUserRoleRepository;
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
@RequestMapping("/api/rbac/sys-user-roles")
public class SysUserRoleController {

    private final SysUserRoleRepository sysUserRoleRepository;

    public SysUserRoleController(SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    @GetMapping
    public List<SysUserRole> list() {
        return sysUserRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysUserRole> get(@PathVariable Long id) {
        return sysUserRoleRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SysUserRole create(@RequestBody SysUserRole sysUserRole) {
        return sysUserRoleRepository.save(sysUserRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysUserRole> update(@PathVariable Long id, @RequestBody SysUserRole sysUserRole) {
        if (!sysUserRoleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysUserRole.setUserRoleId(id);
        return ResponseEntity.ok(sysUserRoleRepository.save(sysUserRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sysUserRoleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysUserRoleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
