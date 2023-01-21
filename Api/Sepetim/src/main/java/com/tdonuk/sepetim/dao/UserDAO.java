package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.CollectionReference;
import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.exception.ConflictException;
import com.tdonuk.exception.NotFoundException;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tdonuk.sepetim.service.constants.UserFields.EMAIL;

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

    @Override
    void restoreInvalidFields(UserDTO dataToUpdate, UserDTO oldData) {
        dataToUpdate.setId(oldData.getId());
        dataToUpdate.setLastLogin(oldData.getLastLogin());
        dataToUpdate.setEmail(oldData.getEmail());
        dataToUpdate.setPassword(oldData.getPassword());
        dataToUpdate.setBirthDate(oldData.getBirthDate());
        dataToUpdate.setPhone(oldData.getPhone());
    }

    public UserDTO findByEmail(final String email) throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<UserDTO> users = collection.whereEqualTo(EMAIL, email).get().get().toObjects(type());

        if(users.isEmpty()) throw new NotFoundException("Kullanıcı bulunamadı", "Verilen bilgilere sahip kullanıcı sistemde bulunmamaktadır");
        if(users.size() > 1) throw new ConflictException("Çakışma hatası", "Verilen bilgiler birden fazla kullanıcı ile eşleşmektedir");

        return users.get(0);
    }
}
