package com.zh.loveme;

import android.app.Application;

/**
 * @Description: 类说明，描述
 * @Author: zh浩
 * @CreateDate: App$ 10:24$
 * @Version: 1.0
 */
public class App extends Application {
    static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public App getmInstance() {
        return mInstance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
