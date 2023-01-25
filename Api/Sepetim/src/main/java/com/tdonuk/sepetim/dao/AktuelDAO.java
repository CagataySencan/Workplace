package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.*;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.cache.Cache;
import com.tdonuk.sepetim.cache.CacheParams;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import com.tdonuk.util.text.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

        List<AktuelDTO> aktuels = Cache.getAktuelHist();

        if(Objects.isNull(aktuels)) aktuels = collection.orderBy("created", Query.Direction.DESCENDING).get().get().toObjects(type());

        Cache.systemCache.put(CacheParams.AKTUEL_HIST, aktuels);

        return new ArrayList<>(aktuels);
    }

    public List<AktuelDTO> updateHist(List<AktuelDTO> hist) throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<AktuelDTO> currentHist = getHist();

        WriteBatch batch = firestore.batch();

        // delete outdated
        List<AktuelDTO> outdated = hist.stream().filter(a -> LocalDate.ofInstant(a.getDate().toInstant(), ZoneId.systemDefault()).isBefore(LocalDate.now().minusDays(30))).collect(Collectors.toList());
        for(AktuelDTO aktuel : outdated) {
            if(StringUtils.isBlank(aktuel.getId())) throw new SystemException("ID is null", "Can not delete aktuel: ID is null");

            batch.delete(collection.document(aktuel.getId()));

            hist.remove(aktuel);
        }

        DocumentReference reference;
        for(AktuelDTO aktuel : hist) {
            if(StringUtils.isBlank(aktuel.getId())) throw new SystemException("ID is null", "Can not create aktuel: ID is null");

            reference = collection.document(aktuel.getId());

            if(currentHist.contains(aktuel)) {
                AktuelDTO current = currentHist.get(currentHist.indexOf(aktuel));

                // restore uneditable fields to prevent unwanted update operations
                aktuel.setCreated(current.getCreated());
                aktuel.setVendor(current.getVendor());

                aktuel.setLastUpdated(new Date());
            }
            else aktuel.setCreated(new Date());

            batch.set(reference, aktuel, SetOptions.merge());
        }

        List<WriteResult> writeResults = batch.commit().get();

        return hist;
    }

}
