package com.hcc.app.ui.mining;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.hcc.app.R;
import com.hcc.app.base.BaseAty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import butterknife.BindView;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * @title  邀请好友二维码和图片合成页面
 * @date   2018/03/21
 * @author enmaoFu
 */
public class InviteFriendImageAty extends BaseAty{

    @BindView(R.id.re_bg)
    RelativeLayout reBg;
    @BindView(R.id.code_img)
    ImageView codeImg;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.fh)
    TextView fh;

    /**
     * 透明状态栏（图片）
     */
    private boolean isHide = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend_image;
    }

    @Override
    protected void initData() {
        setStatusBar();
        Bitmap codeImage = CreateTwoDCode("www.baidu.com");
        codeImg.setImageBitmap(codeImage);
    }

    @Override
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    private void setStatusBar() {
        StatusBarCompat.translucentStatusBar(this, isHide);
    }

    @OnClick({R.id.save,R.id.fh})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.save:
                save.setVisibility(View.GONE);
                fh.setVisibility(View.GONE);
                showToast("保存成功");
                intercept();
                finish();
                break;
            case R.id.fh:
                finish();
                break;
        }
    }

    /**
     * 截取布局，保存到相册里
     */
    public void intercept(){
        // 获取图片某布局
        reBg.setDrawingCacheEnabled(true);
        reBg.buildDrawingCache();
        // 获取图片
        Bitmap bmp = reBg.getDrawingCache();
        // 保存图片
        saveBitmap(bmp, "Hcc_Code.png");
    }

    /**
     * 将图片保存到本地，并通知相册更新图库
     * @param bitmap 要保存的图片
     * @param bitName 图片名字（包括后缀）
     */
    private void saveBitmap(Bitmap bitmap,String bitName){
        String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/";
        File file = new File(mFilePath + bitName);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)){
                out.flush();
                out.close();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        //发送广播通知更新图库，发了这个广播进入相册就可以找到保存的图片了
        //file要传保存图片的
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    /**
     * 将指定的内容生成成二维码
     * 该方法是从 compile 'com.singleshu:ZxingPlus:1.1.4' 抽取出来
     * 因为该依赖里得本方法没有提供修改生成二维码的大小，所以抽取出来自己修改，必须依赖于compile 'com.singleshu:ZxingPlus:1.1.4'
     * @param str 传入字符串用于生成二维码
     * @return 返回生成好的二维码事件
     */
    public static Bitmap CreateTwoDCode(String str) {
        try {
            String content = str;
            // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //编码
            hints.put(EncodeHintType.MARGIN, 2);
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
