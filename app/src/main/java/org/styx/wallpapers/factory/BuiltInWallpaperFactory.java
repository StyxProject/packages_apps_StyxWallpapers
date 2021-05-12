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
package com.styx.wallpapers.factory;

import android.app.WallpaperManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.styx.wallpapers.R;
import com.styx.wallpapers.bundle.WallpaperBundle;
import com.styx.wallpapers.bundle.WallpaperType;

public final class BuiltInWallpaperFactory {

    private BuiltInWallpaperFactory() {
    }

    public static WallpaperBundle build(@NonNull final String name,
                                        @NonNull final Resources res,
                                        @DrawableRes final int drawableRes) {
        Drawable drawable = res.getDrawable(drawableRes, res.newTheme());
        return new WallpaperBundle(name, drawable, drawableRes, WallpaperType.BUILT_IN);
    }
}
