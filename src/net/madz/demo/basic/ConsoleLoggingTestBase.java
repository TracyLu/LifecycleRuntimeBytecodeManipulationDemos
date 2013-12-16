package net.madz.demo.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ConsoleLoggingTestBase {
    @BeforeClass
    public static void setLogLevel() throws SecurityException, FileNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("bin/lifecycle_logging.properties"));
    }
    @Rule
    public TestRule loggerRule = new TestRule() {
    
            @Override
            public Statement apply(Statement base, Description description) {
                return new LoggerStatement(base, description);
            }
        };

    protected static class LoggerStatement extends Statement {
    
            private Statement base;
            private Description description;
    
            public LoggerStatement(Statement base, Description description) {
                this.base = base;
                this.description = description;
            }
    
            @Override
            public void evaluate() throws Throwable {
                final String displayName = description.getDisplayName();
                System.out.println();
                System.out.println();
                System.out.println("########################################################################################################################");
                System.out.println("Processing test: " + displayName);
                base.evaluate();
                System.out.println();
                System.out.println("Finish test: " + displayName);
                System.out.println("########################################################################################################################");
            }
        }

    public ConsoleLoggingTestBase() {
        super();
    }
}