package br.inatel.dm110.impl;

import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.api.StoreInterface;
import br.inatel.dm110.interfaces.StoreLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RequestScoped
@Path("/store")
public class StoreResource implements StoreInterface {

    @EJB
    private StoreLocal storeBean;

    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response storeNewProduct(ProductTO product) {
        ProductTO productTO = storeBean.storeNewProduct(product);
        return Response.status(Response.Status.CREATED).entity(productTO).build();
    }

    @GET
    @Path("/product/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getProduct(@PathParam("productCode") String productCode) {
        ProductTO productTO = storeBean.getProduct(productCode);
        if (productTO != null) {
            return Response.status(Response.Status.OK).entity(productTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<ProductTO> getAllProducts() {
        return storeBean.getAllProducts();
    }

    @PUT
    @Path("/product/{productCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response updateProduct(ProductTO product, @PathParam("productCode") String productCode) {
        ProductTO productTO = storeBean.updateProduct(product, productCode);
        return Response.status(Response.Status.OK).entity(productTO).build();
    }

    @DELETE
    @Path("/product/{productCode}")
    @Override
    public Response deleteProduct(@PathParam("productCode") String productCode) {
        storeBean.deleteProduct(productCode);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
