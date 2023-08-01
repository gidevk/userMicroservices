package com.expriment.utils.audit.Hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomDatabaseIdentifierNamingStrategy extends PhysicalNamingStrategyStandardImpl implements PhysicalNamingStrategy {

    private static final long serialVersionUID = 5347527373255517985L;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        // Custom logic to modify the table name
        String modifiedName = name.getText();// + "_suffix";
        return Identifier.toIdentifier(modifiedName);
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return name;
    }
}
