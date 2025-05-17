package com.mlj.open.emark4j.core.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mlj.open.emark4j.core.Emark4JWare;
import com.mlj.open.emark4j.core.annotation.action.*;
import com.mlj.open.emark4j.core.annotation.builder.EBuilderRegisterPackage;
import com.mlj.open.emark4j.core.annotation.builder.EBuilderRegisterPath;
import com.mlj.open.emark4j.core.bean.Emark4JConfig;
import com.mlj.open.emark4j.core.module.action.ActionManager;
import com.mlj.open.emark4j.core.module.action.SimpleActionManager;
import com.mlj.open.emark4j.core.module.style.StyleManager;
import com.mlj.open.emark4j.core.util.ClassUtil;

import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */

public class ActionFactory implements Emark4JWare {
    private static final ActionFactory DEFAULT_ACTION_FACTORY;


    private static Emark4JConfig config;

    private static Emark4JWare emark4JWare;

    private static DocumentManager documentManager;

    private final com.mlj.open.emark4j.core.module.ActionManager actionManager;

    private final StyleManager styleManager;


    private final ActionManager actionCenter;

    private final ElementManager elementManager;

    static {
        DEFAULT_ACTION_FACTORY = new ActionFactory();
    }

    public static ActionFactory getInstance() {
        return DEFAULT_ACTION_FACTORY;
    }

    private ActionFactory() {
        actionManager = com.mlj.open.emark4j.core.module.ActionManager.getInstance();
        styleManager = StyleManager.getInstance();
        actionCenter = SimpleActionManager.getInstance();
        elementManager = ElementManager.getInstance();
    }

    public void init(Emark4JConfig config, DocumentManager documentManager) {
        if (null != ActionFactory.config && null != ActionFactory.documentManager) {
            throw new RuntimeException("the factory has been init!");
        } else {
            doInit(config, documentManager);
        }
    }

    private void doInit(Emark4JConfig config, DocumentManager documentManager) {
        ActionFactory.config = Objects.requireNonNull(config);
        ActionFactory.documentManager = documentManager;
    }

    public void initBase(Class<?> primarySource, String... args) {
        initBuilderRegister(primarySource, args);
        initActionD(primarySource, args);
        initActionV(primarySource, args);
        initActionVR(primarySource, args);
    }

    private void initActionD(Class<?> primarySource, String[] args) {
        EActionCorePackage declaredAnnotation = primarySource.getDeclaredAnnotation(EActionCorePackage.class);
        String[] packages = declaredAnnotation.packages();

        Map<String, Method> documentActionMap = actionCenter.getDocumentActionMap();
        Map<String, Object> documentActionObjMap = actionCenter.getDocumentActionObjMap();
        ClassUtil.scanPackagesMethod(packages, EActionCore.class, scanResult -> {
            String[] paths = ((EActionCore) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {
                documentActionMap.put(paths[0], scanResult.getTargetMethod());
                documentActionObjMap.put(paths[0], scanResult.getTargetInstance());
            }
        });
    }

    private void initActionV(Class<?> primarySource, String[] args) {
        EActionPackage declaredAnnotation = primarySource.getDeclaredAnnotation(EActionPackage.class);
        String[] packages = declaredAnnotation.packages();

        Map<String, Method> viewActionMap = actionCenter.getViewActionMap();
        Map<String, Object> viewActionObjMap = actionCenter.getViewActionObjMap();
        ClassUtil.scanPackagesMethod(packages, EAction.class, scanResult -> {
            String[] paths = ((EAction) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {
                viewActionMap.put(paths[0], scanResult.getTargetMethod());
                viewActionObjMap.put(paths[0], scanResult.getTargetInstance());
            }
        });
    }

    private void initActionVR(Class<?> primarySource, String[] args) {
        EActionRegisterPackage declaredAnnotation = primarySource.getDeclaredAnnotation(EActionRegisterPackage.class);
        String[] packages = declaredAnnotation.packages();

        Map<String, ActionListener> actionListenerMap = actionManager.getActionListenerMap();
        ClassUtil.scanPackagesMethodResponse(packages, EActionRegisterPath.class, scanResult -> {
            String[] paths = ((EActionRegisterPath) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {
                actionListenerMap.put(paths[0], (ActionListener) scanResult.getTargetMethodResponse());
            }
        });
    }

    private void initBuilderRegister(Class<?> primarySource, String... args) {
        EBuilderRegisterPackage declaredAnnotation = primarySource.getDeclaredAnnotation(EBuilderRegisterPackage.class);
        String[] packages = declaredAnnotation.packages();

        ClassUtil.scanPackagesMethodResponse(packages, EBuilderRegisterPath.class, scanResult -> {
            String[] paths = ((EBuilderRegisterPath) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {
                actionManager.getBuilderMap().put(paths[0], (EBuilder<?>) scanResult.getTargetMethodResponse());
            }
        });
    }

    public void builder() {
        JsonArray jsonArray = config.getJsonObject().getAsJsonArray("styles");
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            EBuilder<?> eBuilder = actionManager.getBuilderMap().get(jsonObject.get("id").getAsString());
            if (null != eBuilder) {
                eBuilder.builder(jsonObject);
            }
        }
    }

    public static <T> T build(JsonObject jsonObject, Class<T> tClass, String pattern) {
        EBuilder<?> eBuilder = ActionFactory.getInstance().actionManager.getBuilderMap().get(pattern);
        Object obj = eBuilder.builder(jsonObject);
        if (obj.getClass() == tClass) {
            return tClass.cast(obj);
        }
        return null;
    }

    // tab
    private void registerSyncDocumentListener() {
//        DocumentManagerV.getMarkDocument().addDocumentListener(new SyncDocumentListeners.EmarkDocumentListener());

//        DocumentManagerV.getSourceDocument().addDocumentListener(new SyncDocumentListeners.SourceDocumentListener());
    }

    public void addActionMapper(ActionMapper actionMapper) {
        actionManager.getMapperMap().put(actionMapper.getName(), actionMapper);
    }

    public void addStyleMapper(StyleManager.StyleMapper styleMapper) {
        styleManager.getMapperMap().put(styleMapper.getName(), styleMapper);
    }

    public DocumentManager getDocumentManager() {
        return DocumentManager.getNowDocumentManager();
    }

    public com.mlj.open.emark4j.core.module.ActionManager getActionManager() {
        return actionManager;
    }

    public StyleManager getStyleManager() {
        return styleManager;
    }

    public static void setEmark4JWare(Emark4JWare emark4JWare) {
        ActionFactory.emark4JWare = emark4JWare;
    }

    public Emark4JWare getEmark4JWare() {
        return emark4JWare;
    }

    @Override
    public void openDocument(URI uri) {
        documentManager.openDocument(uri);
        emark4JWare.openDocument(uri);

        registerSyncDocumentListener();
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        documentManager.updateDocument(lineNum, line);
        emark4JWare.updateDocument(lineNum, line);
    }

    @Override
    public String readDocument(long lineNum) {
        return null;
    }

    @Override
    public void switchDocument(URI uri) {
        documentManager.switchDocument(uri);
        emark4JWare.switchDocument(uri);
    }

    @Override
    public void renameDocumentTo(String newName) {
        documentManager.renameDocumentTo(newName);
        emark4JWare.renameDocumentTo(newName);
    }

    public ActionManager getActionCenter() {
        return actionCenter;
    }

    public ElementManager getElementManager() {
        return elementManager;
    }
}
