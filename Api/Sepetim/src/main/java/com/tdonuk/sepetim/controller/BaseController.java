package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

public abstract class BaseController<T extends DomainDTO> {

    abstract BaseService service();

    @PutMapping(path = {"/{id}", "/{id}/update"})
    public BaseResponse<?> update(@PathVariable String id, @RequestBody T body) throws Exception {
        return BaseResponse.of(service().update(id, body), HttpStatus.OK.value());
    }

    @DeleteMapping(path = {"/{id}", "/{id}/delete"})
    public BaseResponse<?> delete(@PathVariable String id, @RequestBody T body) throws Exception {
        Boolean result;

        if(Objects.isNull(body)) result = service().delete(id);
        else result = service().delete(body);

        return BaseResponse.of(result, HttpStatus.OK.value());
    }
}
