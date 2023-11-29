package week_13.day_3;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/*
create a query and configure it via setMaxResults and setFirstResult:
Query query = entityManager. createQuery("From Foo"); int pageNumber = 1;
int pageSize = 10; query. setFirstResult((pageNumber-1) * pageSize); query.
 */
public class Main {
    public static void main(String[] args) {
        String BASE_URI = "http://localhost:8080/";
        ResourceConfig resourceConfig = new ResourceConfig(ProductResource.class);
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
        System.out.println("Server started at: " + BASE_URI);
    }
}
