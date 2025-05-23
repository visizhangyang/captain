package com.porn.web.common.filters;

import cn.hutool.core.io.IoUtil;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Order(1)
@WebFilter(filterName = "RequestParamsFilter", urlPatterns = {"/*"})
public class RequestParamsFilter
        implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.toLowerCase().startsWith("/common")) {
            chain.doFilter(request, response);
            return;
        }
        final byte[] bodyBytes = IoUtil.readBytes((InputStream) req.getInputStream());
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(req) {
            public BufferedReader getReader() throws IOException {
                return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bodyBytes)));
            }

            public ServletInputStream getInputStream() throws IOException {
                final ByteArrayInputStream bais = new ByteArrayInputStream(bodyBytes);
                return new ServletInputStream() {
                    public boolean isFinished() {
                        return false;
                    }

                    public boolean isReady() {
                        return true;
                    }

                    public void setReadListener(ReadListener readListener) {
                        throw new UnsupportedOperationException();
                    }

                    public int read() throws IOException {
                        return bais.read();
                    }

                    public int read(byte[] b, int off, int len) throws IOException {
                        return bais.read(b, off, len);
                    }

                    public int read(byte[] b) throws IOException {
                        return bais.read(b);
                    }

                    public long skip(long n) throws IOException {
                        return bais.skip(n);
                    }

                    public int available() throws IOException {
                        return bais.available();
                    }

                    public void close() throws IOException {
                        bais.close();
                    }

                    public synchronized void mark(int readlimit) {
                        bais.mark(readlimit);
                    }

                    public synchronized void reset() throws IOException {
                        bais.reset();
                    }

                    public boolean markSupported() {
                        return bais.markSupported();
                    }
                };
            }
        };
        chain.doFilter((ServletRequest) httpServletRequestWrapper, response);
    }
}

