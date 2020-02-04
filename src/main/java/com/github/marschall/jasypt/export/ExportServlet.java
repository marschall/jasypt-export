package com.github.marschall.jasypt.export;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportServlet extends HttpServlet {

  private static final String CXF_CLASS = "org.apache.cxf.Bus";

  private static final String JASYPT_CLASS = "org.jasypt.util.password.StrongPasswordEncryptor";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("text/plain");
    response.setCharacterEncoding("US-ASCII");

    PrintWriter writer = response.getWriter();
    writer.println("CXF visible: " + isClassVisible(CXF_CLASS));
    writer.println("Jasypt visible: " + isClassVisible(JASYPT_CLASS));
  }

  private static boolean isClassVisible(String className) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try {
      classLoader.loadClass(className);
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

}
