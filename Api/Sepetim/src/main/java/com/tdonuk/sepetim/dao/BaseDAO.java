package com.tdonuk.sepetim.dao;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.tdonuk.dto.BaseDTO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.naming.OperationNotSupportedException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class BaseDAO<T extends BaseDTO> {
    abstract String collection();
    abstract Class<T> type();

    /**
     * this is used to prevent unwanted put request like updating the entity's id, creation date, etc..
     */
    abstract void restoreInvalidFields(T dataToUpdate, T oldData);

    protected Firestore firestore;

    @PostConstruct
    private void init() {
        firestore = FirestoreClient.getFirestore();
    }

    public T save(T dto) throws Exception {
        CollectionReference table = firestore.collection(collection());

        DocumentReference created = table.document();

        dto.setId(created.getId());

        WriteResult result = created.set(dto).get();

        return dto;
    }

    public T find(final String id) throws Exception {
        CollectionReference table = firestore.collection(collection());

        return table.document(id).get().get().toObject(type());
    }

    public List<T> findAll(String field, Object begin, Object end) throws Exception {
        CollectionReference table = firestore.collection(collection());

        return table.whereGreaterThanOrEqualTo(field, begin).whereLessThan(field, end).get().get().toObjects(type());
    }

    public List<T> findAll(String field, Object value) throws Exception {
        return firestore.collection(collection()).whereEqualTo(field,value).get().get().toObjects(type());
    }

    public List<T> findAll(String field, List<?> in) throws Exception {
        return firestore.collection(collection()).whereIn(field, in).get().get().toObjects(type());
    }

    public List<T> save(List<T> dtos) throws Exception {
        throw new OperationNotSupportedException("This feature is not implemented yet");
    }

    public Boolean delete(T dto) throws Exception {
        return delete(dto.getId());
    }

    public Boolean delete(String id) throws Exception {
        CollectionReference table = firestore.collection(collection());

        DocumentReference toDelete = table.document(id);

        WriteResult result = toDelete.delete().get();

        return Boolean.TRUE;
    }

    public Boolean delete(List<T> dtos) throws Exception {
        throw new OperationNotSupportedException("This feature is not implemented yet");
    }

    public T update(String id, T dto) throws Exception {
        CollectionReference table = firestore.collection(collection());

        DocumentReference toUpdate = table.document(id);

        T old = toUpdate.get().get().toObject(type());

        restoreInvalidFields(dto, old);

        dto.setLastUpdated(new Date());

        WriteResult result = toUpdate.set(dto).get();

        return dto;
    }

    public T update(String id, Map<String, Object> fields) throws Exception {
        CollectionReference table = firestore.collection(collection());

        DocumentReference toUpdate = table.document(id);

        fields.put("lastUpdated", new Date());

        WriteResult result = toUpdate.update(fields).get();

        return toUpdate.get().get().toObject(type());
    }
}
