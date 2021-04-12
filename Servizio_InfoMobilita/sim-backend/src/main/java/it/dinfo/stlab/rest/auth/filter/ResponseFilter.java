package it.dinfo.stlab.rest.auth.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException{
        responseContext.getHeaders().add("Access-Control-Allow-Origin","*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials","true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods","GET,POST,DELETE,PUT,PATCH");
    }

}
