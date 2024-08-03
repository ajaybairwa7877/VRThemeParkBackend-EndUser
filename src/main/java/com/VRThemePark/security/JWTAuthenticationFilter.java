package com.VRThemePark.security;

import com.VRThemePark.entity.JwtBlacklist;
import com.VRThemePark.entity.UserActionActivity;
import com.VRThemePark.exception.ErrorMessage;
import com.VRThemePark.repository.JwtBlacklistRepository;
import com.VRThemePark.repository.UserActionActivityRepository;
import com.VRThemePark.security.model.UserDetailsImpl;
import com.VRThemePark.utilities.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JWTHelper jwtHelper;

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    public JwtBlacklistRepository jwtBlacklistRepository;

    @Autowired
    private UserActionActivityRepository userActionActivityRepository;

    @Autowired
    private JWTAthenticationEntryPoint point;

    private static final String REQUEST_ID = "request_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization=Bearer <token>
        String requestHeader = request.getHeader("Authorization");
        logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;

        // getting Request id address
        String remoteIpAddr = getClientIp(request);
        logger.info("remote ip address : " + remoteIpAddr);

        String requestId = UUID.randomUUID().toString();
        request.setAttribute(REQUEST_ID, requestId);

        logRequest((HttpServletRequest) request, requestId,remoteIpAddr);
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Authorization=Bearer <token>
//        String requestHeader = request.getHeader("Authorization");
//        logger.info(" Header :  {}", requestHeader);
//        String username = null;
//        String token = null;
//
//        // getting Request id address
//        String remoteIpAddr = getClientIp(request);
//        logger.info("remote ip address : " + remoteIpAddr);
//
//        String requestId = UUID.randomUUID().toString();
//        request.setAttribute(REQUEST_ID, requestId);
//
//        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
//            token = requestHeader.substring(7);
//            try {
//                username = this.jwtHelper.getUsernameFromToken(token);
//            } catch (IllegalArgumentException e) {
//                logger.info("Illegal Argument while fetching the username !!");
//                e.printStackTrace();
//            } catch (ExpiredJwtException e) {
//                logger.info("Given jwt token is expired !!");
//                e.printStackTrace();
//            } catch (MalformedJwtException e) {
//                logger.info("Some changed has done in token !! Invalid Token");
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            logger.info("Invalid Header Value !! ");
//        }
//
//        if (token != null && token.length() > 0) {
//
//            JwtBlacklist blacklist = jwtBlacklistRepository.findByTokenEquals(token);
//            //JwtBlacklist blacklist1 = jwtBlacklistRepository.findByToken(token);
//
//            if (blacklist != null) {
//                logger.info("Invalid JWT token Value !! : {} ", token);
//                redirectException(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid user token");
//                return;
//            }
//
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            //fetch user detail from username
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
//            if (validateToken) {
//                //set the authentication
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                logger.info("Validation fails !!");
//            }
//        }
//
//        logRequest((HttpServletRequest) request, requestId,remoteIpAddr);
//        filterChain.doFilter(request, response);
//    }

    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    private void redirectException(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        ErrorMessage errorMessage = new ErrorMessage(false, HttpServletResponse.SC_UNAUTHORIZED, message,message,null);

        ObjectMapper objectMapper = new ObjectMapper();
        String exceptionString = objectMapper.writeValueAsString(errorMessage);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println(exceptionString);
            writer.flush();
        }
    }

    private void logRequest(HttpServletRequest request, String requestId,String remoteIpAddr) {

        if (request != null && !request.getRequestURI().contains("swagger")) {
            long startTime = System.currentTimeMillis();
            String traceId="";
            StringBuilder data = new StringBuilder();
            data.append("\nLOGGING REQUEST-----------------------------------\n").append("[REQUEST-ID]: ")
                    .append(requestId).append("\n").append("[PATH]: ").append(request.getRequestURI()).append("\n")
                    .append("[QUERIES]: ").append(request.getQueryString()).append("\n").append("[HEADERS]: ")
                    .append("\n");

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                if(key.equals("x-lc-trace-id")) {
                    traceId=value;
                }
                data.append("---").append(key).append(" : ").append(value).append("\n");
                data.append("---").append("remoteIpAddress").append(" : ").append(remoteIpAddr).append("\n");
            }
            data.append("LOGGING REQUEST-----------------------------------\n");

            logger.debug(data.toString());
            saveUserActivity(request,startTime,traceId, requestId,remoteIpAddr);
        }
    }

    private void saveUserActivity(HttpServletRequest httpServletRequest,long startTime,String lcTraceId,String requestId,String remoteIpAddr) {
        UserActionActivity userActionActivity = new UserActionActivity();
        try {
            //UserDetailsImpl loginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //if (loginUser != null) {
                userActionActivity.setCreatedBy(Integer.valueOf(remoteIpAddr));
                userActionActivity.setUserId(Integer.valueOf(remoteIpAddr));
                userActionActivity.setEmail("");
            //}
        } catch (Exception e) {

        }
        userActionActivity.setStartTime(startTime);
        String action=httpServletRequest.getRequestURI().replaceAll("", "");
        userActionActivity.setAction(action);
        userActionActivity.setCreationDate(Util.getCurrentDate());
        userActionActivity.setIpAddress(Util.getRemoteAddr(httpServletRequest));
        userActionActivity.setRequestBody(httpServletRequest.getQueryString());
        //userActionActivity.setType("Mobile-App");
        userActionActivity.setRequestId(requestId);
        userActionActivity.setLcTraceId(lcTraceId);

        userActionActivityRepository.save(userActionActivity);
    }

}
