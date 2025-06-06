package io.github.nichetoolkit.rest.error.image;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>ImageErrorStatus</code>
 * <p>The image error status enumeration.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.RestStatus
 * @see lombok.Getter
 * @since Jdk1.8
 */
@Getter
public enum ImageErrorStatus implements RestStatus {
    /**
     * <code>IMAGE_FILE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.image.ImageErrorStatus} <p>The <code>IMAGE_FILE_ERROR</code> field.</p>
     */
    IMAGE_FILE_ERROR(11100, "The server has encountered an error with image file."),
    /**
     * <code>IMAGE_READ_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.image.ImageErrorStatus} <p>The <code>IMAGE_READ_ERROR</code> field.</p>
     */
    IMAGE_READ_ERROR(11101, "The server has encountered an error with image file reading."),
    /**
     * <code>IMAGE_WRITE_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.image.ImageErrorStatus} <p>The <code>IMAGE_WRITE_ERROR</code> field.</p>
     */
    IMAGE_WRITE_ERROR(11102, "The server has encountered an error with image file writing."),
    /**
     * <code>IMAGE_TRANSFER_ERROR</code>
     * {@link io.github.nichetoolkit.rest.error.image.ImageErrorStatus} <p>The <code>IMAGE_TRANSFER_ERROR</code> field.</p>
     */
    IMAGE_TRANSFER_ERROR(11103, "The server has encountered an error with image file transferring."),
    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see java.lang.String
     */
    private final String message;

    /**
     * <code>ImageErrorStatus</code>
     * <p>Instantiates a new image error status.</p>
     * @param status  {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.Integer
     * @see java.lang.String
     */
    ImageErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
