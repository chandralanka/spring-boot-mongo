package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class SecurityFilter implements Filter {

	private final boolean isSecurityEnable;

	private final String keyId;
	private List<String> byPassAuthenticationUrls;

	SecurityFilter(final boolean isSecurityEnable,final String keyId, List<String> byPassAuthenticationUrls){
		this.isSecurityEnable=isSecurityEnable;
		this.keyId=keyId;
		this.byPassAuthenticationUrls=byPassAuthenticationUrls;
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		log.info("SecurityFilter is added keyId=" + keyId + " isSecurityEnable=" + isSecurityEnable);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		boolean isByPassURI = isByPassUri(((HttpServletRequest)request).getRequestURI());
		log.info(((HttpServletRequest)request).getRequestURI());
		if (isByPassURI || !isSecurityEnable || StringUtils.isEmpty(keyId)) {
			chain.doFilter(request, response);
			return;
		} else {
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			final String userKeyId = httpRequest.getHeader("KeyId");
			if(StringUtils.isEmpty(userKeyId) || !keyId.equals(userKeyId)){
				sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect header, missing KeyId.", response);
				return;
			}else{
				chain.doFilter(request, response);
				return;
			}
		}
	}

	private void sendError(final int code, final String description, final ServletResponse res) throws IOException, ServletException {
		log.error("Security filter failed to execute request due to: Error code:" + code + " and description "+ description);
		HttpServletResponse response = (HttpServletResponse) res;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setStatus(code);
			response.setContentType("application/json");
			StringBuffer sb = new StringBuffer();
			sb.append("{ \"Error Code\" :" + code + ",");
			sb.append("\"Error Description\" :\"" + description + "\" }");
			out.print(sb.toString());
			out.flush();

		} catch (Exception e) {
			log.error("Error in security filter while sending response", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				log.error("Error in security filter while closing output stream", e);
			}
		}
		return;
	}

	@Override
	public void destroy() {
	}


	private boolean isByPassUri(String uri) {
		if (byPassAuthenticationUrls == null || byPassAuthenticationUrls.size() == 0){
			return false;
		}
		for (String byPassUri : byPassAuthenticationUrls) {
			if (!StringUtils.isEmpty(byPassUri) && uri.toLowerCase().contains(byPassUri.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
