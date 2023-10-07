package com.kasia.sickstory.user;

import com.kasia.sickstory.user.User;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Transactional
public class UserController {

    @Resource
    private UserDao userDao;

    @PostMapping("/user/add")
    @ResponseBody
    public User create(@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User();
        Optional.ofNullable(firstName).ifPresent(user::setFirstName);
        Optional.ofNullable(lastName).ifPresent(user::setLastName);
        userDao.createUser(user);
        return user;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable long id) {
        User user = userDao.findById(id);
        return user;
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public void updateUser(@PathVariable long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        User user = userDao.findById(id);
        Optional.ofNullable(firstName).ifPresent(user::setFirstName);
        Optional.ofNullable(lastName).ifPresent(user::setLastName);
        userDao.update(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        User user = userDao.findById(id);
        userDao.delete(user);
    }
}
