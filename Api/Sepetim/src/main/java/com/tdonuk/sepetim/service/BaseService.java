package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.exception.BadRequestException;
import com.tdonuk.sepetim.dao.BaseDAO;
import com.tdonuk.util.text.StringUtils;

import java.util.List;

public abstract class BaseService <T extends BaseDTO> {

    abstract BaseDAO dao();
    abstract T concrete(BaseDTO base);
    abstract List<T> concrete(List<BaseDTO> base);

    public List<T> save(List<T> datas) throws Exception {
        return concrete(dao().save(datas));
    }

    public T save(T data) throws Exception {
        return concrete(dao().save(data));
    }

    public T update(T data) throws Exception {
        if(StringUtils.isBlank(data.getId())) throw new BadRequestException("Ürün bulunamadı", "Güncellenmek istenen ürün bulunamadı: ID alanı boş olamaz.");

        return concrete(dao().update(data));
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

    public Boolean deleteAll(List<String> id) {
        return Boolean.FALSE;
    }
}
