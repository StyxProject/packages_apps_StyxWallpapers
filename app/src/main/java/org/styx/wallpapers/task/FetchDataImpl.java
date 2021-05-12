/*
 * Copyright (C) 2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.styx.wallpapers.task;

import android.app.WallpaperManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.styx.wallpapers.R;
import com.styx.wallpapers.bundle.WallpaperBundle;
import com.styx.wallpapers.factory.BuiltInWallpaperFactory;
import com.styx.wallpapers.factory.GradientWallpaperFactory;
import com.styx.wallpapers.factory.UserWallpaperFactory;

import java.util.ArrayList;
import java.util.List;

final class FetchDataImpl {
    private final List<WallpaperBundle> mData = new ArrayList<>();
    private final Callback mCallbacks;

    FetchDataImpl(@NonNull final Callback callbacks) {
        mCallbacks = callbacks;
    }

    @NonNull
    List<WallpaperBundle> fetchData() {
        addUser();
        addBuiltIn();
        addGradients();

        return mData;
    }

    private void addUser() {
        mData.add(UserWallpaperFactory.build(mCallbacks.getResources()));
    }

    private void addBuiltIn() {
        Resources res = mCallbacks.getResources();

        // Other built-in
        String[] names = res.getStringArray(R.array.wallpaper_built_in_names);
        TypedArray drawables = res.obtainTypedArray(R.array.wallpaper_built_in_drawables);
        for (int i = 0; i < drawables.length(); i++) {
            final int resourceId = drawables.getResourceId(i, 0);
            if (resourceId != 0) {
                mData.add(BuiltInWallpaperFactory.build(names[i], res, resourceId));
            }
        }

        drawables.recycle();
    }

    private void addGradients() {
        Resources res = mCallbacks.getResources();
        String[] names = res.getStringArray(R.array.wallpaper_gradient_names);
        TypedArray gradients = res.obtainTypedArray(R.array.wallpaper_gradient_drawables);
        for (int i = 0; i < gradients.length(); i++) {
            final int resourceId = gradients.getResourceId(i, 0);
            if (resourceId != 0) {
                mData.add(GradientWallpaperFactory.build(names[i], res, resourceId));
            }
        }

        gradients.recycle();
    }

    public interface Callback {
        @NonNull
        Resources getResources();

        @NonNull
        WallpaperManager getWallpaperManager();
    }
}
