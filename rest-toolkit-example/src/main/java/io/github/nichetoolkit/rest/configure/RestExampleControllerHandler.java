package io.github.nichetoolkit.rest.configure;

import io.github.nichetoolkit.rest.RestControllerHandler;
import io.github.nichetoolkit.rest.RestExceptionAdvice;
import io.github.nichetoolkit.rest.RestResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@CrossOrigin
@RestControllerAdvice
public class RestExampleControllerHandler extends RestControllerHandler {

    @Autowired(required = false)
    private List<RestExceptionAdvice>  exceptionAdvices;

    @Autowired(required = false)
    private List<RestResponseAdvice>  responseAdvices;

    public RestExampleControllerHandler(RestExceptionProperties exceptionProperties) {
        super(exceptionProperties);
    }

    @Override
    public List<RestExceptionAdvice> applyExceptionAdvices() {
        return exceptionAdvices;
    }

    @Override
    public List<RestResponseAdvice> applyResponseAdvices() {
        return responseAdvices;
    }
}
