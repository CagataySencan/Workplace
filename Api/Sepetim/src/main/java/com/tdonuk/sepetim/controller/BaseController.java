package com.tdonuk.sepetim.controller;

import com.tdonuk.dto.domain.DomainDTO;
import com.tdonuk.dto.http.BaseResponse;
import com.tdonuk.sepetim.service.BaseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Slf4j
public abstract class BaseController<T extends DomainDTO> {

    abstract BaseService service();

    @PutMapping(path = {"/{id}", "/{id}/update"})
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody T body) throws Exception {
        return ResponseEntity.ok(BaseResponse.of(service().update(id, body)));
    }

    @DeleteMapping(path = {"/{id}", "/{id}/delete"})
    public ResponseEntity<?> delete(@PathVariable String id, @RequestBody T body, HttpServletResponse servletResponse) throws Exception {
        Boolean result;

        if(Objects.isNull(body)) result = service().delete(id);
        else result = service().delete(body);

        return ResponseEntity.ok(BaseResponse.of(result));
    }
}
