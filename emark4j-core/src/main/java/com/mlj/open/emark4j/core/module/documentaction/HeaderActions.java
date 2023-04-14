package com.mlj.open.emark4j.core.module.documentaction;

import com.mlj.open.emark4j.core.annotation.EActionCore;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.DocumentManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2023/04/05：
 *
 * @author MLJ
 */
public class HeaderActions {

    // #需要转义：\#
    private static final Pattern HEADER_REPLACEMENT_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_0_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_1_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_2_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_3_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_4_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_5_PATTERN = Pattern.compile("^(#{1,}?)[^#]");
    private static final Pattern HEADER_6_PATTERN = Pattern.compile("^(#{1,}?)[^#]");

    private static final String HEADER_0_PREFIX = "";
    private static final String HEADER_1_PREFIX = "#";
    private static final String HEADER_2_PREFIX = "##";
    private static final String HEADER_3_PREFIX = "###";
    private static final String HEADER_4_PREFIX = "####";
    private static final String HEADER_5_PREFIX = "#####";
    private static final String HEADER_6_PREFIX = "######";


    @EActionCore(paths = Constants.Path.Header.HEADER0)
    public void header0Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_0_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER1)
    public void header1Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_1_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER2)
    public void header2Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_2_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER3)
    public void header3Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_3_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER4)
    public void header4Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_4_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER5)
    public void header5Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_5_PREFIX);
    }

    @EActionCore(paths = Constants.Path.Header.HEADER6)
    public void header6Action() {
        headerActionBase(HEADER_REPLACEMENT_PATTERN, HEADER_6_PREFIX);
    }

    private void headerActionBase(Pattern pattern, String prefix) {
        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        long selectLineNumStart = documentManager.getSelectLineNumStart();
        long selectLineNumEnd = documentManager.getSelectLineNumEnd();

        if (-1 == selectLineNumStart || -1 == selectLineNumEnd) {
            return;
        }

        for (long i = selectLineNumStart; i <= selectLineNumEnd; i++) {
            String s = documentManager.readDocument(i);

            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                documentManager.updateDocument(i, prefix + s.substring(matcher.end()));
            } else {
                documentManager.updateDocument(i, prefix + s);
            }
        }
    }
}
