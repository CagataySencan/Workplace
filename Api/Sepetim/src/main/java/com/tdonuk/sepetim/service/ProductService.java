package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.CurrentActualDTO;
import com.tdonuk.dto.domain.product.ProductDTO;
import com.tdonuk.sepetim.dao.BaseDAO;
import com.tdonuk.sepetim.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService<ProductDTO> {
    @Autowired
    private ProductDAO productDao;

    @Override
    protected BaseDAO dao() {
        return productDao;
    }

    @Override
    protected ProductDTO concrete(BaseDTO base) {
        if(base instanceof CurrentActualDTO) return (ProductDTO) base;
        else return null;
    }

    @Override
    protected List<ProductDTO> concrete(List<BaseDTO> base) {
        return (List<ProductDTO>)(List<?>) base;
    }

    @Override
    protected List<String> invalidFieldsForUpdate() {
        return List.of(
                "id",
                "created"
        );
    }
}
