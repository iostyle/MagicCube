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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //当前选择的焦点
    private int position;
    protected Unbinder unbinder;
    private File tempFile;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

    private Map<Integer, Bitmap> bitmapMap = new HashMap<>(6);
    private List<ImageView> imageViewList = new ArrayList<>(6);
    private List<TextView> textViewList = new ArrayList<>(54);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        bindViews();
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
                position = 0;
                break;
            case R.id.lLay:
                position = 1;
                break;
            case R.id.fLay:
                position = 2;
                break;
            case R.id.rLay:
                position = 3;
                break;
            case R.id.bLay:
                position = 4;
                break;
            case R.id.dLay:
                position = 5;
                break;
        }
        gotoCamera();
    }

    private void gotoCamera() {
        AndPermission.with(this)
                .runtime().permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE).onGranted(data -> {
            //创建拍照存储的图片文件
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/" + getPackageName() + "/temp"), System.currentTimeMillis() + ".jpg");

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                /*
                 * 指定拍照存储路径
                 * 7.0 及其以上使用FileProvider替换'file://'访问
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //这里的BuildConfig，需要是程序包下BuildConfig。
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", tempFile));
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                }
                startActivityForResult(intent, REQUEST_CAPTURE);
            }

        }).start();
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", tempFile);
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri));
                    } else {
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(tempFile)));
                    }
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

                    imageViewList.get(position).setImageBitmap(bitMap);
                    bitmapMap.put(position, bitMap);
                    getColor();
                }
                break;
        }
    }

    private synchronized void getColor() {
        Bitmap bitmap = bitmapMap.get(position);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int p = 0;
        for (int i = 0; i < 9; i++) {
            switch (i) {
                case 0:
                    p = bitmap.getPixel(width / 6, height / 6);
                    break;
                case 1:
                    p = bitmap.getPixel(width / 2, height / 6);
                    break;
                case 2:
                    p = bitmap.getPixel(width * 5 / 6, height / 6);
                    break;
                case 3:
                    p = bitmap.getPixel(width / 6, height / 2);
                    break;
                case 4:
                    p = bitmap.getPixel(width / 2, height / 2);
                    break;
                case 5:
                    p = bitmap.getPixel(width * 5 / 6, height / 2);
                    break;
                case 6:
                    p = bitmap.getPixel(width / 6, height * 5 / 6);
                    break;
                case 7:
                    p = bitmap.getPixel(width / 2, height * 5 / 6);
                    break;
                case 8:
                    p = bitmap.getPixel(width * 5 / 6, height * 5 / 6);
                    break;
            }
            textViewList.get(position * 9 + i).setText(new Pixel(Color.red(p), Color.green(p), Color.blue(p)).getColor());
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



    // ---------------------------------------------------------------------------------

    @BindView(R.id.uIv)
    ImageView uIv;
    @BindView(R.id.lIv)
    ImageView lIv;
    @BindView(R.id.fIv)
    ImageView fIv;
    @BindView(R.id.rIv)
    ImageView rIv;
    @BindView(R.id.bIv)
    ImageView bIv;
    @BindView(R.id.dIv)
    ImageView dIv;

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

    private void bindViews() {
        imageViewList.add(uIv);
        imageViewList.add(lIv);
        imageViewList.add(fIv);
        imageViewList.add(rIv);
        imageViewList.add(bIv);
        imageViewList.add(dIv);

        textViewList.add(u1Tv);
        textViewList.add(u2Tv);
        textViewList.add(u3Tv);
        textViewList.add(u4Tv);
        textViewList.add(u5Tv);
        textViewList.add(u6Tv);
        textViewList.add(u7Tv);
        textViewList.add(u8Tv);
        textViewList.add(u9Tv);

        textViewList.add(l1Tv);
        textViewList.add(l2Tv);
        textViewList.add(l3Tv);
        textViewList.add(l4Tv);
        textViewList.add(l5Tv);
        textViewList.add(l6Tv);
        textViewList.add(l7Tv);
        textViewList.add(l8Tv);
        textViewList.add(l9Tv);

        textViewList.add(f1Tv);
        textViewList.add(f2Tv);
        textViewList.add(f3Tv);
        textViewList.add(f4Tv);
        textViewList.add(f5Tv);
        textViewList.add(f6Tv);
        textViewList.add(f7Tv);
        textViewList.add(f8Tv);
        textViewList.add(f9Tv);

        textViewList.add(r1Tv);
        textViewList.add(r2Tv);
        textViewList.add(r3Tv);
        textViewList.add(r4Tv);
        textViewList.add(r5Tv);
        textViewList.add(r6Tv);
        textViewList.add(r7Tv);
        textViewList.add(r8Tv);
        textViewList.add(r9Tv);

        textViewList.add(b1Tv);
        textViewList.add(b2Tv);
        textViewList.add(b3Tv);
        textViewList.add(b4Tv);
        textViewList.add(b5Tv);
        textViewList.add(b6Tv);
        textViewList.add(b7Tv);
        textViewList.add(b8Tv);
        textViewList.add(b9Tv);

        textViewList.add(d1Tv);
        textViewList.add(d2Tv);
        textViewList.add(d3Tv);
        textViewList.add(d4Tv);
        textViewList.add(d5Tv);
        textViewList.add(d6Tv);
        textViewList.add(d7Tv);
        textViewList.add(d8Tv);
        textViewList.add(d9Tv);
    }
}
