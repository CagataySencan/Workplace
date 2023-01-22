package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class ActualDAO extends BaseDAO<AktuelDTO> {
    @Override
    protected String collection() {
        return FirebaseCollections.ACTUALS;
    }

    @Override
    protected Class<AktuelDTO> type() {
        return AktuelDTO.class;
    }

    @Override
    void restoreInvalidFields(AktuelDTO dataToUpdate, AktuelDTO oldData) {
        dataToUpdate.setCreated(oldData.getCreated());
        dataToUpdate.setId(oldData.getId());
    }

    public List<AktuelDTO> getHist() throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<AktuelDTO> actuals = collection.orderBy("created", Query.Direction.DESCENDING).get().get().toObjects(type());

        if(CollectionUtils.isEmpty(actuals)) throw new SystemException("Sistem hatası", "Aktüel listesi alınırken bir sorun oluştu. Lütfen daha sonra tekrar deneyiniz.");

        return actuals;
    }

}
