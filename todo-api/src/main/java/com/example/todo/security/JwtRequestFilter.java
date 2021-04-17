package com.example.todo.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo.repository.UserRepository;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtService;

	@Autowired
	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		getTokenString(request.getHeader(HttpHeaders.AUTHORIZATION)).ifPresent(token -> {
			jwtService.getUserNameFromToken(token).ifPresent(userName -> {
				if (SecurityContextHolder.getContext().getAuthentication() == null) {
					userRepository.getUserByUserName(userName).ifPresent(user -> {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								user, null, Collections.emptyList());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					});
				}
			});
		});
		filterChain.doFilter(request, response);
	}

	private Optional<String> getTokenString(String header) {
		if (header == null) {
			return Optional.empty();
		}
		String[] list = header.split(" ");
		if (list.length < 2) {
			return Optional.empty();
		}
		return Optional.ofNullable(list[1]);
	}

}
