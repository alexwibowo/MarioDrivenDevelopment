package com.wibowo.intellij.mdd.events;

import com.intellij.execution.testframework.TestsUIUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.notification.NotificationsAdapter;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

public class TestEvent {

    private final Listener listener;
    private final MessageBusConnection messageBusConnection;

    public TestEvent(final Project project,
                     final Listener listener) {
        this.listener = listener;
        this.messageBusConnection = project.getMessageBus().connect();
    }


    public void start() {
        messageBusConnection.subscribe(Notifications.TOPIC, new NotificationsAdapter() {
            @Override public void notify(@NotNull Notification notification) { // Compiler, Test Runner
                if (notification.getGroupId().equals(TestsUIUtil.NOTIFICATION_GROUP.getDisplayId())) {
                    boolean testsFailed = (notification.getType() == NotificationType.ERROR);
                    if (testsFailed) {
                        listener.onUnitTestFailed();
                    } else {
                        listener.onUnitTestSucceeded();
                    }
                }
            }
        });
    }

    public void stop() {
        messageBusConnection.disconnect();
    }

    public interface Listener {
        void onUnitTestSucceeded();

        void onUnitTestFailed();
    }
}
