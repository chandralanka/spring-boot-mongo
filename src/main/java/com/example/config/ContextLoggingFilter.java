package com.example.config;

import com.example.utils.Utils;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class ContextLoggingFilter extends GenericFilterBean {
	
	private transient final String domain;

	public ContextLoggingFilter(final String domain) {
		super();
		this.domain = domain;
	}

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		String requestId = request.getHeader(Utils.X_REQUEST_ID);
		if (StringUtils.isEmpty(requestId)) {
			requestId = domain + Utils.KEY_SEPARATOR+ UUID.randomUUID().toString();
		} else {
			requestId = requestId + Utils.KEY_SEPARATOR+ domain;
		}

		MDC.put(Utils.REQUEST_ID_NAME, requestId);
		try {
			chain.doFilter(req, res);
		} finally {
			MDC.remove(Utils.REQUEST_ID_NAME);
		}
	}

}