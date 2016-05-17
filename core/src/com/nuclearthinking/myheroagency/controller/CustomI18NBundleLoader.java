package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 * Created by Izonami on 16.05.2016.
 */
public class CustomI18NBundleLoader extends AsynchronousAssetLoader<I18NBundle, CustomI18NBundleLoader.I18NBundleParameter> {

    private I18NBundleParameter parameter;

    public CustomI18NBundleLoader(FileHandleResolver resolver, I18NBundleParameter parameter) {
        super(resolver);
        this.parameter = parameter;
    }

    I18NBundle bundle;

    @Override
    public void loadAsync (AssetManager manager, String fileName, FileHandle file, I18NBundleParameter parameter) {
        this.bundle = null;
        Locale locale;
        String encoding;
        if (parameter == null) {
            locale = this.parameter.locale;
            encoding = null;
        } else {
            locale = parameter.locale == null ? Locale.getDefault() : parameter.locale;
            encoding = parameter.encoding;
        }
        if (encoding == null) {
            this.bundle = I18NBundle.createBundle(file, locale);
        } else {
            this.bundle = I18NBundle.createBundle(file, locale, encoding);
        }
    }

    @Override
    public I18NBundle loadSync (AssetManager manager, String fileName, FileHandle file, I18NBundleParameter parameter) {
        I18NBundle bundle = this.bundle;
        this.bundle = null;
        return bundle;
    }

    @Override
    public Array<AssetDescriptor> getDependencies (String fileName, FileHandle file, I18NBundleParameter parameter) {
        return null;
    }

    static public class I18NBundleParameter extends AssetLoaderParameters<I18NBundle> {
        public final Locale locale;
        public final String encoding;

        public I18NBundleParameter () {
            this(null, null);
        }

        public I18NBundleParameter (Locale locale) {
            this(locale, null);
        }

        public I18NBundleParameter (Locale locale, String encoding) {
            this.locale = locale;
            this.encoding = encoding;
        }
    }
}
