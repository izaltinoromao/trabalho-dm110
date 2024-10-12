package br.inatel.dm110.interfaces;

import br.inatel.dm110.api.ProductTO;

import java.util.List;

public interface Store {

    ProductTO storeNewProduct(ProductTO product);

    ProductTO getProduct(String productCode);

    List<ProductTO> getAllProducts();

    ProductTO updateProduct(ProductTO product, String productCode);

    void deleteProduct(String productCode);

}
