package club.fromfactory.fresco.build;

import android.graphics.Point;

import com.facebook.imagepipeline.request.Postprocessor;

import club.fromfactory.fresco.view.FrescoImageView;

/**
 * Created by lxm on 2017/7/5.
 */

public class FrescoImageloadBuilder extends ImageloadBuilder {

    @Override
    public void load() {
        if (imageView == null) return;
        init(imageView, cornerRadius, isCircle, isAnima, size, postprocessor);
        if (loadLocalPath) {
            imageView.loadLocalImage(url, defaultImg);
        } else {
            imageView.loadView(url, defaultImg);
        }

    }

    private void init(FrescoImageView imageView, int cornerRadius, boolean isCircle, boolean isAnima,
                             Point size, Postprocessor postprocessor) {
        imageView.setAnim(isAnima);
        imageView.setCornerRadius(cornerRadius);
        imageView.setFadeTime(300);
        if (isCircle)
            imageView.asCircle();
        if (postprocessor != null)
            imageView.setPostProcessor(postprocessor);
        if (size != null) {
            imageView.setResize(size);
        }
    }
}
