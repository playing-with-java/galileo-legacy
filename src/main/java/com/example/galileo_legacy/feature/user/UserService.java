package com.example.galileo_legacy.feature.user;

import com.example.galileo_legacy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> listAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public User create(User user) {
        user.setId(null);
        return repository.save(user);
    }

    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        User existing = getById(id);
        repository.delete(existing);
    }
}
