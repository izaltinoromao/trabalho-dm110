package br.inatel.dm110.beans;

import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.entity.AuditMessage;
import br.inatel.dm110.interfaces.AuditLocal;
import br.inatel.dm110.interfaces.AuditRemote;
import br.inatel.dm110.support.AuditConverter;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class AuditBean implements AuditLocal, AuditRemote {

    @PersistenceContext(unitName = "trabalho_dm110_pu")
    private EntityManager em;

    private static Logger log = Logger.getLogger(AuditBean.class.getName());

    @Override
    public void saveAudit(String message) {
        log.info("Saving Audit: " + message);
        AuditMessage entity = new AuditMessage(
                LocalDateTime.now(),
                message
        );

        em.persist(entity);
    }

    @Override
    public List<AuditMessageTO> getAllAudits() {
        log.info("Searching all audits");
        TypedQuery<AuditMessage> query = em.createQuery("select a from AuditMessage a", AuditMessage.class);
        return AuditConverter.toAuditMessageTOList(query.getResultList());
    }
}
