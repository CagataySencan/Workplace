package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.product.DiscountDTO;
import com.tdonuk.sepetim.dao.DiscountDAO;
import com.tdonuk.sepetim.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService extends BaseService<DiscountDTO> {
    @Autowired
    private DiscountDAO discountDAO;

    @Override
    protected BaseDAO dao() {
        return discountDAO;
    }

    @Override
    protected DiscountDTO concrete(BaseDTO base) {
        if(base instanceof DiscountDTO) return (DiscountDTO) base;
        else return null;
    }

    @Override
    protected List<DiscountDTO> concrete(List<BaseDTO> base) {
        return (List<DiscountDTO>)(List<?>) base;
    }

    @Override
    protected List<String> invalidFieldsForUpdate() {
        return List.of(
                "id",
                "created"
        );
    }

    public List<DiscountDTO> getHist() throws Exception {
        return discountDAO.getHist();
    }

    public List<DiscountDTO> updateHist(List<DiscountDTO> hist) throws Exception {
        return discountDAO.updateHist(hist);
    }

}
