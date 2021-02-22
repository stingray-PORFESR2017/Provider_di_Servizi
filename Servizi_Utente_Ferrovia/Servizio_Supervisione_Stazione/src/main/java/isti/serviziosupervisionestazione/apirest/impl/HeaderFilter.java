package isti.serviziosupervisionestazione.apirest.impl;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Singleton
@Provider
public class HeaderFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String contentLength = responseContext.getHeaderString("X-Content-Length");
       // responseContext.getLength();
            //responseContext.getHeaders().remove("Transfer-Encoding");
            //responseContext.getHeaders().remove("X-Content-Length");
          //  responseContext.getHeaders().putSingle("Content-Length", contentLength);
          //  responseContext.getHeaders().add("X-Content-Length", responseContext.getLength());
    }
    
}