package com.mani.javaprojects.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mani.javaprojects.Bo.LoginBo;
import com.mani.javaprojects.Vo.LoginVO;
import com.mani.javaprojects.Vo.RegisterVO;


/**
 * Servlet implementation class StarFlagServlet
 */

public class StarFlagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public StarFlagServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("flowcontroller::::" + request.getParameter("flowcontroller"));
		System.out.println(request.getParameter("submit"));
		

		if ("Login".equalsIgnoreCase(request.getParameter("submit"))) {
			LoginVO login = new LoginVO();
			login.setAccountno(Integer.parseInt(request.getParameter("username")));
			login.setPassword(request.getParameter("password"));

			String result = LoginBo.check_login(login);
			System.out.println(result);
			System.out.println(login.getAccountno());
			if (result == null) {
				System.out.println("Not good combo");
				request.setAttribute("error", "Not a valid username and password");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
				

			} else if (result.equals("" + login.getAccountno())) {
				request.getSession().invalidate();
				System.out.println("Session value false::::" + request.getSession(false));
				HttpSession userSession = request.getSession();
				System.out.println(userSession);
				userSession.setAttribute("account", login.getAccountno());
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			} else {
				System.out.println("some error................");
				request.setAttribute("error", result);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else if ("register".equalsIgnoreCase(request.getParameter("submit"))) {
			RegisterVO register = new RegisterVO();
			register.setName(request.getParameter("name"));
			try {
				if (request.getParameter("confirmpassword").equals(request.getParameter("password")))
					register.setPassword(request.getParameter("password"));
				else
					throw new Exception("Passwords must be same");
				register.setEmail(request.getParameter("email"));
				if (request.getParameter("address").length() < 100)
					register.setAddress(request.getParameter("address"));
				else
					throw new Exception("Address length must be less than 100 characters");
				register.setOpenBalance(Integer.parseInt(request.getParameter("open")));
				String result = LoginBo.registerAccount(register);
				System.out.println(result);
				request.setAttribute("error", result);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			} catch (Exception exp) {
				exp.printStackTrace();
				request.setAttribute("error", exp.getMessage());
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else if ("withdraw".equalsIgnoreCase(request.getParameter("submit"))
				|| "deposit".equalsIgnoreCase(request.getParameter("submit"))) {
			double amount = Integer.parseInt(request.getParameter("amount"));
			
			try {
				double availableBalance=LoginBo.getBalance(request.getSession().getAttribute("account").toString());
				if ("deposit".equalsIgnoreCase(request.getParameter("submit")))
					amount = amount + availableBalance;
				else
					amount = -amount + availableBalance;
				request.setAttribute("result", "Balance");
				if(LoginBo.updateBalance(amount, request.getSession().getAttribute("account").toString()))
				{
					request.setAttribute("balance", amount);
					request.setAttribute("message", "Updated message: Balance after " + request.getParameter("submit") + ":");
				}else{request.setAttribute("balance", availableBalance);request.setAttribute("message", "Cannot withdraw more than ");}

				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			} catch (Exception e) {

				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			}
		} else if ("logout".equals(request.getParameter("flowcontroller"))) {
			if (request.getSession().getAttribute("account") != null) {
				request.getSession().invalidate();
			}
			System.out.println(request.getSession(false));
			System.out.println("Sending redirect");
			response.sendRedirect("Login.jsp");
		} else if ("Deposit".equals(request.getParameter("flowcontroller"))) {
			if (request.getSession().getAttribute("account") != null) {
				request.setAttribute("result", "deposit");
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Login to continue");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}

		} else if ("Withdraw".equals(request.getParameter("flowcontroller"))) {
			if (request.getSession().getAttribute("account") != null) {
				request.setAttribute("result", "withdraw");
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Login to continue");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else if ("Balance".equals(request.getParameter("flowcontroller"))) {
			if (request.getSession().getAttribute("account") != null) {
				System.out.println("Checking balance");
				try {

					System.out.println("Account Number:" + request.getSession().getAttribute("account"));
					double balance = LoginBo.getBalance(request.getSession().getAttribute("account").toString());
					System.out.println("Balance:" + balance);
					request.setAttribute("result", "Balance");
					request.setAttribute("balance", balance);
					request.setAttribute("message", "Balance:");
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				} catch (SQLException exp) {
					exp.printStackTrace();
					request.setAttribute("error", exp.getMessage());
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				} catch (Exception exp) {
					exp.printStackTrace();
					request.setAttribute("error", exp.getMessage());
					request.getRequestDispatcher("homepage.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Login to continue");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}

		else {
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
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
