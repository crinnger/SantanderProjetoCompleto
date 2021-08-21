package br.com.crinnger.salareuniao.apisalareuniao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception implements Serializable {
    private static final long serialVerisonUID=1L;

    public ResourceNotFoundException(String not_found) {
        super(not_found);
    }
}
