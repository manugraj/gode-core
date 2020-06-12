package org.ibs.cds.gode.entity.store.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.ibs.cds.gode.entity.codec.DateToOffsetDateTimeConverter;
import org.ibs.cds.gode.entity.codec.OffsetDateTimeToDateConverter;
import org.ibs.cds.gode.entity.store.condition.MongoDBEnabler;
import org.ibs.cds.gode.system.GodeAppEnvt;
import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;


@Configuration
@Conditional(MongoDBEnabler.class)
@EnableMongoRepositories(basePackages = GodeConstant.ENTITY_BASE_PACKAGE_NAME)
@ComponentScan(GodeConstant.GODE_BASE_PACKAGE_NAME)
@PropertySource(GodeAppEnvt.GODE_PROPERTIES)
@EntityScan(GodeConstant.ENTITY_BASE_PACKAGE_ALL)
public class MongoDBStoreConfig extends AbstractMongoClientConfiguration  {

    private final Environment environment;
    private final List<Converter<?,?>> codecList;

    @Autowired
    public MongoDBStoreConfig(Environment environment){
        this.environment = environment;
        codecList = List.of(
                new DateToOffsetDateTimeConverter(),
                new OffsetDateTimeToDateConverter()
        );
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString string = new ConnectionString(environment.getProperty("gode.datastore.mongodb.uri"));
        return MongoClients.create(string);
    }

    @Override
    protected String getDatabaseName() {
        return mongoClient().getDatabase(environment.getProperty("gode.datastore.mongodb.database.name")).getName();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());

    }
    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(codecList);
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
