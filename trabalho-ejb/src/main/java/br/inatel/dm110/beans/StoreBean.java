package br.inatel.dm110.beans;

import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.api.ProductTO;
import br.inatel.dm110.entity.Product;
import br.inatel.dm110.enums.Operation;
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

    private static Logger log = Logger.getLogger(StoreBean.class.getName());


    @PersistenceContext(unitName = "trabalho_dm110_pu")
    private EntityManager em;

    @EJB
    AuditQueueSender queueSender;

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
    public List<ProductTO> getAllProductCodes() {
        log.info("Search for all products: ");
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);

        AuditMessageTO auditMessageTO = new AuditMessageTO(null,
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTOList(query.getResultList());
    }


    @Override
    public ProductTO getProduct(String productCode) {
        log.info("Search product where productCode= " + productCode);
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productCode = :productCode", Product.class);
        query.setParameter("productCode", productCode);
        List<Product> productList = query.getResultList();

        if (productList.isEmpty()) {
            return null;
        }

        Product product = productList.get(0);

        AuditMessageTO auditMessageTO = new AuditMessageTO(product.getProductCode(),
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return StoreConverter.toProductTO(product);
    }


    @Override
    public int getProductAmount(String productCode) {
        log.info("Search Product Amount, where productCode= " + productCode);
        TypedQuery<Integer> query = em.createQuery("SELECT p.amountStored FROM Product p WHERE p.productCode = :productCode", Integer.class);
        query.setParameter("productCode", productCode);
        List<Integer> amountList = query.getResultList();
        if (amountList.isEmpty()) {
            return 0;
        }

        AuditMessageTO auditMessageTO = new AuditMessageTO(productCode,
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return amountList.get(0);
    }

    @Override
    public int getMinimumAmount(String productCode) {
        log.info("Search Minimum Amount, where productCode= " + productCode);
        TypedQuery<Integer> query = em.createQuery("SELECT p.minimumAmount FROM Product p WHERE p.productCode = :productCode", Integer.class);
        query.setParameter("productCode", productCode);
        List<Integer> minList = query.getResultList();
        if (minList.isEmpty()) {
            return 0;
        }

        AuditMessageTO auditMessageTO = new AuditMessageTO(productCode,
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return minList.get(0);
    }


    @Override
    public String getLocation(String productCode) {
        log.info("Search Location, where productCode= " + productCode);
        TypedQuery<String> query = em.createQuery("SELECT p.location FROM Product p WHERE p.productCode = :productCode", String.class);
        query.setParameter("productCode", productCode);
        List<String> locationList = query.getResultList();
        if (locationList.isEmpty()) {
            return null;
        }

        AuditMessageTO auditMessageTO = new AuditMessageTO(productCode,
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return locationList.get(0);
    }

    @Override
    public int getEnterDate(String productCode) {
        log.info("Search for how long product has been stored, where productCode= " + productCode);
        TypedQuery<Integer> query = em.createQuery("SELECT p.enterDate FROM Product p WHERE p.productCode = :productCode", Integer.class);
        query.setParameter("productCode", productCode);
        List<Integer> entryDateList = query.getResultList();
        if (entryDateList.isEmpty()) {
            return 0;
        }

        AuditMessageTO auditMessageTO = new AuditMessageTO(productCode,
                Operation.GET.getOperation(),
                LocalDateTime.now());
        queueSender.sendTextMessage(auditMessageTO);

        return entryDateList.get(0);
    }
}
