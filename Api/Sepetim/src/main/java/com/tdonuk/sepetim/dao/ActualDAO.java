package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import com.tdonuk.dto.domain.CurrentActualDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class ActualDAO extends BaseDAO<CurrentActualDTO> {
    @Override
    protected String collection() {
        return FirebaseCollections.ACTUALS;
    }

    @Override
    protected Class<CurrentActualDTO> type() {
        return CurrentActualDTO.class;
    }

    @Override
    void restoreInvalidFields(CurrentActualDTO dataToUpdate, CurrentActualDTO oldData) {
        dataToUpdate.setCreated(oldData.getCreated());
        dataToUpdate.setId(oldData.getId());
    }

    public List<CurrentActualDTO> getHist() throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<CurrentActualDTO> actuals = collection.orderBy("created", Query.Direction.DESCENDING).get().get().toObjects(type());

        if(CollectionUtils.isEmpty(actuals)) throw new SystemException("Sistem hatası", "Aktüel listesi alınırken bir sorun oluştu. Lütfen daha sonra tekrar deneyiniz.");

        return actuals;
    }

    public CurrentActualDTO getCurrent() throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<CurrentActualDTO> limited = collection.orderBy("created", Query.Direction.DESCENDING).limitToLast(1).get().get().toObjects(type());

        if(CollectionUtils.isEmpty(limited)) throw new SystemException("Sistem hatası", "Mevcut aktüel alınırken bir sorun oluştu. Lütfen daha sonra tekrar deneyiniz.");

        return limited.get(0);
    }
}
