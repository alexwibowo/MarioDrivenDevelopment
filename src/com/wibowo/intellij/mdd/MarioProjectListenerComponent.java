package com.wibowo.intellij.mdd;

import com.intellij.openapi.application.ApplicationListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.wibowo.intellij.mdd.events.SoundPlayingListener;
import com.wibowo.intellij.mdd.events.TestEvent;
import com.wibowo.intellij.mdd.events.VcsEvent;
import com.wibowo.intellij.mdd.sounds.Sounds;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MarioProjectListenerComponent implements BaseComponent {

    private final Map<Project, VcsEvent> vcsEventByProject = new HashMap<>();
    private final Map<Project, TestEvent> testEventByProject = new HashMap<>();

    private SoundPlayingListener soundPlayingListener;
    private ProjectManagerListener projectManagerListener;
    private ApplicationListener applicationListener;

    @Override
    public void initComponent() {
        init();
    }

    @Override
    public void disposeComponent() {
        dispose(true);
    }

    private void init() {
        soundPlayingListener = new SoundPlayingListener(createSounds()).init();
        initApplicationListeners();
        initProjectListeners();
    }

    public void setBackgroundMusicEnabled(boolean value) {
        Settings.getInstance().setBackgroundMusicEnabled(value);
        update();
    }

    public void setActionSoundsEnabled(boolean value) {
        Settings.getInstance().setActionSoundsEnabled(value);
        update();
    }

    private void dispose(final boolean isIdeShutdown) {
        if (soundPlayingListener == null){
            return;
        }

        disposeProjectListeners();
        disposeApplicationListeners();
        soundPlayingListener.stop(isIdeShutdown);
        soundPlayingListener = null;
    }

    private void update() {
        Settings settings = Settings.getInstance();
        if (!settings.actionSoundsEnabled && !settings.backgroundMusicEnabled) {
            dispose(false);
        } else {
            dispose(false);
            init();
        }
    }

    private Sounds createSounds() {
        final Settings settings = Settings.getInstance();
        if (settings != null) {
            return Sounds.create(settings.actionSoundsEnabled, settings.backgroundMusicEnabled);
        } else {
            return Sounds.create(false, false);
        }
    }

    private void initProjectListeners() {
        projectManagerListener = new ProjectManagerListener() {
            @Override
            public void projectOpened(@NotNull Project project) {
                soundPlayingListener.onProjectOpened();
                vcsEventByProject.computeIfAbsent(project, project1 -> {
                    final VcsEvent vcsEvent = new VcsEvent(project1, soundPlayingListener);
                    vcsEvent.start();
                    return vcsEvent;
                });

                testEventByProject.computeIfAbsent(project, project12 -> {
                    final TestEvent testEvent = new TestEvent(project12, soundPlayingListener);
                    testEvent.start();
                    return testEvent;
                });
            }

            @Override
            public void projectClosed(@NotNull Project project) {
                if (vcsEventByProject.containsKey(project)) {
                    vcsEventByProject.get(project).stop();
                    vcsEventByProject.remove(project);
                }
                if (testEventByProject.containsKey(project)) {
                    testEventByProject.get(project).stop();
                    testEventByProject.remove(project);
                }
            }
        };
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            projectManagerListener.projectOpened(project);
        }
        ProjectManager.getInstance().addProjectManagerListener(projectManagerListener);
    }

    private void initApplicationListeners() {
        applicationListener = new ApplicationListener() {
            @Override
            public void applicationExiting() {
                soundPlayingListener.stopAndWait();

            }
        };
        ApplicationManager.getApplication().addApplicationListener(applicationListener);
    }

    private void disposeProjectListeners() {
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            projectManagerListener.projectClosed(project);
        }
        ProjectManager.getInstance().removeProjectManagerListener(projectManagerListener);

    }

    private void disposeApplicationListeners() {
        ApplicationManager.getApplication().removeApplicationListener(applicationListener);
    }

    static MarioProjectListenerComponent instance() {
        return ApplicationManager.getApplication().getComponent(MarioProjectListenerComponent.class);
    }

    @SuppressWarnings("ConstantConditions")
    @NotNull @Override public String getComponentName() {
        return this.getClass().getCanonicalName();
    }

}
