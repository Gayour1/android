package com.lody.virtual.client.ipc;

import android.content.ClipData;
import android.os.RemoteException;
import android.util.Log;

import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.helper.ipcbus.IPCSingleton;
import com.lody.virtual.server.interfaces.IAppPermissionCallback;
import com.lody.virtual.server.interfaces.IAppPermissionManager;

/**
 * Created by geyao on 2018/1/22.
 */

public class VAppPermissionManager {
    private static final String TAG = VAppPermissionManager.class.getSimpleName();
    /**
     * 禁止将数据向无防护应用复制
     */
    public static final String PROHIBIT_UNPROTECTED_DATA_COPY = "禁止将数据向无防护应用复制";
    /**
     * 禁止对此应用进行截屏or录屏
     */
    public static final String PROHIBIT_SCREEN_SHORT_RECORDER = "禁止对此应用进行截屏,录屏";
    /**
     * 禁止使用网络
     */
    public static final String PROHIBIT_NETWORK = "禁止使用网络";
    /**
     * 启用应用界面水印功能
     */
    public static final String ALLOW_WATER_MARK = "启用水印功能";

    /**
     * 目前支持的权限集合
     */
    public static final String[] permissions = new String[]{
            PROHIBIT_UNPROTECTED_DATA_COPY,//禁止将数据向无防护应用复制
            PROHIBIT_SCREEN_SHORT_RECORDER,//禁止对此应用进行截屏or录屏
            PROHIBIT_NETWORK,//禁止使用网络
            ALLOW_WATER_MARK//启用应用界面水印功能
    };
    private static final VAppPermissionManager sInstance = new VAppPermissionManager();
    private IPCSingleton<IAppPermissionManager> singleton = new IPCSingleton<>(IAppPermissionManager.class);

    public static VAppPermissionManager get() {
        return sInstance;
    }

    public IAppPermissionManager getService() {
        return singleton.get();
    }

    /**
     * 是否是支持的权限
     *
     * @param permissionName 权限名称
     * @return 是否是支持的权限
     */
    public boolean isSupportPermission(String permissionName) {
        Log.d(TAG, "isSupportPermission permissionName: " + permissionName);
        try {
            return getService().isSupportPermission(permissionName);
        } catch (RemoteException e) {
            e.printStackTrace();
            return VirtualRuntime.crash(e);
        }
    }

    /**
     * 清除权限信息
     */
    public void clearPermissionData() {
        Log.d(TAG, "clearPermissionData");
        try {
            getService().clearPermissionData();
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 设置应用权限
     *
     * @param packageName       应用包名
     * @param appPermissionName 应用权限名称
     * @param isPermissionOpen  权限开关
     */
    public void setAppPermission(String packageName, String appPermissionName, boolean isPermissionOpen) {
        Log.d(TAG, "setAppPermission packageName: " + packageName + " appPermissionName: " + appPermissionName + " isPermissionOpen: " + isPermissionOpen);
        try {
            getService().setAppPermission(packageName, appPermissionName, isPermissionOpen);
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 获取应用权限开关状态
     *
     * @param packageName       应用包名
     * @param appPermissionName 应用权限名称
     * @return 权限开关状态
     */
    public boolean getAppPermissionEnable(String packageName, String appPermissionName) {
        Log.d(TAG, "getAppPermissionEnable packageName: " + packageName + " appPermissionName: " + appPermissionName);
        try {
            boolean appPermissionEnable = getService().getAppPermissionEnable(packageName, appPermissionName);
            Log.d(TAG, "getAppPermissionEnable result: " + appPermissionEnable);
            return appPermissionEnable;
        } catch (RemoteException e) {
            e.printStackTrace();
            return VirtualRuntime.crash(e);
        }
    }

    /**
     * 注册回调监听
     *
     * @param iAppPermissionCallback 回调监听
     */
    public void registerCallback(IAppPermissionCallback iAppPermissionCallback) {
        Log.d(TAG, "registerCallback");
        try {
            getService().registerCallback(iAppPermissionCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 接触回调监听注册
     */
    public void unregisterCallback() {
        Log.d(TAG, "unregisterCallback");
        try {
            getService().unregisterCallback();
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 权限拦截触发回调
     *
     * @param appPackageName 应用名称
     * @param permissionName 权限名称
     */
    public void interceptorTriggerCallback(String appPackageName, String permissionName) {
        Log.d(TAG, "interceptorTriggerCallback");
        try {
            getService().interceptorTriggerCallback(appPackageName, permissionName);
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 缓存剪切板信息
     *
     * @param clipData 剪切板信息
     */
    public void cacheClipData(ClipData clipData) {
        Log.d(TAG, "cacheClipData");
        try {
            getService().cacheClipData(clipData);
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 获取缓存的剪切板信息
     */
    public ClipData getClipData() {
        Log.d(TAG, "getClipData");
        try {
            return getService().getClipData();
        } catch (RemoteException e) {
            e.printStackTrace();
            return VirtualRuntime.crash(e);
        }
    }
}
