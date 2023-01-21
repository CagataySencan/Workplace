package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.CollectionReference;
import com.tdonuk.dto.domain.user.UserDTO;
import com.tdonuk.exception.BadRequestException;
import com.tdonuk.exception.ConflictException;
import com.tdonuk.exception.NotFoundException;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import com.tdonuk.sepetim.security.Context;
import com.tdonuk.util.text.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public UserDTO findByEmail(final String email) throws UsernameNotFoundException {
        CollectionReference collection = firestore.collection(collection());

        List<UserDTO> users = null;
        try {
            users = collection.whereEqualTo(EMAIL, email).get().get().toObjects(type());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        if(users.isEmpty()) throw new UsernameNotFoundException("Verilen bilgilere sahip kullanıcı sistemde bulunmamaktadır");
        if(users.size() > 1) throw new RuntimeException("Verilen bilgiler birden fazla kullanıcı ile eşleşmektedir");

        return users.get(0);
    }

    public UserDTO getAuthenticatedUser() throws Exception {
        String loggedUser = Context.loggedEmail();

        if(StringUtils.isBlank(loggedUser)) throw new BadRequestException("İşleminiz sırasında bir sorun oluştu", "Bilinmeyen bir sorun oluştu, lütfen tekrar giriş yaparak oturumunuzu yenileyiniz");

        return findByEmail(loggedUser);
    }
}
