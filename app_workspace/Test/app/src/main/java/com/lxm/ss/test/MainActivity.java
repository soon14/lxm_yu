package com.lxm.ss.test;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.webkit.MimeTypeMap;

import com.lxm.ss.test.camera.CameraMainActivity;
import com.lxm.ss.test.executors.TaskWithResult;
import com.lxm.ss.test.opengl.OpenGLTestActivity;
import com.lxm.ss.test.opengl.opengl.OpenGLTest2Activity;
import com.lxm.ss.test.retrofif.RetrofitHttp;
import com.lxm.ss.test.test.Test01Activity;
import com.lxm.ss.test.test.Test03Activity;
import com.lxm.ss.test.test.Test04Activity;
import com.lxm.ss.test.util.FileUtils;
import com.lxm.ss.test.util.LauncherBadgeHelper;
import com.lxm.ss.test.util.Zlog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        downFile();
//        test();
//        test01();

        initView();
    }

    private void initView() {

        findViewById(R.id.button).setOnClickListener(mOnClickListener);
        findViewById(R.id.button2).setOnClickListener(mOnClickListener);
        findViewById(R.id.button3).setOnClickListener(mOnClickListener);
        findViewById(R.id.button4).setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:{

//                    new Thread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                        @Override
//                        public void uncaughtException(Thread t, Throwable e) {
//
//                        }
//                    });

//                    Intent intent = new Intent();
//                    intent.setComponent(new ComponentName("com.lxm.ss.test","com.lxm.ss.test.test.Test01Activity"));
//                    startActivity(intent);

//                    executorsTest();

                    Intent intent = new Intent(MainActivity.this, CameraMainActivity.class);
                    startActivity(intent);
                }break;
                case R.id.button2:{
                    startActivity(new Intent(MainActivity.this,OpenGLTestActivity.class));
                }break;
                case R.id.button3:{
//                    changeIconNewMessage(50);

//                    RetrofitHttp retrofitHttp = new RetrofitHttp();
//                    retrofitHttp.getCategories();
//
//                   String androidId =  Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//
//                    retrofitHttp.updateVersion(androidId);

//                    rxjava();

//                    testMime();

                  //搜索服务

                    searchService();
                }break;
                case R.id.button4:{
//
//                    Intent[] intents = new Intent[]{new Intent(CameraMainActivity.this,Test03Activity.class),new Intent(CameraMainActivity.this,Test04Activity.class)};
//
//                    startActivities(intents);

//                    rxjava();

                    startActivity(new Intent(MainActivity.this,OpenGLTest2Activity.class));
                }break;
                default:
                    break;
            }
        }
    };


    private void  rxjava() {
//        final int drawableRes = R.drawable.bubble_arrow_up;
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava1 OnSubscribe Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava1 OnSubscribe Thread:Not Main Thread");
                }
//                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext("sdsds");
                subscriber.onCompleted();

                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava1 Subscriber Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava1 Subscriber Thread:Not Main Thread");
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String drawable) {
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                            Zlog.ii("lxm rxjava1 Observer Thread:Main Thread");
                        } else {
                            Zlog.ii("lxm rxjava1 Observer Thread:Not Main Thread");
                        }
                    }

                    @Override
                    public void onCompleted() {
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                            Zlog.ii("lxm rxjava1 Observer Thread:Main Thread");
                        } else {
                            Zlog.ii("lxm rxjava1 Observer Thread:Not Main Thread");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava2 Observable Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava2 Observable Thread:Not Main Thread");
                }
                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        })

        .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
        .subscribe( new Observer<String>() {

            @Override
            public void onCompleted() {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Not Main Thread");
                }
                Zlog.ii("lxm rxjava Subscriber:onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Not Main Thread");
                }
                Zlog.ii("lxm rxjava Subscriber:onError");
            }

            @Override
            public void onNext(String s) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Main Thread");
                } else {
                    Zlog.ii("lxm rxjava2 Subscriber Thread:Not Main Thread");
                }
                Zlog.ii("lxm rxjava Subscriber:onNext  " + s);
            }
        });


    }
    private void executorsTest() {

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService1 = Executors.newFixedThreadPool(5);
//        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
//        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(5);
//        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

//                    startActivity(new Intent(CameraMainActivity.this,Test01Activity.class));


        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        // 创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            // 将任务执行结果存储到List中
            resultList.add(future);
        }
