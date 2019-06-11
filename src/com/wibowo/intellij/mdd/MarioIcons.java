package com.wibowo.intellij.mdd;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface MarioIcons {

    Icon MARIO_STANDING_FROM_LEFT =  IconLoader.getIcon("/mario_standing_from_left_transparent_small.png");
    Icon MARIO_RUNNING_FROM_LEFT =  IconLoader.getIcon("/mario_running_from_left_transparent_small.png");

    Icon MARIO_STANDING_FROM_RIGHT =  IconLoader.getIcon("/mario_standing_from_right_transparent_small.png");
    Icon MARIO_RUNNING_FROM_RIGHT =  IconLoader.getIcon("/mario_running_from_right_transparent_small.png");


    Icon MARIO_ICON = IconLoader.getIcon("/mario_from_left.png");
    Icon RMARIO_ICON = IconLoader.getIcon("/mario_from_right.png");
}
