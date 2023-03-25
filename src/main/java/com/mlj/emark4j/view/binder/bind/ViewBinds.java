package com.mlj.emark4j.view.binder.bind;

import com.mlj.emark4j.annotation.EBindPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.view.EButton;

import javax.swing.*;

/**
 * 2023/01/15：
 *
 * @author MLJ
 */
public class ViewBinds {

    @EBindPath(paths = Constants.Path.Header.HEADER0)
    public EButton header0ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[0];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER1)
    public EButton header1ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[1];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER2)
    public EButton header2ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[2];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER3)
    public EButton header3ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[3];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER4)
    public EButton header4ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[4];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER5)
    public EButton header5ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[5];
    }

    @EBindPath(paths = Constants.Path.Header.HEADER6)
    public EButton header6ButtonBind() {
        return ActionFactory.getInstance().getView().getHeaderButtons()[6];
    }


    @EBindPath(paths = Constants.Path.Editor.DOC_MODE)
    public EButton docModeToggleButtonBind() {
        return ActionFactory.getInstance().getView().getDocModeToggleButton();
    }

    @EBindPath(paths = Constants.Path.Editor.EDIT_MODE)
    public EButton readonlyModeToggleButtonBind() {
        return ActionFactory.getInstance().getView().getReadonlyModeToggleButton();
    }

    @EBindPath(paths = Constants.Path.File.OPEN)
    public EButton openFileButtonBind() {
        return ActionFactory.getInstance().getView().getOpenFileButton();
    }

    @EBindPath(paths = Constants.Path.File.SAVE)
    public EButton saveFileButtonBind() {
        return ActionFactory.getInstance().getView().getSaveFileButton();
    }

    @EBindPath(
            paths = {
                    Constants.Path.Header.HEADER0,
                    Constants.Path.Header.HEADER1,
                    Constants.Path.Header.HEADER2,
                    Constants.Path.Header.HEADER3,
                    Constants.Path.Header.HEADER4,
                    Constants.Path.Header.HEADER5,
                    Constants.Path.Header.HEADER6,
                    Constants.Path.Editor.DOC_MODE,
                    Constants.Path.Editor.EDIT_MODE,
                    Constants.Path.File.OPEN,
                    Constants.Path.File.SAVE,
            }
    )
    public JTextPane textPaneBind() {
        return ActionFactory.getInstance().getView().getTextPane();
    }
}
