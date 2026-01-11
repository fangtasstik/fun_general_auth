package com.funkey.web.rbac.controller;

import com.funkey.web.rbac.entity.SysUser;
import com.funkey.web.rbac.repository.SysUserRepository;
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
@RequestMapping("/api/rbac/sys-users")
public class SysUserController {

    private final SysUserRepository sysUserRepository;

    public SysUserController(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @GetMapping
    public List<SysUser> list() {
        return sysUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SysUser> get(@PathVariable Long id) {
        return sysUserRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SysUser create(@RequestBody SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SysUser> update(@PathVariable Long id, @RequestBody SysUser sysUser) {
        if (!sysUserRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysUser.setUserId(id);
        return ResponseEntity.ok(sysUserRepository.save(sysUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sysUserRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sysUserRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
