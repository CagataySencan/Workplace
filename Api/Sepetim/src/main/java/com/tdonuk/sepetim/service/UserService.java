package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.CurrentActualDTO;
import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.sepetim.dao.BaseDAO;
import com.tdonuk.sepetim.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<UserDTO> {
    @Autowired
    private UserDAO userDAO;

    @Override
    protected BaseDAO dao() {
        return userDAO;
    }

    @Override
    protected UserDTO concrete(BaseDTO base) {
        if(base instanceof CurrentActualDTO) return (UserDTO) base;
        else return null;
    }

    @Override
    protected List<UserDTO> concrete(List<BaseDTO> base) {
        return (List<UserDTO>)(List<?>) base;
    }

    @Override
    protected List<String> invalidFieldsForUpdate() {
        return List.of(
                "id",
                "created",
                "email",
                "lastLogin",
                "birthDate",
                "phone"
        );
    }

    public UserDTO findByEmail(String email) throws Exception {
        return userDAO.findByEmail(email);
    }
}
