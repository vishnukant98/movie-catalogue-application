package com.vishnu.userAuthentication.service;

import com.vishnu.userAuthentication.Model.AppUser;
import com.vishnu.userAuthentication.Model.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
