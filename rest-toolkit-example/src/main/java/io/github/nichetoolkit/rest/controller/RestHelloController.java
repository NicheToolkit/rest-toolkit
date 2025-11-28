package io.github.nichetoolkit.rest.controller;

import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <code>RestHelloController</code>
 * <p>The rest hello controller class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.springframework.web.bind.annotation.RestController
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @since Jdk17
 */
@RestController
@RequestMapping("/rest")
public class RestHelloController {

    /**
     * <code>hello</code>
     * <p>The hello method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The hello return object is <code>RestResult</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public RestResult<?> hello() {
        return RestResult.success(RestErrorStatus.HTTP_CONFIG_ERROR);
    }

    /**
     * <code>test</code>
     * <p>The test method.</p>
     * @return {@link io.github.nichetoolkit.rest.RestResult} <p>The test return object is <code>RestResult</code> type.</p>
     * @throws UnsupportedErrorException {@link io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException} <p>The unsupported error exception is <code>UnsupportedErrorException</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestResult
     * @see org.springframework.web.bind.annotation.RequestMapping
     * @see io.github.nichetoolkit.rest.error.natives.UnsupportedErrorException
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public RestResult<?> test() throws UnsupportedErrorException {
        throw new UnsupportedErrorException();
    }
}
