package io.github.nichetoolkit.rest;

/**
 * <p>RestNoteService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class RestNoteService {

    abstract public void handler(RestRequest request, RestResponse restResponse, RestLog restLog);

}
