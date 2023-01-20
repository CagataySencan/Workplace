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
    protected Class<ProductDTO> type() {
        return ProductDTO.class;
    }
}
