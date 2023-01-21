package com.tdonuk.sepetim.service;

import com.tdonuk.dto.BaseDTO;
import com.tdonuk.dto.domain.CurrentActualDTO;
import com.tdonuk.sepetim.dao.ActualDAO;
import com.tdonuk.sepetim.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualService extends BaseService<CurrentActualDTO> {
    @Autowired
    private ActualDAO actualDAO;

    @Override
    protected BaseDAO dao() {
        return actualDAO;
    }

    @Override
    protected CurrentActualDTO concrete(BaseDTO base) {
        if(base instanceof CurrentActualDTO) return (CurrentActualDTO) base;
        else return null;
    }

    @Override
    protected List<CurrentActualDTO> concrete(List<BaseDTO> base) {
        return (List<CurrentActualDTO>)(List<?>) base;
    }

    @Override
    protected List<String> invalidFieldsForUpdate() {
        return List.of(
                "id",
                "created"
        );
    }

    public List<CurrentActualDTO> getHist() throws Exception {
        return actualDAO.getHist();
    }

    public CurrentActualDTO getCurrent() throws Exception {
        return actualDAO.getCurrent();
    }
}
