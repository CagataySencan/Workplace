package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.*;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.exception.SystemException;
import com.tdonuk.sepetim.cache.Cache;
import com.tdonuk.sepetim.cache.CacheParams;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import com.tdonuk.util.date.DateUtils;
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
public class DiscountDAO extends BaseDAO<DiscountDTO> {
    @Override
    protected String collection() {
        return FirebaseCollections.DISCOUNTS;
    }

    @Override
    protected Class<DiscountDTO> type() {
        return DiscountDTO.class;
    }

    @Override
    void restoreInvalidFields(DiscountDTO dataToUpdate, DiscountDTO oldData) {
        dataToUpdate.setCreated(oldData.getCreated());
        dataToUpdate.setId(oldData.getId());
    }

    public List<DiscountDTO> getHist() throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<DiscountDTO> discounts = Cache.getDiscountHist();

        if(Objects.isNull(discounts)) discounts = collection.orderBy("created", Query.Direction.DESCENDING).get().get().toObjects(type());

        Cache.systemCache.put(CacheParams.DISCOUNT_HIST, discounts);

        return new ArrayList<>(discounts);
    }

    public List<DiscountDTO> updateHist(List<DiscountDTO> hist) throws Exception {
        CollectionReference collection = firestore.collection(collection());

        List<DiscountDTO> currentHist = getHist();

        WriteBatch batch = firestore.batch();

        // delete outdated
        List<DiscountDTO> outdated = hist.stream().filter(a -> DateUtils.dateToLocalDate(a.getBeginDate()).isBefore(DateUtils.minusDays(30))).collect(Collectors.toList());
        for(DiscountDTO discount : outdated) {
            if(StringUtils.isBlank(discount.getId())) throw new SystemException("ID is null", "Can not delete discount: ID is null");

            batch.delete(collection.document(discount.getId()));

            hist.remove(discount);
        }

        // delete ended
        List<DiscountDTO> ended = hist.stream().filter(a -> Objects.nonNull(a.getBeginDate()) && Objects.nonNull(a.getEndDate())).filter(a -> DateUtils.dateToLocalDate(a.getEndDate()).isBefore(LocalDate.now())).collect(Collectors.toList());
        for(DiscountDTO discount : ended) {
            if(StringUtils.isBlank(discount.getId())) throw new SystemException("ID is null", "Can not delete discount: ID is null");

            batch.delete(collection.document(discount.getId()));

            hist.remove(discount);
        }

        DocumentReference reference;
        for(DiscountDTO discount : hist) {
            if(StringUtils.isBlank(discount.getId())) throw new SystemException("ID is null", "Can not create discount: ID is null");

            reference = collection.document(discount.getId());

            if(currentHist.contains(discount)) {
                DiscountDTO current = currentHist.get(currentHist.indexOf(discount));

                // restore uneditable fields to prevent unwanted update operations
                discount.setCreated(current.getCreated());
                discount.setVendor(current.getVendor());

                discount.setLastUpdated(new Date());
            }
            else discount.setCreated(new Date());

            batch.set(reference, discount, SetOptions.merge());
        }

        List<WriteResult> writeResults = batch.commit().get();

        return hist;
    }

}
