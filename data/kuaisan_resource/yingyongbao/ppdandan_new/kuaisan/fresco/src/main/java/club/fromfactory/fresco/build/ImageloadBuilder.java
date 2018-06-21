package club.fromfactory.fresco.build;

import android.graphics.Point;

import com.facebook.imagepipeline.request.Postprocessor;

import club.fromfactory.fresco.view.FrescoImageView;

/**
 * Created by lxm on 2017/7/5.
 */

public abstract class ImageloadBuilder<T extends ImageloadBuilder> {

    protected FrescoImageView imageView;
    protected String url;
    protected int defaultImg = -1;
    protected int cornerRadius;
    protected boolean isCircle;
    protected boolean loadLocalPath;
    protected boolean isAnima;
    protected Point size;
    protected Postprocessor postprocessor ;

    public T setImageView(FrescoImageView imageView) {
        this.imageView = imageView;

        return (T)this;
    }

    public T setUrl(String url) {
        this.url = url;
        return (T)this;
    }

    public T setDefaultImg(int defaultImg) {
        this.defaultImg = defaultImg;
        return (T)this;
    }

    public T setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        return (T)this;
    }

    public T setCircle(boolean circle) {
        isCircle = circle;

        return (T)this;
    }

    public T setLoadLocalPath(boolean loadLocalPath) {
        this.loadLocalPath = loadLocalPath;

        return (T)this;
    }

    public T setAnima(boolean anima) {
        isAnima = anima;

        return (T)this;
    }

    public T setSize(Point size) {
        this.size = size;

        return (T)this;
    }

    public T setPostprocessor(Postprocessor postprocessor) {
        this.postprocessor = postprocessor;

        return (T)this;
    }

    public abstract void load();
}
