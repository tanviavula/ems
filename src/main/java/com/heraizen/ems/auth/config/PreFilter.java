package com.heraizen.ems.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class PreFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(PreFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		LOG.info("Before : Request Dispacthed");
		chain.doFilter(request, response);
		LOG.info("Response : Request Dispacthed");

	}

}
