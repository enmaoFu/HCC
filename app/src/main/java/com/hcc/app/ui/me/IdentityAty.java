package com.hcc.app.ui.me;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.em.baseframe.util.AppJsonUtil;
import com.em.baseframe.util.RetrofitUtils;
import com.em.baseframe.view.dialog.FormBotomDefaultDialogBuilder;
import com.em.baseframe.view.popupwindow.CBDialogBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hcc.app.R;
import com.hcc.app.base.BaseAty;
import com.hcc.app.config.UserManeger;
import com.hcc.app.http.MeInterface;
import com.hcc.app.util.VerificationNumberUtil;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * @title  身份认证页面
 * @date   2018/03/20
 * @author enmaoFu
 */
public class IdentityAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.identity_head)
    SimpleDraweeView identityHead;
    @BindView(R.id.identity_username)
    TextView identityUsername;
    @BindView(R.id.identity_is)
    ImageView identityIs;
    @BindView(R.id.identity_img)
    ImageView identityImg;
    @BindView(R.id.identity_name)
    TextView identityName;
    @BindView(R.id.identity_number)
    TextView identityNumber;
    @BindView(R.id.identity_btn)
    TextView identityBtn;

    /**
     * 拍照请求码,身份证正面
     *//*
    private static final int REQUEST_CAMERA_Z = 11;

    *//**
     * 相册请求码,身份证正面
     *//*
    private static final int REQUEST_PHOTO_Z  = 12;

    *//**
     * 拍照请求码,身份证反面
     *//*
    private static final int REQUEST_CAMERA_F = 21;

    *//**
     * 相册请求码,身份证反面
     *//*
    private static final int REQUEST_PHOTO_F  = 22;

    *//**
     * 拍照请求码,身份证手持
     *//*
    private static final int REQUEST_CAMERA_SC = 31;

    *//**
     * 相册请求码,身份证手持
     *//*
    private static final int REQUEST_PHOTO_SC  = 32;

    *//**
     * 裁剪身份证正面返回
     *//*
    private static final int CROP_Z  = 42;

    *//**
     * 裁剪身份证反面返回
     *//*
    private static final int CROP_F  = 52;

    *//**
     * 裁剪身份证手持面返回
     *//*
    private static final int CROP_SC  = 62;

    *//**
     * 存放认证的三个照片
     *//*
    private Map<String,String> imgMap = new HashMap<>();

    *//**
     * 图片路径
     *//*
    private String mFilePath;*/

    /**
     * 输入的名字
     */
    private String getInputName;

    /**
     * 输入的身份证号
     */
    private String getInputNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identity;
    }

    @Override
    protected void initData() {

        initToolbar(mToolbar,"身份认证");

    }

    @Override
    public boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {
        showLoadingDialog(null);
        doHttp(RetrofitUtils.createApi(MeInterface.class).getcard(),2);
    }

    @OnClick({R.id.identity_name_re,R.id.identity_number_re,R.id.identity_btn})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.identity_name_re:
                if(UserManeger.getStatus() == 0){
                    new CBDialogBuilder(this, CBDialogBuilder.DIALOG_STYLE_NORMAL)
                            .showIcon(false)
                            .setTouchOutSideCancelable(true)
                            .showCancelButton(false)
                            .setTitle("填写姓名")
                            .setView(R.layout.dialog_identity_name)
                            .setConfirmButtonText("确定")
                            .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                            .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                                @Override
                                public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                    EditText inputName = (EditText)dialog.findViewById(R.id.input_name);
                                    getInputName = inputName.getText().toString().trim();
                                    if(getInputName.length() == 0){
                                        showErrorToast("请输入姓名");
                                    }else{
                                        identityName.setText(getInputName);
                                        dialog.dismiss();
                                    }
                                }
                            })
                            .create().show();
                }
                break;
            case R.id.identity_number_re:
                if(UserManeger.getStatus() == 0){
                    new CBDialogBuilder(this, CBDialogBuilder.DIALOG_STYLE_NORMAL)
                            .showIcon(false)
                            .setTouchOutSideCancelable(true)
                            .showCancelButton(false)
                            .setTitle("填写身份证号")
                            .setView(R.layout.dialog_identity_number)
                            .setConfirmButtonText("确定")
                            .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                            .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                                @Override
                                public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                    EditText inputNumber = (EditText)dialog.findViewById(R.id.input_number);
                                    getInputNumber = inputNumber.getText().toString().trim();
                                    if(getInputNumber.length() == 0){
                                        showErrorToast("请输入身份证号");
                                    }else if(!VerificationNumberUtil.isIDCard(getInputNumber)){
                                        showErrorToast("请输入正确的身份证号");
                                    }else{
                                        identityNumber.setText(getInputNumber);
                                        dialog.dismiss();
                                    }
                                }
                            })
                            .create().show();
                }
                break;
            case R.id.identity_btn:
                if(UserManeger.getStatus() == 0){
                    String getIdentityName = identityName.getText().toString().trim();
                    String getIdentityNumber = identityNumber.getText().toString().trim();
                    if(getIdentityName.equals("未认证，请填写") || getIdentityNumber.equals("未认证，请填写")){
                        showErrorToast("请填写真实姓名和身份证号");
                    }else{
                        showLoadingDialog(null);
                        doHttp(RetrofitUtils.createApi(MeInterface.class).userAuth(getIdentityName,getIdentityNumber),1);
                    }
                }
                /*if(getIdentityName.equals("未认证，请填写") || getIdentity_number.equals("未认证，请填写")){
                    showErrorToast("请填写真实姓名和身份证号");
                }else if(imgMap.size() < 3){
                    showErrorToast("请上传所有要求的身份证图片");
                }else{
                    showToast("认证");
                }*/
                break;
            /*case R.id.sfz_z_text:
                FormBotomDefaultDialogBuilder mDefaultDialogBuilderZ = new FormBotomDefaultDialogBuilder(this);
                mDefaultDialogBuilderZ.setFBFirstBtnText("拍照");
                mDefaultDialogBuilderZ.setFBLastBtnText("相册");
                //点击进行拍照
                mDefaultDialogBuilderZ.setFBFirstBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openCamera(REQUEST_CAMERA_Z);
                    }
                });
                //点击进行相册选择
                mDefaultDialogBuilderZ.setFBLastBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openPhoto(REQUEST_PHOTO_Z);
                    }
                });
                mDefaultDialogBuilderZ.show();
                break;
            case R.id.sfz_f_text:
                FormBotomDefaultDialogBuilder mDefaultDialogBuilderF = new FormBotomDefaultDialogBuilder(this);
                mDefaultDialogBuilderF.setFBFirstBtnText("拍照");
                mDefaultDialogBuilderF.setFBLastBtnText("相册");
                //点击进行拍照
                mDefaultDialogBuilderF.setFBFirstBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openCamera(REQUEST_CAMERA_F);
                    }
                });
                //点击进行相册选择
                mDefaultDialogBuilderF.setFBLastBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openPhoto(REQUEST_PHOTO_F);
                    }
                });
                mDefaultDialogBuilderF.show();
                break;
            case R.id.sfz_sc_text:
                FormBotomDefaultDialogBuilder mDefaultDialogBuilderSc = new FormBotomDefaultDialogBuilder(this);
                mDefaultDialogBuilderSc.setFBFirstBtnText("拍照");
                mDefaultDialogBuilderSc.setFBLastBtnText("相册");
                //点击进行拍照
                mDefaultDialogBuilderSc.setFBFirstBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openCamera(REQUEST_CAMERA_SC);
                    }
                });
                //点击进行相册选择
                mDefaultDialogBuilderSc.setFBLastBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        openPhoto(REQUEST_PHOTO_SC);
                    }
                });
                mDefaultDialogBuilderSc.show();
                break;*/
        }
    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onSuccess(result, call, response, what);
        switch (what){
            case 1:
                UserManeger.setStatus(Integer.parseInt(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"status")));
                isIdentity(UserManeger.getStatus(),getInputName,getInputNumber);
                EventBus.getDefault().post("ok");
                break;
            case 2:
                int status = Integer.parseInt(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"status"));
                if(status == 0){
                    isIdentity(status,"","");
                }else{
                    String username = AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"username");
                    String cardId = AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"card_id");
                    isIdentity(status,username,cardId);
                }
                break;
        }
    }

    public void isIdentity(int status, String username, String iDCard){

        Uri uri = Uri.parse(UserManeger.getHead());
        identityHead.setImageURI(uri);
        identityUsername.setText(UserManeger.getUsername());

        switch (status){
            case 1:
                identityBtn.setBackground(getResources().getDrawable(R.drawable.relative_identity_text_roundn));
                identityBtn.setText("已认证");
                identityIs.setImageDrawable(getResources().getDrawable(R.drawable.rzy));
                identityImg.setVisibility(View.VISIBLE);
                identityName.setText(username);
                identityNumber.setText(iDCard.substring(0,4) + "**********" + iDCard.substring(iDCard.length() - 4));
                break;
            case 0:
                identityBtn.setBackground(getResources().getDrawable(R.drawable.relative_identity_text_round));
                identityBtn.setText("申请认证");
                identityIs.setImageDrawable(getResources().getDrawable(R.drawable.rzw));
                identityImg.setVisibility(View.GONE);
                identityName.setText("未认证，请填写");
                identityNumber.setText("未认证，请填写");
                break;
        }
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CAMERA_Z:
                String cameraZ = "file://"+ mFilePath;
                Logger.v("照相正面" + cameraZ);
                startUcrop(cameraZ,"z",CROP_Z);
                break;
            case REQUEST_CAMERA_F:
                String cameraF = "file://"+ mFilePath;
                Logger.v("照相反面" + cameraF);
                startUcrop(cameraF,"f",CROP_F);
                break;
            case REQUEST_CAMERA_SC:
                String cameraSC = "file://"+ mFilePath;
                Logger.v("照相手持" + cameraSC);
                startUcrop(cameraSC,"sc",CROP_SC);
                break;
            case REQUEST_PHOTO_Z :
                getPhoto(data,"z",CROP_Z);
                Logger.v("相册正面");
                break;
            case REQUEST_PHOTO_F :
                getPhoto(data,"f",CROP_F);
                Logger.v("相册反面");
                break;
            case REQUEST_PHOTO_SC :
                getPhoto(data,"sc",CROP_SC);
                Logger.v("相册手持");
                break;
            case CROP_Z:
                //捕获异常，防止空指针崩溃退出
                try{
                    // 成功（返回的是文件地址）
                    //meHead.setImageURI((Uri) null);
                    //meHead.setImageURI(UCrop.getOutput(data));
                    sfzZ.setImageURI(UCrop.getOutput(data));
                    Logger.v("裁剪正面成功返回: " + UCrop.getOutput(data));
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case CROP_F:
                //捕获异常，防止空指针崩溃退出
                try{
                    // 成功（返回的是文件地址）
                    //meHead.setImageURI((Uri) null);
                    //meHead.setImageURI(UCrop.getOutput(data));
                    Logger.v("裁剪反面成功返回: " + UCrop.getOutput(data));
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case CROP_SC:
                //捕获异常，防止空指针崩溃退出
                try{
                    // 成功（返回的是文件地址）
                    //meHead.setImageURI((Uri) null);
                    //meHead.setImageURI(UCrop.getOutput(data));
                    Logger.v("裁剪手持成功返回: " + UCrop.getOutput(data));
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case UCrop.RESULT_ERROR:
                // 失败
                Logger.v("裁剪失败返回: " + UCrop.getError(data));
                break;
        }
    }*/

    /**
     * 配置裁剪工具
     * @param path
     */
    /*private void startUcrop(String path,String imageName,int requestCode) {
        Uri uri_crop = Uri.parse(path);
        //裁剪后保存到文件中
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/";
        Logger.v(imageName + "...");
        imgMap.put(imageName,mFilePath + imageName + ".jpg");
        //Uri destinationUri = Uri.fromFile(new File(getActivity().getCacheDir(), "SampleCropImage.jpg"));
        Uri destinationUri = Uri.fromFile(new File(mFilePath, imageName + ".jpg"));
        Logger.v("裁剪: " + path);
        UCrop uCrop = UCrop.of(uri_crop, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        options.setHideBottomControls(false);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.start(this,requestCode);
    }*/

    /**
     * 打开相机拍照
     */
    /*private void openCamera(int result) {
        //设置图片的保存路径,作为全局变量
        mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/filename.jpg";
        File temp = new File(mFilePath);
        //获取文件的Uri
        Uri imageFileUri = Uri.fromFile(temp);
        //跳转到相机Activity
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //告诉相机拍摄完毕输出图片到指定的Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, result);
    }*/

    /**
     * 打开相册
     */
    /*private void openPhoto(int result){
        Intent choosePicIntent = new Intent(Intent.ACTION_PICK, null);
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
        startActivityForResult(choosePicIntent, result);
    }*/

    /*public void getPhoto(Intent data,String imageName,int requestCode){
        try {
            if(data != null){
                Uri uri = data.getData();
                if(!TextUtils.isEmpty(uri.getAuthority())){
                    Cursor cursor = getContentResolver().query(uri,new String[]{MediaStore.Images.Media.DATA},null,null,null);
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
                    startUcrop(path,imageName,requestCode);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/

}
