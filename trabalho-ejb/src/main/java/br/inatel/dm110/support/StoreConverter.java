package br.inatel.dm110.support;

import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static ProductTO toProductTO(Product product) {
        return new ProductTO(product.getProductCode(),
                product.getAmountStored(),
                product.getMinimumAmount(),
                product.getLocation(),
                product.getEnterDate());
    }

    public static List<ProductTO> toProductTOList(List<Product> productList) {
        return productList.stream().map(StoreConverter::toProductTO).collect(Collectors.toList());
    }

    public static Product toProduct(ProductTO productTO) {
        return new Product(productTO.getProductCode(),
                productTO.getAmountStored(),
                productTO.getMinimumAmount(),
                productTO.getLocation(),
                productTO.getEnterDate());
    }

}
