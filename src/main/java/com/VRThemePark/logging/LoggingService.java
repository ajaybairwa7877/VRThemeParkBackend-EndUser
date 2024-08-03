package com.VRThemePark.logging;

import com.VRThemePark.entity.User;
import com.VRThemePark.entity.UserActionActivity;
import com.VRThemePark.repository.UserActionActivityRepository;
import com.VRThemePark.repository.UserDetailsRepository;
import com.VRThemePark.security.model.UserDetailsImpl;
import com.VRThemePark.utilities.GsonParserUtils;
import com.VRThemePark.utilities.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.VRThemePark.security.JWTAuthenticationFilter.getClientIp;

//import static com.VRThemePark.security.JWTAuthenticationFilter.getClientIp;

@Service
@Slf4j
public class LoggingService {

	private static final String REQUEST_ID = "request_id";
	Logger log = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private UserActionActivityRepository userActionActivityRepository;


	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		long startTime = System.currentTimeMillis();
		if (httpServletRequest.getRequestURI().contains("medias")
				&& !httpServletRequest.getRequestURI().contains("swagger")) {
			return;
		}

		// getting Request id address
		String remoteIpAddr = getClientIp(httpServletRequest);
		log.info("remote ip address : " + remoteIpAddr);


		Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
		StringBuilder data = new StringBuilder();
		data.append("\nLOGGING REQUEST BODY-----------------------------------\n").append("[REQUEST-ID]: ")
				.append(requestId).append("\n").append("[BODY REQUEST]: ").append("\n\n")
				.append("[Headers]: " + Util.getParameters(httpServletRequest)).append("\n\n")
				.append("[REQUEST URI]: " + httpServletRequest.getRequestURI()).append("\n\n").append("\n\n")
				.append(GsonParserUtils.parseObjectToString(body)).append("\n\n").append("\n")
				.append("LOGGING REQUEST BODY-----------------------------------\n");
		log.debug(data.toString());
		saveUserActivity(httpServletRequest, startTime, body, requestId.toString(),remoteIpAddr);
	}

	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {

		if (httpServletRequest.getRequestURI().contains("medias")
				&& !httpServletRequest.getRequestURI().contains("swagger")) {
			return;
		}
		Object requestId = httpServletRequest.getAttribute(REQUEST_ID);

		try {
			StringBuilder data = new StringBuilder();
			data.append("\nLOGGING RESPONSE-----------------------------------\n").append("[BODY RESPONSE]: ")
					.append("\n\n").append(GsonParserUtils.parseObjectToString(body)).append("\n\n")
					.append("LOGGING RESPONSE-----------------------------------\n");


			if (httpServletResponse.getStatus() != 200) {
				log.debug(data.toString());
				// System.out.println(httpServletResponse.getStatus()+"----"+GsonParserUtils.parseObjectToString(body));
				updateUserActivityWithError(requestId.toString(), httpServletResponse.getStatus(), "",
						GsonParserUtils.parseObjectToString(body));
			} else {
				//log.debug(data.toString());
				updateUserActivity(requestId.toString(), httpServletResponse.getStatus(), "", GsonParserUtils.parseObjectToString(body).toString());
			}
		} catch (Exception e) {
			log.debug("Exception while log response request Id : {} , httpservlet response : {} , Exception : {}", requestId.toString(),
					httpServletResponse.getStatus() , e.getMessage());
		}
	}

	private void saveUserActivity(HttpServletRequest httpServletRequest, long startTime, Object body,
			String requestId,String remoteIpAddress) {
		UserActionActivity userActionActivity = userActionActivityRepository.findByRequestId(requestId);
		if (userActionActivity == null) {
			userActionActivity = new UserActionActivity();
		}
		try {
			//UserDetailsImpl loginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//if (loginUser != null) {
				userActionActivity.setCreatedBy(Integer.valueOf(remoteIpAddress));
				userActionActivity.setUserId(Integer.valueOf(remoteIpAddress));
				userActionActivity.setEmail("");
			//}
		} catch (Exception e) {

		}
		userActionActivity.setStartTime(startTime);
		String action = httpServletRequest.getRequestURI().replaceAll("", "");
		userActionActivity.setAction(action);
		userActionActivity.setCreationDate(Util.getCurrentDate());
		userActionActivity.setIpAddress(Util.getRemoteAddr(httpServletRequest));
		userActionActivity.setRequestBody(GsonParserUtils.parseObjectToString(body));

		userActionActivity.setUserId(0);
		userActionActivity.setCreatedBy(0);


		if (action.equalsIgnoreCase("login")) {
			String reqBody = GsonParserUtils.parseObjectToString(body)
					.replaceAll("\"password\":\".+?\"", "\"password\":\"*******\"")
					.replaceAll("\"fcmToken\":\".+?\"", "\"fcmToken\":\"*******\"");

			//UserDetailsImpl loginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//if (loginUser != null) {
				userActionActivity.setUserId(Integer.valueOf(remoteIpAddress));
				userActionActivity.setCreatedBy(Integer.valueOf(remoteIpAddress));
			//}

			userActionActivity.setRequestBody(reqBody);
			userActionActivity.setEmail(Util.returnEmailFromJson(reqBody));
		}

		if (action.equalsIgnoreCase("login")) {
			String reqBody = GsonParserUtils.parseObjectToString(body).replaceAll("\"password\":\".+?\"",
					"\"password\":\"*******\"");
			userActionActivity.setRequestBody(reqBody);
			userActionActivity.setEmail(Util.returnEmailFromJson(reqBody));
		}
		//userActionActivity.setType("Mobile-App");
		userActionActivity.setType("");
		userActionActivity.setRequestId(requestId);
		userActionActivityRepository.save(userActionActivity);
	}

	private void updateUserActivity(String requestId, Integer status, String userId,String data) {
		try {
			UserActionActivity userActionActivity = userActionActivityRepository.findByRequestId(requestId);
			if (userActionActivity != null) {
				long startTime = userActionActivity.getStartTime();
				long endTime = System.currentTimeMillis();
				float duration = (endTime - startTime) / 1000F;
				userActionActivity.setEndTime(endTime);
				userActionActivity.setTotalExecutionTime(duration);
				userActionActivity.setStatus(status);
				userActionActivity.setResponseBody(data);
				userActionActivityRepository.save(userActionActivity);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}

	private void updateUserActivityWithError(String requestId, Integer status, String userId, String errorData) {
		try {
			UserActionActivity userActionActivity = userActionActivityRepository.findByRequestId(requestId);
			if (userActionActivity != null) {
				long startTime = userActionActivity.getStartTime();
				long endTime = System.currentTimeMillis();
				float duration = (endTime - startTime) / 1000F;
				userActionActivity.setEndTime(endTime);
				userActionActivity.setTotalExecutionTime(duration);
				userActionActivity.setStatus(status);
				userActionActivity.setResponseBody(errorData);
				userActionActivityRepository.save(userActionActivity);
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
}
