package extjs;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ExtServlet extends HttpServlet {

	private static final long serialVersionUID = -7289832339111126263L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("接收到前台请求, 即将对请求进行处理!");
		int start = Integer.valueOf(request.getParameter("start"));
		int limit = Integer.valueOf(request.getParameter("limit"));

		String jsonStr = null;
		Map<String, Object> jsonObj = new HashMap<String, Object>();
		List<Person> persons = new ArrayList<Person>();
		int totalCount = 20;
		for (int i = 1; i <= totalCount; i++) {
			Person p = new Person(i, "张三" + i, formatDate(new Date()));
			persons.add(p);
		}
		persons = persons.subList(start, limit + start);
		jsonObj.put("data", persons);
		jsonObj.put("totalCount", totalCount);
		response.setContentType("application/x-json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		jsonStr = gson.toJson(jsonObj);
		out.print(jsonStr);
		out.close();
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

}
