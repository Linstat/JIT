package ru.sivak.bugify.appmanager;

import org.apache.http.client.fluent.Executor;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class HelperBase {
    protected ApplicationManager app;

    public HelperBase(ApplicationManager app) {
        this.app = app;
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }
}