//        executorService.shutdown();

        // 遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println( "lxm executorsTest  "+fs.get()); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
                return;
            }
        }
    }

    private void searchService() {

        SearchManager searchManager = (SearchManager)getSystemService(SEARCH_SERVICE);


        Bundle bundle=new Bundle();
        bundle.putString("data", "");

        //打开浮动搜索框（第一个参数默认添加到搜索框的值）
        //bundle为传递的数据
        searchManager.startSearch("mm", false, new ComponentName("com.lxm.ss.test","com.lxm.ss.test.test.Test01Activity"),bundle, false);
        searchManager.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                System.out.println("lxm searchService setOnCancelListener :");
            }
        });
        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                System.out.println("lxm searchService setOnDismissListener :");
            }
        });

    }

    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {

        return super.onSearchRequested(searchEvent);
    }

    private void testMime() {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();



        //MimeTypeMap中是否有txt的MimeType

        System.out.println(mimeTypeMap.hasExtension("txt"));
        System.out.println(mimeTypeMap.hasMimeType("text/html"));

        //获得txt文件类型的MimeType

//        String extension = mimeTypeMap.getMimeTypeFromExtension("css");


        String fileExtension = MimeTypeMap.getFileExtensionFromUrl("http://pre.fromfactory.club/auth2/login?redirect=%2Fcart");
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);

        System.out.println(fileExtension +"   " + mimeType);
    }


    private void changeIconNewMessage(int num) {

        LauncherBadgeHelper.setBadgeCount(MainActivity.this,num);
    }

    private void test() {

        String str = "123qwertyuio12232" ;
        Zlog.ii("lxm test:" + str.indexOf("3"));
        Zlog.ii("lxm test:" + str.lastIndexOf("3"));
        Zlog.ii("lxm test:" + str.compareTo("q"));
        Zlog.ii("lxm test:" + str.compareToIgnoreCase("q"));
        Zlog.ii("lxm test:" + str.valueOf(true));
        Zlog.ii("lxm test:" + str.replace("12","45"));
        Zlog.ii("lxm test:" + str.replaceFirst("12","45"));
        Zlog.ii("lxm test:" + str.replaceAll("12","45"));
        Zlog.ii("lxm test:" + str.substring(str.indexOf("3")));

    }

    private void test01() {

        recordTime(MainActivity.class,"test");

        Comparator comparator = new Student();

    }

    /**
     * 记录操作执行总时间.
     *
     * @param <T> the generic type
     * @param clazz the clazz
     * @param methodName the method name
     */
    public <T> void recordTime(Class<T> clazz, String methodName) {
        long start = System.currentTimeMillis();
        Zlog.ii("start: " + start);

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            String name = method.getName();
            if (name.equals(methodName)) {
                try {
                    method.invoke(clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        Zlog.ii("end: " + end);
        Zlog.ii("duration: " + (end - start) + " ms");
    }


    private void downFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fo = null ;
                try {
                    URL url = new URL("");
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setReadTimeout(30000);//设置客户端连接超时间隔，

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        InputStream is = conn.getInputStream();

                        File file = new File("");
                        if (!file.exists() || !file.isDirectory()) {
                            file.mkdir();
                        }
                        fo = new FileOutputStream(file);

                        int len = -1 ;
                        byte[] b = new byte[1024];
                        while ((len = is.read(b)) != -1 ) {
                            fo.write(b,0,len);
                        }
                        fo.flush();

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    if (fo != null) {
                        try {
                            fo.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }).start();

    }

//    /**
//     * Button点击事件
//     */
//    public void install()
//    {
//        String path = "";
//        if (FileUtils.isSdcardReady()) {
//            path = FileUtils.getSdcardPath();
//        } else {
//            path = FileUtils.getCachePath(this);
//        }
//        String fileName = path + "/AidlServerDemo.apk";
//        File file = new File(fileName);
//
//        try {
//            if(!file.exists())
//                copyAPK2SD(fileName);
//            Uri uri = Uri.fromFile(new File(fileName));
//            Class<?> clazz = Class.forName("android.os.ServiceManager");
//            Method method = clazz.getMethod("getService", String.class);
//            IBinder iBinder = (IBinder) method.invoke(null, "package");
//            IPackageManager ipm = IPackageManager.Stub.asInterface(iBinder);
//            @SuppressWarnings("deprecation")
//            VerificationParams verificationParams = new VerificationParams(null, null, null, VerificationParams.NO_UID, null);
//
//            ipm.installPackage(fileName, new PackageInstallObserver(), 2, null, verificationParams, "");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//
//
//    // 用于显示结果
//    class PackageInstallObserver extends IPackageInstallObserver2.Stub {
//
//        @Override
//        public void onUserActionRequired(Intent intent) throws RemoteException {
//            // TODO Auto-generated method stub
//
//        }
//
//        @Override
//        public void onPackageInstalled(String basePackageName, int returnCode, String msg, Bundle extras) throws RemoteException {
//            // TODO Auto-generated method stub
//
//        }
//    };


    /**
     * 拷贝assets文件夹的APK插件到SD
     *
     * @param strOutFileName
     * @throws IOException
     */
    private void copyAPK2SD(String strOutFileName) throws IOException {
        FileUtils.createDipPath(strOutFileName);
        InputStream myInput = this.getAssets().open("AidlServerDemo.apk");
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();

    }
}
