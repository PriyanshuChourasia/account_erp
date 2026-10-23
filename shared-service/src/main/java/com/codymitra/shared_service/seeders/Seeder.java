package com.codymitra.shared_service.seeders;

/**
 * Implement this and annotate with @Component to register a seeder.
 * Seeders must be idempotent: running them twice must not duplicate data.
 */
public interface Seeder {

    /** Name used to pick this seeder from the command line, e.g. --seed=account_nature */
    String name();

    /** Lower runs first. Use it when one seeder depends on another's data. */
    default int order() {
        return 100;
    }

    /** True for master data the app can't work without: runs on every startup, no --seed needed. */
    default boolean runOnStartup() {
        return false;
    }

    void seed();
}
