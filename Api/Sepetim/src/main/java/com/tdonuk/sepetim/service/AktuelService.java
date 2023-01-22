package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.product.AktuelDTO;
import com.tdonuk.sepetim.dao.ActualDAO;
import com.tdonuk.sepetim.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AktuelService extends BaseService<AktuelDTO> {
    @Autowired
    private ActualDAO actualDAO;

    @Override
    protected BaseDAO dao() {
        return actualDAO;
    }

    @Override
    protected AktuelDTO concrete(BaseDTO base) {
        if(base instanceof AktuelDTO) return (AktuelDTO) base;
        else return null;
    }

    @Override
    protected List<AktuelDTO> concrete(List<BaseDTO> base) {
        return (List<AktuelDTO>)(List<?>) base;
    }

    @Override
    protected List<String> invalidFieldsForUpdate() {
        return List.of(
                "id",
                "created"
        );
    }

    public List<AktuelDTO> getHist() throws Exception {
        return actualDAO.getHist();
    }

}
