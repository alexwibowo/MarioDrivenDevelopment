package com.wibowo.intellij.mdd.events;

import com.wibowo.intellij.mdd.sounds.Sound;
import com.wibowo.intellij.mdd.sounds.Sounds;

import java.util.HashMap;
import java.util.Map;

public class SoundPlayingListener implements VcsEvent.Listener, TestEvent.Listener {
    private final Sounds sounds;
    private final Map<String, Sound> soundsByAction;
    private boolean stopped;

    public SoundPlayingListener(final Sounds sounds) {
        this.sounds = sounds;
        this.soundsByAction = editorSounds(sounds);
    }

    public SoundPlayingListener init() {
        sounds.marioSong.playInBackground();
        return this;
    }

    public void stop(boolean isIdeShutdown) {
        if (stopped) {
            return;
        }
        stopped = true;
        sounds.marioSong.stop();
        sounds.zeldaSong.stop();
        if (isIdeShutdown) {
            sounds.gameover.play();
        }
    }

    @Override
    public void onVcsCommit() {
        sounds.powerupAppears.play();
    }

    @Override
    public void onVcsUpdate() {
        sounds.powerup.play();
    }

    @Override
    public void onVcsPush() {
        sounds.powerup.play();
    }

    @Override
    public void onVcsPushFailed() {
        sounds.oneDown.play();
    }

    @Override
    public void onUnitTestSucceeded() {
        sounds.oneUp.play();
    }

    @Override
    public void onUnitTestFailed() {
        sounds.mammaMia.play();
    }

    public void onProjectOpened() {
        sounds.marioDreaming.play();
    }


