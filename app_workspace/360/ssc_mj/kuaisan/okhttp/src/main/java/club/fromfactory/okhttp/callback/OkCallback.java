package club.fromfactory.okhttp.callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lxm on 2017/6/20.
 */

public abstract class OkCallback<T> {


    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);


    public static OkCallback CALLBACK_DEFAULT = new OkCallback() {
        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            return null;
        }

        @Override
        public void onError(Call call, Exception e) {

        }

        @Override
        public void onResponse(Object response) {

        }
    };
}
