package br.inatel.dm110.impl;

import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.api.StoreInterface;
import br.inatel.dm110.interfaces.StoreLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@RequestScoped
@Path("/store")
public class StoreResource implements StoreInterface {

    @EJB
    private StoreLocal storeBean;

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<ProductTO> getAllProductCodes() {
        return storeBean.getAllProductCodes();
    }

    @GET
    @Path("/product/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public ProductTO getProduct(@PathParam("productCode")String productCode) {

        return storeBean.getProduct(productCode);
    }

    @GET
    @Path("/product/amount/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getProductAmount(@PathParam("productCode")String productCode) {

        int amountStored = storeBean.getProductAmount(productCode);

        JsonObject json = Json.createObjectBuilder()
                .add("amountStored", amountStored)
                .build();

        return Response.ok(json).build();
    }

    @GET
    @Path("/product/minimum/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getMinimumAmount(@PathParam("productCode")String productCode) {

        int minimumAmount = storeBean.getMinimumAmount(productCode);

        JsonObject json = Json.createObjectBuilder()
                .add("minimumAmount", minimumAmount)
                .build();

        return Response.ok(json).build();
    }

    @GET
    @Path("/product/location/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getLocation(@PathParam("productCode")String productCode) {

        String location = storeBean.getLocation(productCode);

        JsonObject json = Json.createObjectBuilder()
                .add("location", location)
                .build();

        return Response.ok(json).build();
    }

    @GET
    @Path("/product/enterDate/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getEnterDate(@PathParam("productCode")String productCode) {

        int enterDate = storeBean.getEnterDate(productCode);

        JsonObject json = Json.createObjectBuilder()
                .add("enterDate", enterDate)
                .build();

        return Response.ok(json).build();
    }

    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response storeNewProduct(ProductTO product) {
        ProductTO productTO = storeBean.storeNewProduct(product);
        return Response.status(Response.Status.CREATED).entity(productTO).build();
    }
}
