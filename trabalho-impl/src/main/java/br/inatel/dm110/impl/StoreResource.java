package br.inatel.dm110.impl;

import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.api.StoreInterface;
import br.inatel.dm110.interfaces.Store;
import br.inatel.dm110.interfaces.StoreLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    public int getProductAmount(@PathParam("productCode")String productCode) {

        return storeBean.getProductAmount(productCode);
    }

    @GET
    @Path("/product/minimum/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public int getMinimumAmount(@PathParam("productCode")String productCode) {

        return storeBean.getMinimumAmount(productCode);
    }

    @GET
    @Path("/product/local/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String getLocation(@PathParam("productCode")String productCode) {

        return storeBean.getLocation(productCode);
    }

    @GET
    @Path("/product/age/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public int getEnterDate(@PathParam("productCode")String productCode) {

        return storeBean.getEnterDate(productCode);
    }

    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void storeNewProduct(ProductTO product) {
        storeBean.storeNewProduct(product);
    }
}
