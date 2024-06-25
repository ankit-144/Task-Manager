package org.example;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.dao.TaskDAO;
import org.example.health.DatabaseHealthChecks;
import org.example.resources.TaskResource;
import org.jdbi.v3.core.Jdbi;
/*import io.prometheus.client.dropwizard.DropwizardExports;*/



public class TodoApplication extends Application<TodoApplicationConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public String getName() {
        return "TodoApplication";
    }

    @Override
    public void initialize(final Bootstrap<TodoApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TodoApplicationConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(final TodoApplicationConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final TodoApplicationConfiguration configuration,
                    final Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "db");
        final TaskDAO taskDAO = jdbi.onDemand(TaskDAO.class);

        environment.jersey().register(new TaskResource(taskDAO));

        environment.healthChecks().register("health",
                new DatabaseHealthChecks(jdbi, configuration.getDataSourceFactory().getValidationQuery().orElse("SELECT 1")));

        /*registerMetrics(environment);*/
    }
    /*private void registerMetrics(Environment environment) {
        final MetricRegistry registry = environment.metrics();
        new DropwizardExports(registry).register();
    }*/
}
