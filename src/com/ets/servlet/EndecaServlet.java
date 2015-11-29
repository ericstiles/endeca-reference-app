package com.ets.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ets.EndecaQueryProcessor;
import utils.ParameterUtils;
import com.ets.model.ResponseBean;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(description = "EndecaServlet Description", urlPatterns = { "/EndecaServlet" })
public class EndecaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nextJSP = "/index.jsp";
	private String BEAN = "bean";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EndecaServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EndecaQueryProcessor eqp = new EndecaQueryProcessor();
		ResponseBean bean = getBean(request);
		
		bean.setParameterMap(request.getParameterMap());	
		bean.setReferringURL(request.getHeader("referer"));
		bean.setRequestParameters(ParameterUtils.getEndecaQueryString(request.getParameterMap()));
		bean.setRequestUrl(request.getRequestURL().toString());
		eqp.run(bean);
		request.getSession().setAttribute(BEAN, bean);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	private ResponseBean getBean(HttpServletRequest request) {
		if (request.getSession().getAttribute(BEAN) == null) return new ResponseBean();
		return (ResponseBean) request.getSession().getAttribute(BEAN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
