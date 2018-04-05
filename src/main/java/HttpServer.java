import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

enum QueryMethod {
    ACCEPT_JSON("/accept_json"), DECLINE_JSON("/decline_json"), NONE("/error");

    private String stringRepresentation;

    QueryMethod(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    static QueryMethod of(String stringRepresentation) {
        if (stringRepresentation.equals(ACCEPT_JSON.stringRepresentation)) {
            return ACCEPT_JSON;
        } else if (stringRepresentation.equals(DECLINE_JSON.stringRepresentation)) {
            return DECLINE_JSON;
        } else {
            return NONE;
        }
    }

    @Override  public String toString() {
        return stringRepresentation;
    }
}


public class HttpServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getQuery(request, response);
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        postQuery(request, response);
    }

    private void getQuery(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msg = request.getMethod();
        msg += request.getHeader("login");
        msg += " " + request.getHeader("password");

        response.getWriter().print("\n " + msg);
        response.getWriter().print("\n" + parseMethodByUrl(request.getRequestURL().toString()));
        response.getWriter().print("\n" + request.getContextPath().toString());

        response.getWriter().print("\n" + request.getParameter("name"));
    }

    private String parseMethodByUrl(String url) {
        String methodName = url.substring(url.lastIndexOf("/"), url.length() - 1);

        return methodName;
    }

    private void postQuery(HttpServletRequest request, HttpServletResponse response) {

    }
}