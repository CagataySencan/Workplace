package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

public abstract class BaseController<T extends DomainDTO> {

    abstract BaseService service();

    @PostMapping(path = {"/", "/save", "/create"})
    public BaseResponse<?> save(@RequestBody T body) throws Exception {
        return BaseResponse.of(service().save(body), HttpStatus.OK.value());
    }

    @PutMapping(path = {"/{id}", "/{id}/update"})
    public BaseResponse<?> update(@PathVariable String id, @RequestBody T body) throws Exception {
        return BaseResponse.of(service().update(body), HttpStatus.OK.value());
    }

    @DeleteMapping(path = {"/{id}", "/{id}/delete"})
    public BaseResponse<?> delete(@PathVariable String id, @RequestBody T body) throws Exception {
        Boolean result;

        if(Objects.isNull(body)) result = service().delete(id);
        else result = service().delete(body);

        return BaseResponse.of(result, HttpStatus.OK.value());
    }
}
