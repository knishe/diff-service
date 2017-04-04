package com.waes.diff;

import org.glassfish.grizzly.Connection;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.HttpServerFilter;
import org.glassfish.grizzly.http.server.HttpServerProbe;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Test service for diff service integration test
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class DiffServer {
    private static final String BASE_URL = "http://localhost:9090/";

    public static void main(String[] args) {
        try {
            System.out.println("Starting Diff App local testing server: " + BASE_URL);

            final ResourceConfig resourceConfig = new ResourceConfig();
            resourceConfig.register(RestDiffServiceEndpoint.class);

            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URL), resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                server.shutdownNow();
            }));

            HttpServerProbe probe = new HttpServerProbe.Adapter() {
                public void onRequestReceiveEvent(HttpServerFilter filter, Connection connection, Request request) {
                    System.out.println(request.getRequestURI());
                }
            };
            server.getServerConfiguration().getMonitoringConfig().getWebServerConfig().addProbes(probe);

            server.start();
            System.out.println(format("Diff Server started.\n url=%s\n", BASE_URL));

            Thread.currentThread().join();
            server.shutdown();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DiffServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


