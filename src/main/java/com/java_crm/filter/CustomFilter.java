package com.java_crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java_crm.common.Const;
import com.java_crm.model.AccountsModel;
import com.java_crm.model.AccountsModelImpl;
import com.java_crm.pojo.Accounts;

@WebFilter(urlPatterns = {"/user_table", "/projects", "/tasks_all"})
public class CustomFilter implements Filter {

	AccountsModel accountsModel = new AccountsModelImpl();
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		String emailUser = (String) session.getAttribute(Const.SESSION_USER);
		Accounts user = accountsModel.getAccount(emailUser);
		if(user.getRole().getId() == 3) {
			resp.sendRedirect(req.getContextPath() + "/forbidden");
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
