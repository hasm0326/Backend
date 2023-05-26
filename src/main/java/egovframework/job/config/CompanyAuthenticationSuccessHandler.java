package egovframework.job.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import egovframework.job.service.CompanyDetails;

public class CompanyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// 로그인 성공 후 JSON 응답 생성
	    Long companySequenceNumber = getCompanySequenceNumberFromAuthentication(authentication);
	    String jsonResponse = "{ \"message\": \"로그인이 성공하였습니다.\", \"companySequenceNumber\": \"" + companySequenceNumber + "\" }";
	    
	    // JSON 형식으로 응답을 설정
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    // JSON 응답을 출력
	    PrintWriter out = response.getWriter();
	    out.print(jsonResponse);
	    out.flush();
	}

	private Long getCompanySequenceNumberFromAuthentication(Authentication authentication) {
		CompanyDetails companyDetails = (CompanyDetails) authentication.getPrincipal();
		return companyDetails.getC_num();
	}
}