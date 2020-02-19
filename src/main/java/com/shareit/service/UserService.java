package com.shareit.service;

import com.shareit.exception.NotFoundException;
import com.shareit.model.auth.TrustedUser;
import com.shareit.model.entities.Post;
import com.shareit.model.entities.User;
import com.shareit.model.repository.TrustedUserRepo;
import com.shareit.model.repository.UserRepo;
import com.shareit.util.UserSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrustedUserRepo trustedRepo;

    public User persistUser(User user) {
        TrustedUser trusted = trustedRepo.findByUsername(user.getEmail())
                .orElseThrow(() -> new NotFoundException(String.format("Cant't find trusted user: %s",
                        user.getEmail())));
        User savedUser = userRepo.save(user);
        trusted.setUser(savedUser);
        trustedRepo.save(trusted);
        return savedUser;
    }

    public User getUser(String id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("user_id" + id));
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user_email" + email));
    }

    public User updateUser(String id, @Valid UserSection section, @Valid User user) {
        User targetUser = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("user_id" + id));
        User updatedTargetUser = updateValidFields(section, targetUser, user);
        return userRepo.save(updatedTargetUser);
    }

    private User updateValidFields(UserSection section, User targetUser, User updatedUser) {
        switch (section) {
            case email:
                targetUser.setEmail(updatedUser.getEmail());
                break;
            case account:
                targetUser.setAccount(updatedUser.getAccount());
                break;
            case profile:
                targetUser.setProfile(updatedUser.getProfile());
                break;
            case posts:
                List<Post> posts = updatedUser.getPosts();
                if (posts.isEmpty())
                    throw new IllegalArgumentException("Empty posts data");
                targetUser.appendPost(posts.get(0));
                break;
            case board:
                targetUser.setBoard(updatedUser.getBoard());
                break;
            default:
                throw new NotFoundException(String.format("endpoint: /%s", section));
        }
        return targetUser;
    }
}
