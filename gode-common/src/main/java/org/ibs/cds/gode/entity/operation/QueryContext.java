package org.ibs.cds.gode.entity.operation;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.pagination.PageContext;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueryContext {
    private Predicate predicate;
    private PageContext pageContext;
}
