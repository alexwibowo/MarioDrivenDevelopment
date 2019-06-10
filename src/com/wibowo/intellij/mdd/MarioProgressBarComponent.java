package com.wibowo.intellij.mdd;

import com.intellij.ide.ui.LafManager;

import javax.swing.*;

public class MarioProgressBarComponent {
    public MarioProgressBarComponent() {
        LafManager.getInstance().addLafManagerListener(__ -> updateProgressBarUI());
        updateProgressBarUI();
    }

    private void updateProgressBarUI() {
        UIManager.put("ProgressBarUI", MarioProgressBarUI.class.getName());
        UIManager.getDefaults().put(MarioProgressBarUI.class.getName(), MarioProgressBarUI.class);
    }
}
