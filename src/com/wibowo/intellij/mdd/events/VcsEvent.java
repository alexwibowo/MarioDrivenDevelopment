package com.wibowo.intellij.mdd.events;

import com.intellij.openapi.project.Project;

public class VcsEvent {

    private final Listener listener;

    public VcsEvent(final Project project,
                    final Listener listener) {
        this.listener = listener;
    }


    public void start() {
        // using bus to listen to vcs updates because normal listener calls it twice
        // (see also https://gist.github.com/dkandalov/8840509)
        CheckinHandlerFactory.listener = listener;
    }

    public void stop() {
        CheckinHandlerFactory.listener = null;
    }

    public interface Listener {
        void onVcsCommit();

        void onVcsUpdate();

        void onVcsPush();

        void onVcsPushFailed();
    }
}
