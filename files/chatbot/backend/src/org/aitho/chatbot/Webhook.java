package org.aitho.chatbot;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aitho.request.AgentRequest;
import com.google.gson.Gson;

/**
 * Servlet implementation class Webhook
 */

@WebServlet("/Webhook")
public class Webhook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Webhook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson js = new Gson();
			String json = convertStreamToString(request.getInputStream());
			AgentRequest agentRequest = js.fromJson(json, AgentRequest.class);
			Controller.handle(agentRequest, response);
		}catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static String convertStreamToString(java.io.InputStream is) {
		Scanner scanner = new Scanner(is);
		scanner.useDelimiter("\\A");
		String output = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
	    return output;
	}

}
