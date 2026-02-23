package com.personal_project.ecommerce.service;

import com.personal_project.ecommerce.repository.UserLoginRepository;
import com.personal_project.ecommerce.dto.UserLoginDTO;

public class UserService {
    public String checkLogin(UserLoginDTO userLoginDTO) {
        if (new UserLoginRepository().isExisted(userLoginDTO)){
            return "Dang nhap thanh cong !!!";
        }else{
            return "Dang nhap khong thanh cong !";
        }
    }
}
