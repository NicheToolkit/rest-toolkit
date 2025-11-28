package io.github.nichetoolkit.rest.error.image;

import io.github.nichetoolkit.rest.RestError;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;

/**
 * <code>ImageTransferException</code>
 * <p>The image transfer exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.FileErrorException
 * @since Jdk17
 */
public class ImageTransferException extends FileErrorException {
    /**
     * <code>ImageTransferException</code>
     * <p>Instantiates a new image transfer exception.</p>
     */
    public ImageTransferException() {
        super(ImageErrorStatus.IMAGE_TRANSFER_ERROR);
    }

    /**
     * <code>ImageTransferException</code>
     * <p>Instantiates a new image transfer exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see io.github.nichetoolkit.rest.RestStatus
     */
    public ImageTransferException(RestStatus status) {
        super(status, RestError.error(status));
    }

    /**
     * <code>ImageTransferException</code>
     * <p>Instantiates a new image transfer exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageTransferException(String message) {
        super(ImageErrorStatus.IMAGE_TRANSFER_ERROR, RestError.error(ImageErrorStatus.IMAGE_TRANSFER_ERROR, message));
    }

    /**
     * <code>ImageTransferException</code>
     * <p>Instantiates a new image transfer exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message  {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public ImageTransferException(String resource, String message) {
        super(ImageErrorStatus.IMAGE_TRANSFER_ERROR, RestError.error(resource, ImageErrorStatus.IMAGE_TRANSFER_ERROR, message));
    }

    @Override
    public ImageTransferException get() {
        return new ImageTransferException();
    }
}
