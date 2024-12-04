/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.nightst0rm.entities.Logger;
import net.nightst0rm.entities.Token;
import net.nightst0rm.utils.DataUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author qubu
 */
@WebFilter(filterName = "CSRF_Filter", urlPatterns = {"/*"})
public class CSRF_Filter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Logger logger = new Logger();
        logger.setUser_id((Integer)req.getSession().getAttribute("user_id"));
        try {

            if (req.getParameter("csrf_token") != null) {
                Token t = DataUtils.getObject(req.getParameter("csrf_token"));
                if (t.getToken_value().equals(req.getSession().getAttribute("random_text"))) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("/home.jsp");
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            String alert = "Oops! an exception has occurred! The error message has been logged. ";
            logger.setEvent(e.getMessage());
            logger.insert();
            response.setContentType("text/plain");
            response.getWriter().write(alert);
            return;
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


}
