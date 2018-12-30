package bda1.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class HttpConnection {

    private static WebResource resource = null;

    public static WebResource getResource() {
        return resource != null ? resource : createResource();
    }

    private static WebResource createResource() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        resource = client.resource("http://localhost:8080/rest");
        return resource;
    }


}
