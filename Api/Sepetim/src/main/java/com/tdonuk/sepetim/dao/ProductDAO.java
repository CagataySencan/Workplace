package com.tdonuk.sepetim.dao;

import com.tdonuk.dto.domain.product.ProductDTO;
import com.tdonuk.sepetim.constant.FirebaseCollections;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO extends BaseDAO<ProductDTO>{
    @Override
    protected String collection() {
        return FirebaseCollections.PRODUCTS;
    }

    @Override
    void restoreInvalidFields(ProductDTO dataToUpdate, ProductDTO oldData) {
        dataToUpdate.setId(oldData.getId());
        dataToUpdate.setCreated(oldData.getCreated());
    }

    @Override
    protected Class<ProductDTO> type() {
        return ProductDTO.class;
    }
}
