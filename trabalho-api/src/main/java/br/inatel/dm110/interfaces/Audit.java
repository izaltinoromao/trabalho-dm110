package br.inatel.dm110.interfaces;

import br.inatel.dm110.api.AuditMessageTO;

import java.util.List;

public interface Audit {

    public void saveAudit(String message);

    public List<AuditMessageTO> getAllAudits();

 }
