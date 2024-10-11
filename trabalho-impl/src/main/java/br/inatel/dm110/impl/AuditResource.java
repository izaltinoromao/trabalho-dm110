package br.inatel.dm110.impl;

import br.inatel.dm110.api.AuditInterface;
import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.interfaces.AuditLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@RequestScoped
@Path("/audit")
public class AuditResource implements AuditInterface {

    @EJB
    private AuditLocal auditBean;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<AuditMessageTO> getAllAudits() {
        return auditBean.getAllAudits();
    }
}
