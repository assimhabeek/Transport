package bda1.services;

import bda1.utils.HttpConnection;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class BaseService<T> {

    private String path;
    private final Class<T> clazz;

    public BaseService(String path, Class<T> type) {
        this.path = path;
        this.clazz = type;
    }

    public Set<T> findAll() {
        ClientResponse response = HttpConnection.getResource()
                .path(this.path)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(getParameterizedSetType());
    }

    public T find(int id) {
        ClientResponse response = HttpConnection.getResource()
                .path(this.path + "/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response.getEntity(clazz);
    }

    public int create(T element) {
        ClientResponse response = HttpConnection.getResource()
                .path(this.path)
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, element);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return Integer.parseInt(response.getEntity(String.class));
    }


    public boolean update(T element) {
        ClientResponse response = HttpConnection.getResource()
                .path(this.path)
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, element);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return Boolean.parseBoolean(response.getEntity(String.class));
    }

    public boolean delete(T element) {
        ClientResponse response = HttpConnection.getResource()
                .path(this.path)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class, element);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return response.getEntity(boolean.class);
    }

    private GenericType<Set<T>> getParameterizedSetType() {
        ParameterizedType parameterizedGenericType = new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            public Type getRawType() {
                return Set.class;
            }

            public Type getOwnerType() {
                return Set.class;
            }
        };
        return new GenericType<Set<T>>(parameterizedGenericType) {
        };
    }
}
