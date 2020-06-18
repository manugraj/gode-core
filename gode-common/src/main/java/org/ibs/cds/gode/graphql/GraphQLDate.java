package org.ibs.cds.gode.graphql;

import com.github.sisyphsu.dateparser.DateParserUtils;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GraphQLDate extends GraphQLScalarType {

    public GraphQLDate() {
        super("Date", "Built-in Date", new Coercing() {

            @Override
            public Date serialize(Object input) throws CoercingSerializeException {
                Date result = null;
                result = convertImpl(input);
                if (result == null) {
                    throw exception(input);
                }
                return null;
            }

            @Override
            public Date parseValue(Object input) throws CoercingParseValueException {
                Date result = null;
                result = convertImpl(input);
                if (result == null) {
                    throw exception(input);
                }
                return null;
            }

            @Override
            public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                Date result = null;
                result = convertImpl(input);
                if (result == null) {
                    throw exception(input);
                }
                return null;
            }


            private Date convertImpl(Object input) {
                if (input instanceof Date) {
                    return (Date) input;
                } else if (input instanceof String) {
                    return DateParserUtils.parseDate((String) input);
                } else {
                    return null;
                }
            }

            private String typeName(Object input) {
                if (input == null) {
                    return "null";
                }

                return input.getClass().getSimpleName();
            }

            private CoercingSerializeException exception(Object input) {
                return new CoercingSerializeException(
                        "Expected type 'Date' but was '" + typeName(input) + "'."
                );
            }

        });
    }
}
