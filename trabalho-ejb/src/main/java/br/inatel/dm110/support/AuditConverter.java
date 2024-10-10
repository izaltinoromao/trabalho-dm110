package br.inatel.dm110.support;

import br.inatel.dm110.api.AuditMessageTO;
import br.inatel.dm110.entity.AuditMessage;

import java.util.List;
import java.util.stream.Collectors;

public class AuditConverter {

    public static AuditMessageTO toAuditMessageTO(AuditMessage auditMessage) {
        return new AuditMessageTO(auditMessage.getTimeStamp(),
                auditMessage.getMessage());
    }

    public static List<AuditMessageTO> toAuditMessageTOList(List<AuditMessage> auditMessageList) {
        return auditMessageList.stream().map(AuditConverter::toAuditMessageTO).collect(Collectors.toList());
    }
}
