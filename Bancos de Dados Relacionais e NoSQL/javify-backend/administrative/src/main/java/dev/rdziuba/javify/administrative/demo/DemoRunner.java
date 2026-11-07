package dev.rdziuba.javify.administrative.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("demo")
public class DemoRunner implements CommandLineRunner {

    private final JdbcAccess jdbcAccess;
    private final JpaDemo jpaDemo;
    
    public DemoRunner(JdbcAccess jdbcAccess, JpaDemo jpaDemo) {
        this.jdbcAccess = jdbcAccess;
        this.jpaDemo = jpaDemo;
    }


    @Override
    public void run(String... args) throws Exception {
        jdbcAccess.execute();
        jpaDemo.searchAddress();
        jpaDemo.registerAddress();
        jpaDemo.updateAddress();
        jpaDemo.findSignatureWithPlan();
        jpaDemo.removeExpiredCreditCard();
        jpaDemo.testQueries();
    }
    
}
