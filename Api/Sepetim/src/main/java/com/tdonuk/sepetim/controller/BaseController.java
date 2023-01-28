package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import com.tdonuk.sepetim.util.ErrorUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Slf4j
public abstract class BaseController<T extends DomainDTO> {

    abstract BaseService service();

    @PutMapping(path = {"/{id}", "/{id}/update"})
    public BaseResponse<?> update(@PathVariable String id, @RequestBody T body, HttpServletResponse servletResponse) {
        try {
            return BaseResponse.of(service().update(id, body), HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.badRequest(e, servletResponse);
        }
    }

    @DeleteMapping(path = {"/{id}", "/{id}/delete"})
    public BaseResponse<?> delete(@PathVariable String id, @RequestBody T body, HttpServletResponse servletResponse) {
        Boolean result;

        try {
            if(Objects.isNull(body)) result = service().delete(id);
            else result = service().delete(body);

            return BaseResponse.of(result, HttpStatus.OK.value());
        } catch (Exception e) {
            return ErrorUtils.badRequest(e, servletResponse);
        }
    }
}
