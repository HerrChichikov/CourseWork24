package kurs24;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Console;
import java.io.IOException;


@WebServlet(name="Calc", urlPatterns="/Calc")
public class Calc extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCalc Calc = RequestCalc.fromRequestParameters(request);
		Calc.setAsRequestAttributesAndCalculate(request);
		if (Calc.x==0) {
			request.getRequestDispatcher("/Results.jsp").forward(request, response);
		}else if (Calc.x==5) {
			//request.getRequestDispatcher("/Index.jsp").forward(request, response);
			String path=request.getContextPath()+"/index.jsp";
			response.sendRedirect(path);
		}
		
		
	}
	private static class RequestCalc{
		private String first_calc;
		private String second_calc;
		private double result;
		private static int x=0;
		
		private RequestCalc(String first, String second){
		this.first_calc=first;
		this.second_calc=second;
		}
		
		public static RequestCalc fromRequestParameters(HttpServletRequest request) {
			return new RequestCalc(
					request.getParameter("first"),
					request.getParameter("second"));	
		}
		
		public void setAsRequestAttributesAndCalculate(HttpServletRequest request) {
			request.setAttribute("first", first_calc);
			request.setAttribute("second", second_calc);
			int first_try;
			int second_try;
			x=0;
			try {
				first_try=Integer.parseInt(first_calc);
				second_try=Integer.parseInt(second_calc);
			}
			catch (NumberFormatException e) {
				first_try=0;
				second_try=0;
				x=5;
			}
			result=(first_try*second_try)/3;
			request.setAttribute("result", result);
		}
		
	}
	
}