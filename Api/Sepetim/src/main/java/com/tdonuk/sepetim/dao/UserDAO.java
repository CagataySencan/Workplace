package com.tdonuk.sepetim.dao;

import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<UserDTO> {
    @Override
    protected String collection() {
        return FirebaseCollections.USERS;
    }

    @Override
    protected Class<UserDTO> type() {
        return UserDTO.class;
    }
}
