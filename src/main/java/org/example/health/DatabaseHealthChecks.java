package org.example.health;

import com.codahale.metrics.health.HealthCheck;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public final class DatabaseHealthChecks extends HealthCheck {

    private final Jdbi jdbi;

    private final String validationQuery;

    public DatabaseHealthChecks(Jdbi jdbi, String validationQuery) {
        this.jdbi = jdbi;
        this.validationQuery = validationQuery;
    }

    @Override
    protected Result check() {
        try {
            final Handle handle = jdbi.open();
            handle.execute(validationQuery);
            handle.close();
        } catch (Exception e) {
            return Result.unhealthy("Database is not running!");
        }

        return Result.healthy();
    }
}