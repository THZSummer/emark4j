package com.mlj.open.emark4j.core.module;

import com.mlj.open.emark4j.core.Emark4JWare;
import com.mlj.open.emark4j.core.util.FileUtil;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class DocumentManager implements Emark4JWare {
    private static final Map<String, DocumentManager> documentManagerMap = new HashMap<>();
    private static DocumentManager nowDocumentManager;

    private String name;
    private final Map<Long, String> documentLines;
    private URI documentURI;

    private Long selectStart;
    private Long selectEnd;


    private DocumentManager(URI uri) {
        this.name = uri.getPath();
        this.documentLines = new HashMap<>();
        this.documentURI = uri;

        documentManagerMap.put(name, this);
        nowDocumentManager = this;
    }

    public static DocumentManager createNewDocumentManager(URI uri) {
        return new DocumentManager(uri);
    }

    public static DocumentManager createNewDocumentManager() {
        return createNewDocumentManager(getUntitledUri());
    }

    private static URI getUntitledUri() {
        return FileUtil.getUntitledUri(documentManagerMap.size() + 1);
    }

    @Override
    public void openDocument(URI uri) {
        new DocumentManager(uri);
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        getNowDocumentManager().documentLines.put(lineNum, line);
        System.out.println(documentLines.toString());
    }

    @Override
    public String readDocument(long lineNum) {
        return getNowDocumentManager().documentLines.get(lineNum);
    }

    @Override
    public void closeDocument(URI uri) {
        documentManagerMap.remove(uri.getPath());
    }

    @Override
    public void switchDocument(URI uri) {
        nowDocumentManager = documentManagerMap.get(uri.getPath());
    }

    @Override
    public void saveDocument(URI uri) {

    }

    @Override
    public void renameDocumentTo(String newName) {
        String old = getName();
        documentManagerMap.put(newName, documentManagerMap.get(old));
        documentManagerMap.remove(old);
    }

    public static DocumentManager getNowDocumentManager() {
        System.out.println(nowDocumentManager.documentURI.getPath());
        return nowDocumentManager;
    }

    public static String getName() {
        return getNowDocumentManager().name;
    }


    public static URI getDocumentURI() {
        return getNowDocumentManager().documentURI;
    }

    public static void setDocumentURI(URI documentURI) {
        getNowDocumentManager().documentURI = documentURI;
    }

    public Long getSelectStart() {
        return selectStart;
    }

    public void setSelectStart(Long selectStart) {
        getNowDocumentManager().selectStart = selectStart;
    }

    public Long getSelectEnd() {
        return selectEnd;
    }

    public void setSelectEnd(Long selectEnd) {
        getNowDocumentManager().selectEnd = selectEnd;
    }

    public long getSelectLineNumStart() {
        long sum = 0;
        Long start = getNowDocumentManager().getSelectStart();
        Map<Long, String> lines = getNowDocumentManager().documentLines;
        for (long i = 1; i <= lines.size(); i++) {
            if (start <= (sum += lines.get(i).length())) {
                return i;
            }
        }
        return -1L;
    }

    public long getSelectLineNumEnd() {
        long sum = 0;
        Long end = getNowDocumentManager().selectEnd;
        Map<Long, String> lines = getNowDocumentManager().documentLines;
        for (long i = 1; i <= lines.size(); i++) {
            if (end <= (sum += lines.get(i).length())) {
                return i;
            }
        }
        return -1L;
    }
}
