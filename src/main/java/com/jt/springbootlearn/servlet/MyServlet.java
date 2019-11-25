package com.jt.springbootlearn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet{



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Hello MyServlet");
    }

    /**
     * A convenience method which can be overridden so that there's no need to
     * call <code>super.init(config)</code>.
     * <p>
     * Instead of overriding {@link #init(ServletConfig)}, simply override this
     * method and it will be called by
     * <code>GenericServlet.init(ServletConfig config)</code>. The
     * <code>ServletConfig</code> object can still be retrieved via
     * {@link #getServletConfig}.
     *
     * @throws ServletException if an exception occurs that interrupts the servlet's
     *                          normal operation
     */
    @Override
    public void init() throws ServletException {
        System.out.println("MyServlet init.....");
        super.init();
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet
     * is being taken out of service. See {@link Servlet#destroy}.
     */
    @Override
    public void destroy() {
        System.out.println("MyServlet destroy....");
        super.destroy();
    }
}
