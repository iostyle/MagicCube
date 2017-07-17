package iostyle.com.magiccube.Activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import iostyle.com.magiccube.Bean.Pixel;
import iostyle.com.magiccube.BuildConfig;
import iostyle.com.magiccube.R;

/**
 * 上黄下白，前蓝后绿，左橙右红。
 * #FFFF00       #FFFFFF       #00469F        #009F41        #FE8A0A      #D50010
 * 255 255 0    255 255 255   0 70 159       0 159 65       254 138 10   213 0 16
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.uIv)
    ImageView uIv;
    @BindView(R.id.uLay)
    FrameLayout uLay;
    @BindView(R.id.lIv)
    ImageView lIv;
    @BindView(R.id.lLay)
    FrameLayout lLay;
    @BindView(R.id.fIv)
    ImageView fIv;
    @BindView(R.id.fLay)
    FrameLayout fLay;
    @BindView(R.id.rIv)
    ImageView rIv;
    @BindView(R.id.rLay)
    FrameLayout rLay;
    @BindView(R.id.bIv)
    ImageView bIv;
    @BindView(R.id.bLay)
    FrameLayout bLay;
    @BindView(R.id.dIv)
    ImageView dIv;
    @BindView(R.id.dLay)
    FrameLayout dLay;

    private static String TAG = "SKY";
    protected Unbinder unbinder;
    @BindView(R.id.u1Tv)
    TextView u1Tv;
    @BindView(R.id.u2Tv)
    TextView u2Tv;
    @BindView(R.id.u3Tv)
    TextView u3Tv;
    @BindView(R.id.u4Tv)
    TextView u4Tv;
    @BindView(R.id.u5Tv)
    TextView u5Tv;
    @BindView(R.id.u6Tv)
    TextView u6Tv;
    @BindView(R.id.u7Tv)
    TextView u7Tv;
    @BindView(R.id.u8Tv)
    TextView u8Tv;
    @BindView(R.id.u9Tv)
    TextView u9Tv;
    @BindView(R.id.l1Tv)
    TextView l1Tv;
    @BindView(R.id.l2Tv)
    TextView l2Tv;
    @BindView(R.id.l3Tv)
    TextView l3Tv;
    @BindView(R.id.l4Tv)
    TextView l4Tv;
    @BindView(R.id.l5Tv)
    TextView l5Tv;
    @BindView(R.id.l6Tv)
    TextView l6Tv;
    @BindView(R.id.l7Tv)
    TextView l7Tv;
    @BindView(R.id.l8Tv)
    TextView l8Tv;
    @BindView(R.id.l9Tv)
    TextView l9Tv;
    @BindView(R.id.f1Tv)
    TextView f1Tv;
    @BindView(R.id.f2Tv)
    TextView f2Tv;
    @BindView(R.id.f3Tv)
    TextView f3Tv;
    @BindView(R.id.f4Tv)
    TextView f4Tv;
    @BindView(R.id.f5Tv)
    TextView f5Tv;
    @BindView(R.id.f6Tv)
    TextView f6Tv;
    @BindView(R.id.f7Tv)
    TextView f7Tv;
    @BindView(R.id.f8Tv)
    TextView f8Tv;
    @BindView(R.id.f9Tv)
    TextView f9Tv;
    @BindView(R.id.r1Tv)
    TextView r1Tv;
    @BindView(R.id.r2Tv)
    TextView r2Tv;
    @BindView(R.id.r3Tv)
    TextView r3Tv;
    @BindView(R.id.r4Tv)
    TextView r4Tv;
    @BindView(R.id.r5Tv)
    TextView r5Tv;
    @BindView(R.id.r6Tv)
    TextView r6Tv;
    @BindView(R.id.r7Tv)
    TextView r7Tv;
    @BindView(R.id.r8Tv)
    TextView r8Tv;
    @BindView(R.id.r9Tv)
    TextView r9Tv;
    @BindView(R.id.b1Tv)
    TextView b1Tv;
    @BindView(R.id.b2Tv)
    TextView b2Tv;
    @BindView(R.id.b3Tv)
    TextView b3Tv;
    @BindView(R.id.b4Tv)
    TextView b4Tv;
    @BindView(R.id.b5Tv)
    TextView b5Tv;
    @BindView(R.id.b6Tv)
    TextView b6Tv;
    @BindView(R.id.b7Tv)
    TextView b7Tv;
    @BindView(R.id.b8Tv)
    TextView b8Tv;
    @BindView(R.id.b9Tv)
    TextView b9Tv;
    @BindView(R.id.d1Tv)
    TextView d1Tv;
    @BindView(R.id.d2Tv)
    TextView d2Tv;
    @BindView(R.id.d3Tv)
    TextView d3Tv;
    @BindView(R.id.d4Tv)
    TextView d4Tv;
    @BindView(R.id.d5Tv)
    TextView d5Tv;
    @BindView(R.id.d6Tv)
    TextView d6Tv;
    @BindView(R.id.d7Tv)
    TextView d7Tv;
    @BindView(R.id.d8Tv)
    TextView d8Tv;
    @BindView(R.id.d9Tv)
    TextView d9Tv;

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.uLay, R.id.lLay, R.id.fLay, R.id.rLay, R.id.bLay, R.id.dLay})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uLay:
                Log.d(TAG, "onClick: uLay");
                flag = "u";
                gotoCamera();
                break;
            case R.id.lLay:
                Log.d(TAG, "onClick: lLay");
                flag = "l";
                gotoCamera();
                break;
            case R.id.fLay:
                Log.d(TAG, "onClick: fLay");
                flag = "f";
                gotoCamera();
                break;
            case R.id.rLay:
                Log.d(TAG, "onClick: rLay");
                flag = "r";
                gotoCamera();
                break;
            case R.id.bLay:
                Log.d(TAG, "onClick: bLay");
                flag = "b";
                gotoCamera();
                break;
            case R.id.dLay:
                Log.d(TAG, "onClick: dLay");
                flag = "d";
                gotoCamera();
                break;
        }
    }

    private File tempFile;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private Bitmap uBitMap;
    private Bitmap lBitMap;
    private Bitmap fBitMap;
    private Bitmap rBitMap;
    private Bitmap bBitMap;
    private Bitmap dBitMap;

    private Pixel u1;
    private Pixel u2;
    private Pixel u3;
    private Pixel u4;
    private Pixel u5;
    private Pixel u6;
    private Pixel u7;
    private Pixel u8;
    private Pixel u9;

    private Pixel l1;
    private Pixel l2;
    private Pixel l3;
    private Pixel l4;
    private Pixel l5;
    private Pixel l6;
    private Pixel l7;
    private Pixel l8;
    private Pixel l9;

    private Pixel f1;
    private Pixel f2;
    private Pixel f3;
    private Pixel f4;
    private Pixel f5;
    private Pixel f6;
    private Pixel f7;
    private Pixel f8;
    private Pixel f9;

    private Pixel r1;
    private Pixel r2;
    private Pixel r3;
    private Pixel r4;
    private Pixel r5;
    private Pixel r6;
    private Pixel r7;
    private Pixel r8;
    private Pixel r9;

    private Pixel b1;
    private Pixel b2;
    private Pixel b3;
    private Pixel b4;
    private Pixel b5;
    private Pixel b6;
    private Pixel b7;
    private Pixel b8;
    private Pixel b9;

    private Pixel d1;
    private Pixel d2;
    private Pixel d3;
    private Pixel d4;
    private Pixel d5;
    private Pixel d6;
    private Pixel d7;
    private Pixel d8;
    private Pixel d9;


    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                    Log.d("evan", "**********camera path*******" + getRealFilePathFromUri(MainActivity.this, Uri.fromFile(tempFile)));
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    switch (flag) {
                        case "u":
                            uIv.setImageBitmap(bitMap);
                            uBitMap = bitMap;
                            getColor(flag);
                            break;
                        case "l":
                            lIv.setImageBitmap(bitMap);
                            lBitMap = bitMap;
                            getColor(flag);
                            break;
                        case "f":
                            fIv.setImageBitmap(bitMap);
                            fBitMap = bitMap;
                            getColor(flag);
                            break;
                        case "r":
                            rIv.setImageBitmap(bitMap);
                            rBitMap = bitMap;
                            getColor(flag);
                            break;
                        case "b":
                            bIv.setImageBitmap(bitMap);
                            bBitMap = bitMap;
                            getColor(flag);
                            break;
                        case "d":
                            dIv.setImageBitmap(bitMap);
                            dBitMap = bitMap;
                            getColor(flag);
                            break;

                    }

                }
                break;
        }
    }

    private void getColor(String flag) {
        int width = 0;
        int height = 0;
        int p = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        switch (flag) {
            case "u":
                width = uBitMap.getWidth();
                height = uBitMap.getHeight();

                p = uBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u1 = new Pixel(r, g, b);
                u1Tv.setText(u1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u2 = new Pixel(r, g, b);
                u2Tv.setText(u2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u3 = new Pixel(r, g, b);
                u3Tv.setText(u3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u4 = new Pixel(r, g, b);
                u4Tv.setText(u4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u5 = new Pixel(r, g, b);
                u5Tv.setText(u5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u6 = new Pixel(r, g, b);
                u6Tv.setText(u6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u7 = new Pixel(r, g, b);
                u7Tv.setText(u7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u8 = new Pixel(r, g, b);
                u8Tv.setText(u8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = uBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                u9 = new Pixel(r, g, b);
                u9Tv.setText(u9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);


                break;
            case "l":
                width = lBitMap.getWidth();
                height = lBitMap.getHeight();

                p = lBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l1 = new Pixel(r, g, b);
                l1Tv.setText(l1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l2 = new Pixel(r, g, b);
                l2Tv.setText(l2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l3 = new Pixel(r, g, b);
                l3Tv.setText(l3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l4 = new Pixel(r, g, b);
                l4Tv.setText(l4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l5 = new Pixel(r, g, b);
               l5Tv.setText(l5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l6 = new Pixel(r, g, b);
                l6Tv.setText(l6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l7 = new Pixel(r, g, b);
                l7Tv.setText(l7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l8 = new Pixel(r, g, b);
                l8Tv.setText(l8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = lBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                l9 = new Pixel(r, g, b);
                l9Tv.setText(l9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);
                break;
            case "f":
                width = fBitMap.getWidth();
                height = fBitMap.getHeight();

                p = fBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f1 = new Pixel(r, g, b);
                f1Tv.setText(f1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f2 = new Pixel(r, g, b);
                f2Tv.setText(f2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f3 = new Pixel(r, g, b);
                f3Tv.setText(f3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f4 = new Pixel(r, g, b);
                f4Tv.setText(f4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f5 = new Pixel(r, g, b);
                f5Tv.setText(f5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f6 = new Pixel(r, g, b);
                f6Tv.setText(f6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f7 = new Pixel(r, g, b);
                f7Tv.setText(f7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f8 = new Pixel(r, g, b);
                f8Tv.setText(f8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = fBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                f9 = new Pixel(r, g, b);
                f9Tv.setText(f9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);
                break;
            case "r":
                width = rBitMap.getWidth();
                height = rBitMap.getHeight();

                p = rBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r1 = new Pixel(r, g, b);
                r1Tv.setText(r1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r2 = new Pixel(r, g, b);
                r2Tv.setText(r2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r3 = new Pixel(r, g, b);
                r3Tv.setText(r3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r4 = new Pixel(r, g, b);
                r4Tv.setText(r4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r5 = new Pixel(r, g, b);
                r5Tv.setText(r5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r6 = new Pixel(r, g, b);
                r6Tv.setText(r6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r7 = new Pixel(r, g, b);
                r7Tv.setText(r7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r8 = new Pixel(r, g, b);
                r8Tv.setText(r8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = rBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                r9 = new Pixel(r, g, b);
                r9Tv.setText(r9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);
                break;
            case "b":
                width = bBitMap.getWidth();
                height = bBitMap.getHeight();

                p = bBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b1 = new Pixel(r, g, b);
                b1Tv.setText(b1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b2 = new Pixel(r, g, b);
                b2Tv.setText(b2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b3 = new Pixel(r, g, b);
                b3Tv.setText(b3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b4 = new Pixel(r, g, b);
                b4Tv.setText(b4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b5 = new Pixel(r, g, b);
                b5Tv.setText(b5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b6 = new Pixel(r, g, b);
                b6Tv.setText(b6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b7 = new Pixel(r, g, b);
                b7Tv.setText(b7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b8 = new Pixel(r, g, b);
                b8Tv.setText(b8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = bBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                b9 = new Pixel(r, g, b);
                b9Tv.setText(b9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);
                break;
            case "d":
                width = dBitMap.getWidth();
                height = dBitMap.getHeight();

                p = dBitMap.getPixel(width / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d1 = new Pixel(r, g, b);
                d1Tv.setText(d1.getColor());
                Log.d(TAG, "1  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width / 2, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d2 = new Pixel(r, g, b);
                d2Tv.setText(d2.getColor());
                Log.d(TAG, "2  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width * 5 / 6, height / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d3 = new Pixel(r, g, b);
                d3Tv.setText(d3.getColor());
                Log.d(TAG, "3  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d4 = new Pixel(r, g, b);
                d4Tv.setText(d4.getColor());
                Log.d(TAG, "4  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width / 2, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d5 = new Pixel(r, g, b);
                d5Tv.setText(d5.getColor());
                Log.d(TAG, "5  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width * 5 / 6, height / 2);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d6 = new Pixel(r, g, b);
                d6Tv.setText(d6.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d7 = new Pixel(r, g, b);
                d7Tv.setText(d7.getColor());
                Log.d(TAG, "7  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width / 2, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d8 = new Pixel(r, g, b);
                d8Tv.setText(d8.getColor());
                Log.d(TAG, "8  R:" + r + ",G:" + g + ",B:" + b);

                p = dBitMap.getPixel(width * 5 / 6, height * 5 / 6);
                r = Color.red(p);
                g = Color.green(p);
                b = Color.blue(p);
                d9 = new Pixel(r, g, b);
                d9Tv.setText(d9.getColor());
                Log.d(TAG, "6  R:" + r + ",G:" + g + ",B:" + b);
                break;
        }
    }


    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
