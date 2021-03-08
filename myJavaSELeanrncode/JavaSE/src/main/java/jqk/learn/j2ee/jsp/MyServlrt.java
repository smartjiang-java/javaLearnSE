package jqk.learn.j2ee.jsp;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author:JQK
 * @Date:2020/12/28 16:53
 * @Package:jqk.learn.j2ee.jsp
 * @ClassName:MyServlrt
 **/

public class MyServlrt  implements Servlet {
    @Override
    public void init(ServletConfig config) {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            HttpServletRequest request= (HttpServletRequest) req;
        String method = request.getMethod();
           //  method == GET 或者POST
        if("GET".equalsIgnoreCase(method)){
            doGet(req,res);
        }
        if("POST".equalsIgnoreCase(method)){
            doPost(req,res);
        }

    }

    protected void doGet(ServletRequest req, ServletResponse resp)  {

    }

    protected void doPost(ServletRequest req, ServletResponse resp) {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}


