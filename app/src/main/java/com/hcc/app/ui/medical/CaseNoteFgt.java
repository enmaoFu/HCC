package com.hcc.app.ui.medical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.adapter.CaseNoteAdapter;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.pojo.CaseNotePojo;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  病历fragemnt
 * @date   2018/03/02
 * @author enmaoFu
 */
public class CaseNoteFgt extends BaseLazyFgt {

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.image)
    ImageView image;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private CaseNoteAdapter caseNoteAdapter;
    //数据源
    private List<CaseNotePojo> caseNotes;

    //拍照请求码
    private static final int REQUEST_CAMERA = 1;

    //相册请求码
    private static final int REQUEST_PHOTO  = 2;

    //图片路径
    private String mFilePath;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_medical_case_note;
    }

    @Override
    protected void initData() {

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        caseNoteAdapter = new CaseNoteAdapter(R.layout.item_case_note, null);
        //设置布局管理器
        rvData.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //大小不受适配器影响
        rvData.setHasFixedSize(true);
        //设置加载动画类型
        caseNoteAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rvData.setItemAnimator(new DefaultItemAnimator());
        //设置空数据页面
        //setEmptyView(caseNoteAdapter);
        //设置adapter
        rvData.setAdapter(caseNoteAdapter);

    }

    @Override
    protected void requestData() {

    }

    @SuppressLint("Range")
    @OnClick({R.id.camera})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.camera:
                //点击弹出对话框，选择拍照或者系统相册
                new AlertDialog.Builder(getActivity()).setTitle("上传病历")//设置对话框标题
                        .setPositiveButton("拍照", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                openCamera();
                            }
                        }).setNegativeButton("系统相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openPhoto();
                    }
                }).show();//在按键响应事件中显示此对话框
                break;
        }
    }

    /**
     * 设置病历的RecyclerView空数据页面
     *
     * @param quickAdapter
     */
    public void setEmptyView(BaseQuickAdapter quickAdapter) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_medical_null, null, false);
        quickAdapter.setEmptyView(view);
    }

    /**
     * 设置数据
     * @return
     */
    public List<CaseNotePojo> setData(){
        caseNotes = new ArrayList<>();
        CaseNotePojo caseNote = null;
        for(int i = 0; i < 8; i++){
            caseNote = new CaseNotePojo();
            caseNotes.add(caseNote);
        }
        return caseNotes;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CAMERA:
                String s = "file://"+ mFilePath;
                startUcrop(s);
                break;
            case REQUEST_PHOTO :
                try {
                    if(data != null){
                        Uri uri = data.getData();
                        if(!TextUtils.isEmpty(uri.getAuthority())){
                            Cursor cursor = getActivity().getContentResolver().query(uri,new String[]{MediaStore.Images.Media.DATA},null,null,null);
                            if(null == cursor){
                                return;
                            }
                            cursor.moveToFirst();
                            //拿到了照片的path
                            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                            cursor.close();
                            path = "file://"+ path;
                           // Logger.v(path + "....");
                            //启动裁剪界面，配置裁剪参数
                            startUcrop(path);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case UCrop.REQUEST_CROP:
                //捕获异常，防止空指针崩溃退出
                try{
                    // 成功（返回的是文件地址）
                    Logger.v("裁剪成功返回: " + UCrop.getOutput(data));
                    image.setImageURI(null);
                    image.setImageURI(UCrop.getOutput(data));
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case UCrop.RESULT_ERROR:
                // 失败
                Logger.v("裁剪失败返回: " + UCrop.getError(data));
                break;
        }
    }

    private void startUcrop(String path) {
        Uri uri_crop = Uri.parse(path);
        //裁剪后保存到文件中
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/";
        //Uri destinationUri = Uri.fromFile(new File(getActivity().getCacheDir(), "SampleCropImage.jpg"));
        Uri destinationUri = Uri.fromFile(new File(mFilePath, "SampleCropImage.jpg"));
        Logger.v("裁剪: " + path);
        UCrop uCrop = UCrop.of(uri_crop, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        options.setHideBottomControls(false);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(getActivity(), R.color.colorPrimary));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.start(getActivity(),this);
    }

    /**
     * 启动相机拍照
     */
    private void openCamera() {
        //设置图片的保存路径,作为全局变量
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/filename.jpg";
        File temp = new File(mFilePath);
        //获取文件的Uri
        Uri imageFileUri = Uri.fromFile(temp);
        //跳转到相机Activity
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //告诉相机拍摄完毕输出图片到指定的Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    /**
     * 启动相册
     */
    private void openPhoto(){
        Intent choosePicIntent = new Intent(Intent.ACTION_PICK, null);
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(choosePicIntent, REQUEST_PHOTO);
    }

}
