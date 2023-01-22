package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.*;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import com.tdonuk.util.text.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class AktuelDAO extends BaseDAO<AktuelDTO> {
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

        return actuals;
    }

    public List<AktuelDTO> updateHist(List<AktuelDTO> hist) throws Exception {
        CollectionReference collection = firestore.collection(collection());

        WriteBatch batch = firestore.batch();

        // delete outdated
        List<AktuelDTO> outdated = hist.stream().filter(a -> LocalDate.ofInstant(a.getDate().toInstant(), ZoneId.systemDefault()).isBefore(LocalDate.now().minusDays(30))).collect(Collectors.toList());
        for(AktuelDTO aktuel : outdated) {
            if(StringUtils.isBlank(aktuel.getId())) throw new SystemException("ID is null", "Can not delete aktuel: ID is null");

            batch.delete(collection.document(aktuel.getId()));

            hist.remove(aktuel);
        }

        DocumentSnapshot snapshot;
        DocumentReference reference;
        for(AktuelDTO aktuel : hist) {
            if(StringUtils.isBlank(aktuel.getId())) throw new SystemException("ID is null", "Can not create aktuel: ID is null");

            reference = collection.document(aktuel.getId());
            snapshot = reference.get().get();

            if(snapshot.exists()) aktuel.setLastUpdated(new Date());
            else aktuel.setCreated(new Date());

            batch.set(reference, aktuel);
        }

        List<WriteResult> writeResults = batch.commit().get();

        return getHist();
    }

}
