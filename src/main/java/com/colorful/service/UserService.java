package com.colorful.service;

import com.colorful.dao.UserDAO;
import com.colorful.model.User;
import com.colorful.util.MD5Util;

/**
 * @author colorful@TaleLin
 */
public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        String md5Password = MD5Util.md5(password);
        return userDAO.selectByUserNameAndPassword(username, md5Password);
    }

}
