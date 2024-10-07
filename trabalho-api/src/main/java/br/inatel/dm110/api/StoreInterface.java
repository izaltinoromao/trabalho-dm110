package br.inatel.dm110.api;

import jakarta.ws.rs.core.Response;

import java.util.List;

public interface StoreInterface {

    public List<ProductTO> getAllProductCodes();

    public ProductTO getProduct(String productCode);

    public Response getProductAmount(String productCode);

    public Response getMinimumAmount(String productCode);

    public Response getLocation(String productCode);

    public Response getEnterDate(String productCode);

    public Response storeNewProduct(ProductTO product);
}
