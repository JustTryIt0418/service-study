package org.delivery.api.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        // doFilter 이전은 다른 클래스 필요
        // request 정보
        Enumeration<String> headerNames = req.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);

            headerValues
                    .append("[")
                    .append(headerName)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        }

        String requestBody = new String(req.getContentAsByteArray());
        String uri = req.getRequestURI();
        String method = req.getMethod();

        log.info(">>>>> uri : {} , method : {}, header : {}\n>>>>> body : {}", uri, method, headerValues, requestBody);

        // response 정보
        StringBuilder responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerName -> {
            String headerValue = res.getHeader(headerName);

            responseHeaderValues
                    .append("[")
                    .append(headerValue)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        String responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<< uri : {}, method : {},  header : {}\n<<<<< body : {}", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();       // 이게 없으면 response body가 비어서 내려감
    }
}
