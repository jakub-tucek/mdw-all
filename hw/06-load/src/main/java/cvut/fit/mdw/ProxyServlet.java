package cvut.fit.mdw;

import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Optional;

public class ProxyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Access");

        // url
        Optional<ServerWrapper> url = RunnerSingleton.getLoadBalancer().getUrl();

        if (!url.isPresent()) {
            response.setStatus(500);
            return;
        }
        ServerWrapper serverWrapper = url.get();

        System.out.println("Used url for loading is:" + serverWrapper.getServerName());

        HttpURLConnection connection = (HttpURLConnection) (new URL(serverWrapper.getServerName())).openConnection();
        // HTTP method
        connection.setRequestMethod("GET");
        // copy headers
        Enumeration headerNames = request.getHeaderNames();

        Object elem;
        while (headerNames.hasMoreElements()) {
            Object element = headerNames.nextElement();
            String head = (String) element;
            connection.setRequestProperty(head, request.getHeader(head));
        }

        // copy body
        BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        ServletOutputStream sout = response.getOutputStream();
        while ((inputLine = inputStream.readLine()) != null) {
            sout.write(inputLine.getBytes());
        }
        // close
        inputStream.close();
        sout.flush();
    }

    @PreDestroy
    public void destroy() {
        RunnerSingleton.getLoadBalancer().stop();
    }
}
