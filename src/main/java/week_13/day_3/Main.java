package week_13.day_3;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.File;
import java.net.URL;

public class Main {
    // Week 13 Day 04
    public static void main(String[] args) throws LifecycleException {
        String contextPath = "/myapp";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setHostname("localhost");
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        ctx.addLifecycleListener(new ContextConfig());
        final WebResourceRoot root = new StandardRoot(ctx);
        final URL url = findClassLocation(Main.class);
        root.createWebResourceSet(WebResourceRoot.ResourceSetType.PRE, "/WEB-INF/classes", url, "/");
        ctx.setResources(root);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static URL findClassLocation(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation();
    }

    // Week 13 Day 03
//    public static void main(String[] args) {
//        String BASE_URI = "http://localhost:8080/";
//        ResourceConfig resourceConfig = new ResourceConfig(ProductResource.class);
//        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
//        System.out.println("Server started at: " + BASE_URI);
//    }
}
