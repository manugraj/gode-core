package org.ibs.cds.gode.pagination;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryContext {

    private ResponsePageContext pageContext;
    private Predicate predicate;
}
