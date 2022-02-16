package io.github.nichetoolkit.rest.interceptor;


import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * <p>RestRequestWrapper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class RestNoteRequestWrapper extends HttpServletRequestWrapper implements Closeable {

    private byte[] body;

    private BufferedReader reader;

    private ServletInputStream inputStream;

    public RestNoteRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        loadBody(request);
    }

    private void loadBody(HttpServletRequest request) throws IOException{
        body = StreamUtils.copyToByteArray(request.getInputStream());
        inputStream = new RequestCachingInputStream(body);
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public BufferedReader getReader()throws IOException {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(inputStream, getCharacterEncoding()));
        }
        return reader;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (inputStream != null) {
            return inputStream;
        }
        return super.getInputStream();
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private static class RequestCachingInputStream extends ServletInputStream {

        private final ByteArrayInputStream inputStream;

        public RequestCachingInputStream(byte[] bytes) {
            inputStream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() {
            return inputStream.read();
        }

        @Override
        public int read(@NonNull byte[] bytes) throws IOException {
            return inputStream.read(bytes);
        }

        @Override
        public int read(@NonNull byte[] bytes, int off, int len) {
            return inputStream.read(bytes, off, len);
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readlistener) {
        }

    }
}
