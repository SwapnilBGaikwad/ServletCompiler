import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServer() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.sendRedirect("/Compiler/MainPage.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			response.setContentType("text/html");
			PrintWriter out = null;
			int programLang = 0;
			try {
				out = response.getWriter();
				ProgramReader reader = new ProgramReader(request);
				reader.readProgram();
				reader.readInput();
				programLang = reader.getProgramLang();
			} catch (IOException e) {
				out.print("<h2>Error in reading files...........</h2>");
			}
			catch (NullPointerException e) {
				out.print("<h2>Error in reading files........... NullPointer.</h2>");
			}
			
			ServerCompiler compiler = new ServerCompiler();
			String compileOutput = compiler.compile(programLang);
			if(compileOutput == null) {
				String output = compiler.execute();
				out.print(output);
			}
			else {
				out.print("<h2>Compiler Error.</h2> " + compileOutput);
			}
	}

}
