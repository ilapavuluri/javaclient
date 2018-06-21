package com.dev.dao;

import java.util.List;
import com.dev.model.User;

public interface UserDaoInterface {
List<User> getUsers();

User getUserByName(String userName);

void createUser(User user);
}
