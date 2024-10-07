package br.inatel.dm110.interfaces;

import br.inatel.dm110.api.ProductTO;

import java.util.List;

public interface Store {

    public List<ProductTO> getAllProductCodes();

    public ProductTO getProduct(String productCode);

    public int getProductAmount(String productCode);

    public int getMinimumAmount(String productCode);

    public String getLocation(String productCode);

    public int getEnterDate(String productCode);

    public void storeNewProduct(ProductTO product);
}
