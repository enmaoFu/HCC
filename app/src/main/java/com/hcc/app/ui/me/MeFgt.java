package com.hcc.app.ui.me;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.em.baseframe.util.AppJsonUtil;
import com.em.baseframe.util.AppManger;
import com.em.baseframe.util.DateTool;
import com.em.baseframe.util.RetrofitUtils;
import com.em.baseframe.view.dialog.Effectstype;
import com.em.baseframe.view.dialog.FormBotomDefaultDialogBuilder;
import com.em.baseframe.view.dialog.MaterialDialog;
import com.em.baseframe.view.popupwindow.CBDialogBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.config.UserManeger;
import com.hcc.app.http.MeInterface;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import qiu.niorgai.StatusBarCompat;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @title  我fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MeFgt extends BaseLazyFgt {

    @BindView(R.id.me_head)
    SimpleDraweeView meHead;
    @BindView(R.id.m_status)
    TextView mStatus;
    @BindView(R.id.m_date)
    TextView mDate;
    @BindView(R.id.m_high)
    TextView mHigh;
    @BindView(R.id.m_blood)
    TextView mBlood;
    @BindView(R.id.m_weight)
    TextView mWeight;
    @BindView(R.id.m_sex)
    TextView mSex;
    @BindView(R.id.m_allergy)
    TextView mAllergy;

    /**
     * 拍照请求码
     */
    private static final int REQUEST_CAMERA = 1;

    /**
     * 相册请求码
     */
    private static final int REQUEST_PHOTO  = 2;

    /**
     * 图片路径
     */
    private String mFilePath;

    /**
     * 编辑的身高、体重、过敏源
     */
    private String getInputHeightWeightAllergy;

    /**
     * 编辑的出生日期
     */
    private String getDate;

    /**
     * 编辑的血型
     */
    private String bloodStr;
    /**
     * 用于在点击血型弹出来框架的时候，选中的是我当前的血型那一栏
     */
    private int bloodStrSelectedItemPos;

    /**
     * 编辑的性别
     */
    private String sexStr;
    /**
     * 用于在点击性别弹出来框架的时候，选中的是我当前的血型那一栏
     */
    private int sexStrSelectedItemPos;

    /**
     * 弹出相机和相册选择框
     */
    private FormBotomDefaultDialogBuilder mDefaultDialogBuilder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        setUserMessage();
        setStatusBar();
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        setUserMessage();
        setStatusBar();
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.me_head_re,R.id.me_blood_ype,R.id.me_allergy,R.id.me_height,R.id.me_birth_date,R.id.family_re,
    R.id.identity_re,R.id.out,R.id.weight,R.id.sex})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.me_head_re:
                if (mDefaultDialogBuilder == null) {
                    mDefaultDialogBuilder = new FormBotomDefaultDialogBuilder(getActivity());
                    mDefaultDialogBuilder.setFBFirstBtnText("拍照");
                    mDefaultDialogBuilder.setFBLastBtnText("相册");
                    //点击进行拍照
                    mDefaultDialogBuilder.setFBFirstBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                        @Override
                        public void dialogBtnOnClick() {
                            openCamera();
                        }
                    });
                    //点击进行相册选择
                    mDefaultDialogBuilder.setFBLastBtnClick(new FormBotomDefaultDialogBuilder.DialogBtnCallBack() {
                        @Override
                        public void dialogBtnOnClick() {
                            openPhoto();
                        }
                    });
                }
                mDefaultDialogBuilder.show();
                break;
            case R.id.me_birth_date:
                long afterYears = 365 * 1000 * 60 * 60 * 24L / 60L;
                TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                        .setCancelStringId("取消")
                        .setSureStringId("确定")
                        .setTitleStringId("选择出生日期")
                        .setYearText("年")
                        .setMonthText("月")
                        .setDayText("日")
                        .setCyclic(false)
                        .setMinMillseconds(afterYears)
                        .setMaxMillseconds(System.currentTimeMillis())
                        .setCurrentMillseconds(System.currentTimeMillis())
                        .setThemeColor(Color.parseColor("#769dfc"))
                        .setType(Type.YEAR_MONTH_DAY)
                        .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                        .setWheelItemTextSelectorColor(Color.parseColor("#769dfc"))
                        .setWheelItemTextSize(12)
                        .setCallBack(new OnDateSetListener() {
                            @Override
                            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                                getDate = DateTool.getDateToString(millseconds);
                                showLoadingDialog(null);
                                doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("date",getDate),3);
                            }
                        })
                        .build();
                mDialogAll.show(getFragmentManager(), "year_month_day");
                break;
            case R.id.me_blood_ype:
                new CBDialogBuilder(getActivity())
                        .setTouchOutSideCancelable(true)
                        .showConfirmButton(false)
                        .setTitle("选择血型")
                        .setTitleTextSize(14)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"A型血", "B型血", "O型血", "AB型血"}, new CBDialogBuilder.onDialogItemClickListener() {
                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                bloodStrSelectedItemPos = position;
                                bloodStr = (String) ItemAdapter.getItem(position);
                                dialog.dismiss();
                                showLoadingDialog(null);
                                doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("blood",bloodStr),4);
                            }
                        }, bloodStrSelectedItemPos)
                        .create().show();
                break;
            case R.id.me_allergy:
                new CBDialogBuilder(getActivity(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(false)
                        .setTitle("填写过敏源")
                        .setTitleTextSize(14)
                        .setView(R.layout.dialog_me_allergy)
                        .setConfirmButtonText("确定")
                        .setConfirmButtonTextSize(14)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                EditText inputHeightWeight = (EditText)dialog.findViewById(R.id.input_allergy);
                                getInputHeightWeightAllergy = inputHeightWeight.getText().toString().trim();
                                if(getInputHeightWeightAllergy.length() == 0){
                                    showErrorToast("请输入过敏源");
                                }else{
                                    dialog.dismiss();
                                    showLoadingDialog(null);
                                    doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("allergy",getInputHeightWeightAllergy),6);
                                }
                            }
                        })
                        .create().show();
                break;
            case R.id.me_height:
                new CBDialogBuilder(getActivity(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(false)
                        .setTitle("填写身高（cm）")
                        .setTitleTextSize(14)
                        .setView(R.layout.dialog_me_height_weight)
                        .setConfirmButtonText("确定")
                        .setConfirmButtonTextSize(14)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                EditText inputHeightWeight = (EditText)dialog.findViewById(R.id.input_height_weight);
                                getInputHeightWeightAllergy = inputHeightWeight.getText().toString().trim();
                                if(getInputHeightWeightAllergy.length() == 0){
                                    showErrorToast("请输入身高");
                                }else if(Integer.parseInt(getInputHeightWeightAllergy) > 200){
                                    showErrorToast("请输入正确身高");
                                }else{
                                    dialog.dismiss();
                                    showLoadingDialog(null);
                                    doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("high",getInputHeightWeightAllergy),1);
                                }
                            }
                        })
                        .create().show();
                break;
            case R.id.weight:
                new CBDialogBuilder(getActivity(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(false)
                        .setTitle("填写体重（kg）")
                        .setTitleTextSize(14)
                        .setView(R.layout.dialog_me_height_weight)
                        .setConfirmButtonText("确定")
                        .setConfirmButtonTextSize(14)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                EditText inputHeightWeight = (EditText)dialog.findViewById(R.id.input_height_weight);
                                getInputHeightWeightAllergy = inputHeightWeight.getText().toString().trim();
                                if(getInputHeightWeightAllergy.length() == 0){
                                    showErrorToast("请输入体重");
                                }else if(Integer.parseInt(getInputHeightWeightAllergy) > 100){
                                    showErrorToast("请输入正确体重");
                                }else{
                                    dialog.dismiss();
                                    showLoadingDialog(null);
                                    doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("weight",getInputHeightWeightAllergy),2);
                                }
                            }
                        })
                        .create().show();
                break;
            case R.id.sex:
                new CBDialogBuilder(getActivity())
                        .setTouchOutSideCancelable(true)
                        .showConfirmButton(false)
                        .setTitle("选择性别")
                        .setTitleTextSize(14)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"男", "女"}
                                , new CBDialogBuilder.onDialogItemClickListener() {
                                    @Override
                                    public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                                  Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                                  int position) {
                                        sexStrSelectedItemPos = position;
                                        sexStr = (String) ItemAdapter.getItem(position);
                                        dialog.dismiss();
                                        showLoadingDialog(null);
                                        doHttp(RetrofitUtils.createApi(MeInterface.class).quickEdit("sex",sexStr),5);
                                    }
                                }, sexStrSelectedItemPos)
                        .create().show();
                break;
            case R.id.family_re:
                startActivity(FamilyAty.class,null);
                break;
            case R.id.identity_re:
                startActivity(IdentityAty.class,null);
                break;
            case R.id.out:
                MaterialDialog dialog = new MaterialDialog(getActivity());
                dialog.setMDMessage("是否立即退出当前账号?").setMDConfirmBtnClick(new MaterialDialog.DialogBtnCallBack() {
                    @Override
                    public void dialogBtnOnClick() {
                        //退出登录
                        setHasAnimiation(false);
                        UserManeger.setIsLogin(false);
                        UserManeger.setOpenid("");
                        UserManeger.setUsername("");
                        UserManeger.setHead("");
                        UserManeger.setSex("");
                        UserManeger.setHeight(0);
                        UserManeger.setBlood("");
                        UserManeger.setStatus(0);
                        UserManeger.setDate("");
                        UserManeger.setWeight(0);
                        UserManeger.setAllergy("");
                        AppManger.getInstance().killAllActivity();
                        System.exit(0);
                    }
                }).setMDEffect(Effectstype.Shake).show();
                break;
        }
    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onSuccess(result, call, response, what);
        switch (what){
            case 1:
                UserManeger.setHeight(Integer.parseInt(getInputHeightWeightAllergy));
                mHigh.setText(UserManeger.getHeight() + "cm");
                showToast("设置身高成功");
                break;
            case 2:
                UserManeger.setWeight(Integer.parseInt(getInputHeightWeightAllergy));
                mWeight.setText(UserManeger.getWeight() + "kg");
                showToast("设置体重成功");
                break;
            case 3:
                UserManeger.setDate(getDate);
                mDate.setText(UserManeger.getDate());
                showToast("设置出生日期成功");
                break;
            case 4:
                UserManeger.setBlood(bloodStr);
                mBlood.setText(bloodStr);
                showToast("设置血型成功");
                break;
            case 5:
                UserManeger.setSex(sexStr);
                mSex.setText(UserManeger.getSex());
                showToast("设置性别成功");
                break;
            case 6:
                UserManeger.setAllergy(getInputHeightWeightAllergy);
                mAllergy.setText(UserManeger.getAllergy());
                showToast("设置过敏源成功");
                break;
            case 7:
                UserManeger.setHead(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"avator"));
                meHead.setImageURI(UserManeger.getHead());
                showToast("设置头像成功");
                break;
        }
    }

    /**
     * 设置用户的基本信息
     */
    public void setUserMessage(){
        Uri uri = Uri.parse(UserManeger.getHead());
        meHead.setImageURI(uri);

        if(UserManeger.getStatus() == 0){
            mStatus.setText("未认证");
        }else{
            mStatus.setText("已认证");
        }

        if(UserManeger.getDate().equals("0")){
            mDate.setText("未设置");
        }else{
            mDate.setText(UserManeger.getDate());
        }

        if(UserManeger.getHeight() == 0){
            mHigh.setText("未设置");
        }else{
            mHigh.setText(UserManeger.getHeight() + "cm");
        }

        if(UserManeger.getBlood().equals("0")){
            mBlood.setText("未设置");
        }else{
            mBlood.setText(UserManeger.getBlood());
        }
        if(UserManeger.getBlood().equals("A型血")){
            bloodStrSelectedItemPos = 0;
        }else if(UserManeger.getBlood().equals("B型血")){
            bloodStrSelectedItemPos = 1;
        }else if(UserManeger.getBlood().equals("O型血")){
            bloodStrSelectedItemPos = 2;
        }else if(UserManeger.getBlood().equals("AB型血")){
            bloodStrSelectedItemPos = 3;
        }

        if(UserManeger.getWeight() == 0){
            mWeight.setText("未设置");
        }else{
            mWeight.setText(UserManeger.getWeight() + "kg");
        }

        if(UserManeger.getSex().equals("0")){
            mSex.setText("未设置");
        }else{
            mSex.setText(UserManeger.getSex());
        }
        if(UserManeger.getSex().equals("男")){
            sexStrSelectedItemPos = 0;
        }else if(UserManeger.getSex().equals("女")){
            sexStrSelectedItemPos = 1;
        }

        if(UserManeger.getAllergy().equals("0")){
            mAllergy.setText("未设置");
        }else{
            mAllergy.setText(UserManeger.getAllergy());
        }
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
                    //meHead.setImageURI((Uri) null);
                    //meHead.setImageURI(UCrop.getOutput(data));
                    Logger.v("裁剪成功返回: " + UCrop.getOutput(data));
                    File file = new File(mFilePath,"SampleCropImage.jpg");
                    //create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("avator", file.getName(), requestFile);
                    showLoadingDialog(null);
                    doHttp(RetrofitUtils.createApi(MeInterface.class).editAvator(body),7);
                    //doHttp(RetrofitUtils.createApi(MeInterface.class).editAvator(requestFile),7);
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

    /**
     * 配置裁剪工具
     * @param path
     */
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
     * 打开相机拍照
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
     * 打开相册
     */
    private void openPhoto(){
        Intent choosePicIntent = new Intent(Intent.ACTION_PICK, null);
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(choosePicIntent, REQUEST_PHOTO);
    }

    /**
     * 事件总线，接收IdentityAty发送的已认证消息
     * @param str
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String str) {
        Logger.v(str + "...");
        if(str.equals("ok")){
            mStatus.setText("已认证");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
