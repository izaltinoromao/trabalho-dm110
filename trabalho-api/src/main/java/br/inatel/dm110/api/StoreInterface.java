package br.inatel.dm110.api;

import jakarta.ws.rs.core.Response;

import java.util.List;

public interface StoreInterface {

    Response storeNewProduct(ProductTO product);

    Response getProduct(String productCode);

    List<ProductTO> getAllProducts();

    Response updateProduct(ProductTO product, String productCode);

    Response deleteProduct(String productCode);
}
