package com.onmylevel.banking.service;

import com.onmylevel.banking.entity.User;
import com.onmylevel.banking.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final AuditService auditService;

    public UserService(UserRepository userRepository, AuditService auditService) {
        this.userRepository = userRepository;
        this.auditService = auditService;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    @Transactional
    public User createUser(String name) {
        log.info("Creating user: {}", name);
        User user = new User(name);
        User saved = userRepository.save(user);
        auditService.log("User", saved.getId(), "CREATE", "Created: " + name);
        return saved;
    }

    @Transactional
    public User updateUser(Long id, String name) {
        log.info("Updating user: {}", id);
        User user = getUserById(id);
        String oldName = user.getName();
        user.setName(name);
        User updated = userRepository.save(user);
        auditService.log("User", id, "UPDATE", oldName + " -> " + name);
        return updated;
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user: {}", id);
        User user = getUserById(id);
        auditService.log("User", id, "DELETE", "Deleted: " + user.getName());
        userRepository.delete(user);
    }
}
