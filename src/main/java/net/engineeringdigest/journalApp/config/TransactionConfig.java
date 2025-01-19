package net.engineeringdigest.journalApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory dbFactory) { //DBfactory is used to establish a connectoion with the database
        return new MongoTransactionManager(dbFactory);
    }
}
/*
 * To Use Transactional Container , we gotta configure some stuff first
 *
 * PlatformTransactionManager and MongotransactionManager.
 *  An instance of PlatformTransactionManager will be returned named MongotransactionManager
 *
 * SimpleMongoClientDatabaseFactory is the implementation of MongoDatabaseFactory
 * */