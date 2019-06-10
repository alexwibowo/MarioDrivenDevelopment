package com.wibowo.intellij.mdd.events;

import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.CommitContext;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import org.jetbrains.annotations.NotNull;

public class CheckinHandlerFactory extends com.intellij.openapi.vcs.checkin.CheckinHandlerFactory {
    public static VcsEvent.Listener listener;

    @NotNull
    @Override
    public CheckinHandler createHandler(@NotNull CheckinProjectPanel checkinProjectPanel,
                                        @NotNull CommitContext commitContext) {
        return new CheckinHandler() {
            @Override public void checkinSuccessful() {
                if (listener != null) {
                    listener.onVcsCommit();
                }
            }
        };
    }
}
