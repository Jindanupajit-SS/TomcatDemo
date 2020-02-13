package com.smoothstack.TomcatDemo.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smoothstack.TomcatDemo.dto.Credential;
import com.smoothstack.TomcatDemo.dto.User;
import com.smoothstack.TomcatDemo.util.StringUtil;

/**
 * Servlet implementation class RestService
 */
@WebServlet({"/login-api"})
public class RestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private Map<Integer, User> userMap = new HashMap<>();
	private Map<Integer, Credential> credentialMap = new HashMap<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestService() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	userMap.put(1, new User(1, "Brat", "Pitt"));
		userMap.put(2, new User(2, "Al", "Pacino"));
		userMap.put(3, new User(3, "Al", "Pacino"));
		userMap.put(4, new User(4, "Natalie", "Portman"));
		
		credentialMap.put(1, new Credential(1, userMap.get(1), "user1", sha256("password")));
		credentialMap.put(2, new Credential(1, userMap.get(2), "user2", sha256("password")));
		credentialMap.put(3, new Credential(1, userMap.get(3), "user3", sha256("password")));
		credentialMap.put(4, new Credential(1, userMap.get(4), "user4", sha256("password")));

		System.out.println("INIT !");

		
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuilder sb= new StringBuilder();
		
		try (InputStream in = request.getInputStream() ) {

		
			int byteCount = 0;
			
			while((byteCount = in.available())  > 0) {
				byte[] b = new byte[byteCount];
				in.read(b);
				sb.append(new String(b));
			}
		} catch (Exception e) {}
		
		
		
		
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		Gson gson = builder.setPrettyPrinting().create();
		int status = HttpServletResponse.SC_FORBIDDEN;

		Map<String, List<String>> query = StringUtil.getQuery(sb.toString());
		Credential credential = null;
		String username = null;
		String password = null;
		User user = null;
		System.out.printf("Query String = %s\n",sb.toString());
		
		if (!query.containsKey("username") ||  !query.containsKey("password") ||
				(null == (credential = getCredential(username=query.get("username").get(0), password=query.get("password").get(0))))
				) {
			credential = new Credential(0, user = null, username, password);
		}

		else {
			user = credential.getUser();
			status = HttpServletResponse.SC_OK;
		}
		
		Map<String, Object> kv = new HashMap<>();
		
		kv.put("code",  status);
		kv.put("user",  user);
		out.print(gson.toJson(kv));
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Credential getCredential(String username, String password) {
		
		for (int credId : credentialMap.keySet()) {
			Credential credential = credentialMap.get(credId);
			System.out.printf("Checking %s %s\n", username, password);
			System.out.printf("      -> %s %s\n", credential.getLogin(), credential.getPassword());
			if (username.equals(credential.getLogin()) && sha256(password).equals(credential.getPassword())) {
				return credential;
			}
		}
		
		return null;
	}
	
	public String sha256(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
			return (DatatypeConverter.printHexBinary(digest).toLowerCase());
		} catch (NoSuchAlgorithmException e) {
			return (password);
		}

	}

}
