package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.exception.BadRequestException;
import com.tdonuk.sepetim.dao.BaseDAO;
import com.tdonuk.util.text.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService <T extends BaseDTO> {

    abstract BaseDAO dao();
    abstract T concrete(BaseDTO base);
    abstract List<T> concrete(List<BaseDTO> base);
    abstract List<String> invalidFieldsForUpdate();

    public List<T> save(List<T> datas) throws Exception {
        return concrete(dao().save(datas));
    }

    public T save(T data) throws Exception {
        return concrete(dao().save(data));
    }

    public T update(String id, T data) throws Exception {
        if(StringUtils.isBlank(data.getId())) throw new BadRequestException("Ürün bulunamadı", "ID boş olamaz");

        return concrete(dao().update(id, data));
    }

    public T update(String id, Map<String,Object> data) throws Exception {
        if(StringUtils.isBlank(id)) throw new BadRequestException("Ürün bulunamadı", "ID boş olamaz");

        Map<String, Object> validFields = new HashMap<>();

        for(String field : data.keySet()) {
            if(! invalidFieldsForUpdate().contains(field)) validFields.put(field, data.get(field));
        }

        return concrete(dao().update(id, validFields));
    }

    public Boolean delete(T data) throws Exception {
        if(StringUtils.isBlank(data.getId())) throw new BadRequestException("Ürün bulunamadı", "Silinmek istenen ürün bulunamadı: ID alanı boş olamaz.");

        return dao().delete(data);
    }

    public Boolean delete(String id) throws Exception {
        if(StringUtils.isBlank(id)) throw new BadRequestException("Ürün bulunamadı", "Silinmek istenen ürün bulunamadı: ID alanı boş olamaz.");

        return dao().delete(id);
    }

    public List<T> findByField(String field, Object value) throws Exception{
        return concrete(dao().findAll(field, value));
    }

    public Boolean delete(List<T> dtos) throws Exception {
        return dao().delete(dtos);
    }

}
