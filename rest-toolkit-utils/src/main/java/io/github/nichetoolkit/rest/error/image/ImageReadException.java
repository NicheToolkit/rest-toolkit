package io.github.nichetoolkit.rest.error.image;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>ImageReadException</code>
 * <p>The image read exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk1.8
 */
public class ImageReadException extends FileErrorException {
    /**
     * <code>ImageReadException</code>
     * <p>Instantiates a new image read exception.</p>
     */
    public ImageReadException() {
        super(ImageErrorStatus.IMAGE_READ_ERROR);
    }

    /**
     * <code>ImageReadException</code>
     * <p>Instantiates a new image read exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ImageReadException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ImageReadException</code>
     * <p>Instantiates a new image read exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageReadException(String message) {
        super(ImageErrorStatus.IMAGE_READ_ERROR, RestError.error(ImageErrorStatus.IMAGE_READ_ERROR, message));
    }

    /**
     * <code>ImageReadException</code>
     * <p>Instantiates a new image read exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageReadException(String resource, String message) {
        super(ImageErrorStatus.IMAGE_READ_ERROR, RestError.error(resource, ImageErrorStatus.IMAGE_READ_ERROR, message));
    }

    @Override
    public ImageReadException get() {
        return new ImageReadException();
    }
}
