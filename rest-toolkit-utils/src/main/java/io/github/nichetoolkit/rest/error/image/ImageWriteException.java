package io.github.nichetoolkit.rest.error.image;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>ImageWriteException</code>
 * <p>The image write exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk1.8
 */
public class ImageWriteException extends FileErrorException {
    /**
     * <code>ImageWriteException</code>
     * <p>Instantiates a new image write exception.</p>
     */
    public ImageWriteException() {
        super(ImageErrorStatus.IMAGE_WRITE_ERROR);
    }

    /**
     * <code>ImageWriteException</code>
     * <p>Instantiates a new image write exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ImageWriteException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ImageWriteException</code>
     * <p>Instantiates a new image write exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageWriteException(String message) {
        super(ImageErrorStatus.IMAGE_WRITE_ERROR, RestError.error(ImageErrorStatus.IMAGE_WRITE_ERROR, message));
    }

    /**
     * <code>ImageWriteException</code>
     * <p>Instantiates a new image write exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageWriteException(String resource, String message) {
        super(ImageErrorStatus.IMAGE_WRITE_ERROR, RestError.error(resource, ImageErrorStatus.IMAGE_WRITE_ERROR, message));
    }

    @Override
    public ImageWriteException get() {
        return new ImageWriteException();
    }
}