    private static Map<String, Sound> editorSounds(Sounds sounds) {
        Map<String, Sound> result = new HashMap<String, Sound>();

        result.put("EditorUp", sounds.kick);
        result.put("EditorDown", sounds.kick);
        result.put("EditorUpWithSelection", sounds.kick);
        result.put("EditorDownWithSelection", sounds.kick);
        result.put("EditorPreviousWord", sounds.kick);
        result.put("EditorNextWord", sounds.kick);
        result.put("EditorPreviousWordWithSelection", sounds.kick);
        result.put("EditorNextWordWithSelection", sounds.kick);
        result.put("EditorSelectWord", sounds.kick);
        result.put("EditorUnSelectWord", sounds.kick);
        result.put("$SelectAll", sounds.kick);
        result.put("EditorLineStart", sounds.jumpSmall);
        result.put("EditorLineEnd", sounds.jumpSmall);
        result.put("EditorLineStartWithSelection", sounds.jumpSmall);
        result.put("EditorLineEndWithSelection", sounds.jumpSmall);
        result.put("EditorPageUp", sounds.jumpSuper);
        result.put("EditorPageDown", sounds.jumpSuper);
        result.put("GotoPreviousError", sounds.jumpSuper);
        result.put("GotoNextError", sounds.jumpSuper);
        result.put("FindNext", sounds.jumpSuper);
        result.put("FindPrevious", sounds.jumpSuper);
        result.put("MethodUp", sounds.jumpSuper);
        result.put("MethodDown", sounds.jumpSuper);
        result.put("Back", sounds.jumpSuper);
        result.put("Forward", sounds.jumpSuper);
        result.put("GotoSuperMethod", sounds.jumpSuper);
        result.put("GotoDeclaration", sounds.jumpSuper);
        result.put("GotoImplementation", sounds.jumpSuper);
        result.put("EditSource", sounds.jumpSuper); // this is F4 navigate action

        result.put("EditorPaste", sounds.fireball);
        result.put("ReformatCode", sounds.fireball);
        result.put("EditorToggleCase", sounds.fireball);
        result.put("ExpandLiveTemplateByTab", sounds.fireball);
        result.put("EditorCompleteStatement", sounds.fireball);
        result.put("EditorChooseLookupItem", sounds.fireball);
        result.put("EditorChooseLookupItemReplace", sounds.fireball);
        result.put("HippieCompletion", sounds.fireball);
        result.put("HippieBackwardCompletion", sounds.fireball);
        result.put("MoveStatementUp", sounds.fireball);
        result.put("MoveStatementDown", sounds.fireball);
        result.put("EditorStartNewLineBefore", sounds.fireball);
        result.put("EditorStartNewLine", sounds.fireball);
        result.put("EditorDuplicate", sounds.fireball);
        result.put("EditorBackSpace", sounds.breakblock);
        result.put("EditorJoinLines", sounds.breakblock);
        result.put("EditorDelete", sounds.breakblock);
        result.put("EditorDeleteLine", sounds.breakblock);
        result.put("EditorDeleteToWordStart", sounds.breakblock);
        result.put("EditorDeleteToWordEnd", sounds.breakblock);
        result.put("CommentByLineComment", sounds.breakblock);
        result.put("CommentByBlockComment", sounds.breakblock);
        result.put("ToggleBookmark", sounds.stomp);
        result.put("ToggleBookmarkWithMnemonic", sounds.stomp);
        result.put("ToggleLineBreakpoint", sounds.stomp);
        result.put("HighlightUsagesInFile", sounds.stomp);

        result.put("NextTab", sounds.jumpSuper);
        result.put("PreviousTab", sounds.jumpSuper);
        result.put("CloseEditor", sounds.fireworks);
        result.put("CloseAllEditorsButActive", sounds.fireworks);
        result.put("$Undo", sounds.fireworks);
        result.put("$Redo", sounds.fireworks);
        result.put("ExpandAllRegions", sounds.stomp);
        result.put("CollapseAllRegions", sounds.stomp);
        result.put("ExpandRegion", sounds.stomp);
        result.put("CollapseRegion", sounds.stomp);
        result.put("CollapseSelection", sounds.stomp);
        result.put("PasteMultiple", sounds.stomp);
        result.put("FileStructurePopup", sounds.stomp);
        result.put("ShowBookmarks", sounds.stomp);
        result.put("ViewBreakpoints", sounds.stomp);
        result.put("QuickJavaDoc", sounds.stomp);
        result.put("ParameterInfo", sounds.stomp);
        result.put("ShowIntentionActions", sounds.stomp);
        result.put("EditorToggleColumnMode", sounds.stomp);
        result.put("SurroundWith", sounds.stomp);
        result.put("InsertLiveTemplate", sounds.stomp);
        result.put("SurroundWithLiveTemplate", sounds.stomp);
        result.put("NewElement", sounds.stomp);
        result.put("Generate", sounds.stomp);
        result.put("OverrideMethods", sounds.stomp);
        result.put("ImplementMethods", sounds.stomp);

        result.put("ChangeSignature", sounds.stomp);
        result.put("ExtractMethod", sounds.stomp);
        result.put("Inline", sounds.stomp);
        result.put("Move", sounds.stomp);

        result.put("Find", sounds.stomp);
        result.put("FindInPath", sounds.stomp);
        result.put("Replace", sounds.stomp);
        result.put("ReplaceInPath", sounds.stomp);

        result.put("ChangesView.Diff", sounds.stomp);
        result.put("CompareClipboardWithSelection", sounds.stomp);

        result.put("Switcher", sounds.stomp);
        result.put("RecentFiles", sounds.stomp);
        result.put("GotoClass", sounds.stomp);
        result.put("GotoFile", sounds.stomp);
        result.put("GotoSymbol", sounds.stomp);
        result.put("SearchEverywhere", sounds.stomp);
        result.put("GotoLine", sounds.stomp);
        result.put("ShowUsages", sounds.stomp);
        result.put("FindUsages", sounds.stomp);
        result.put("ShowNavBar", sounds.stomp);
        result.put("RunInspection", sounds.stomp);

        result.put("SelectIn", sounds.stomp);
        result.put("QuickChangeScheme", sounds.stomp);
        result.put("ActivateProjectToolWindow", sounds.stomp);
        result.put("ActivateStructureToolWindow", sounds.stomp);
        result.put("ActivateFindToolWindow", sounds.stomp);
        result.put("ActivateChangesToolWindow", sounds.stomp);
        result.put("ActivateRunToolWindow", sounds.stomp);
        result.put("ActivateDebugToolWindow", sounds.stomp);
        result.put("ActivateMessagesToolWindow", sounds.stomp);
        result.put("ActivateFavoritesToolWindow", sounds.stomp);
        result.put("AddToFavoritesPopup", sounds.stomp);
        result.put("TypeHierarchy", sounds.stomp);
        result.put("HideActiveWindow", sounds.stomp);
        result.put("Vcs.QuickListPopupAction", sounds.stomp);
        result.put("Vcs.ShowMessageHistory", sounds.stomp);

        result.put("ChooseRunConfiguration", sounds.stomp);
        result.put("ChooseDebugConfiguration", sounds.stomp);

        return result;
    }
}
