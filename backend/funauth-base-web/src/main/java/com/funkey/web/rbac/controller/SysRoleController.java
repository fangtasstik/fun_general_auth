package com.funkey.web.rbac.controller;

import com.funkey.web.rbac.entity.SysRole;
import com.funkey.web.rbac.repository.SysRoleRepository;
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
@RequestMapping("/api/rbac/sys-roles")
public class SysRoleController {

    private final SysRoleRepository sysRoleRepository;

    public SysRoleController(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    @GetMapping
    public List<SysRole> list() {
        return sysRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysRole> get(@PathVariable Long id) {
        return sysRoleRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SysRole create(@RequestBody SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysRole> update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        if (!sysRoleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysRole.setRoleId(id);
        return ResponseEntity.ok(sysRoleRepository.save(sysRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sysRoleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysRoleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
