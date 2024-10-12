package br.inatel.dm110.beans;

import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.entity.Product;
import br.inatel.dm110.enums.Operation;
import br.inatel.dm110.exceptions.ProductNotFoundException;
import br.inatel.dm110.interfaces.StoreLocal;
import br.inatel.dm110.interfaces.StoreRemote;
import br.inatel.dm110.support.StoreConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Local(StoreLocal.class)
@Remote(StoreRemote.class)
public class StoreBean implements StoreLocal, StoreRemote {

    private static final Logger log = Logger.getLogger(StoreBean.class.getName());
    @EJB
    AuditQueueSender queueSender;
    @PersistenceContext(unitName = "trabalho_dm110_pu")
    private EntityManager em;

    @Override
    public ProductTO storeNewProduct(ProductTO product) {
        log.info("Saving product: " + product.getProductCode());
        Product entity = StoreConverter.toProduct(product);
        em.persist(entity);

        AuditMessageTO auditMessageTO = new AuditMessageTO(product.getProductCode(),
                Operation.CREATE.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTO(entity);
    }

    @Override
    public ProductTO getProduct(String productCode) {
        log.info("Search product where productCode= " + productCode);
        Product product = getProductEntity(productCode);

        AuditMessageTO auditMessageTO = new AuditMessageTO(product.getProductCode(),
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTO(product);
    }

    @Override
    public List<ProductTO> getAllProducts() {
        log.info("Search for all products: ");
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);

        AuditMessageTO auditMessageTO = new AuditMessageTO("all products",
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTOList(query.getResultList());
    }

    @Override
    public ProductTO updateProduct(ProductTO product, String productCode) {
        log.info("Update product where productCode= " + productCode);

        Product productEntity = getProductEntity(productCode);
        productEntity.setAmountStored(product.getAmountStored());
        productEntity.setLocation(product.getLocation());
        productEntity.setEnterDate(product.getEnterDate());
        productEntity.setMinimumAmount(product.getMinimumAmount());

        em.merge(productEntity);

        AuditMessageTO auditMessageTO = new AuditMessageTO(productEntity.getProductCode(),
                Operation.UPDATE.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTO(productEntity);
    }

    @Override
    public void deleteProduct(String productCode) {
        log.info("Delete product where productCode= " + productCode);

        Product productEntity = getProductEntity(productCode);

        if (productEntity != null) {
            em.remove(productEntity);
        }

        AuditMessageTO auditMessageTO = new AuditMessageTO(productCode,
                Operation.DELETE.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);
    }

    public Product getProductEntity(String productCode) {
        Product product = em.find(Product.class, productCode);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with code: " + productCode);
        }
        return product;
    }
}
