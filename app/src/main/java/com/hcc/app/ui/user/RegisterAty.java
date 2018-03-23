package com.hcc.app.ui.user;

import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.em.baseframe.util.DateTool;
import com.em.baseframe.util.MD5Util;
import com.em.baseframe.util.RetrofitUtils;
import com.hcc.app.R;
import com.hcc.app.base.BaseAty;
import com.hcc.app.http.UserInterface;
import com.hcc.app.util.VerificationNumberUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @title  注册页面
 * @date   2018/03/06
 * @author enmaoFu
 */
public class RegisterAty extends BaseAty{

    @BindView(R.id.register_select_number)
    Spinner registerSelectNumber;
    @BindView(R.id.code_btn)
    TextView codeBtn;
    @BindView(R.id.get_phone)
    EditText getPhone;
    @BindView(R.id.get_code)
    EditText getCode;
    @BindView(R.id.get_invitation)
    EditText getInvitation;
    @BindView(R.id.eyes)
    ImageView eyes;
    @BindView(R.id.input_pwd)
    EditText inputPwd;

    /**
     * 定义一个String类型的List数组作为数据源
     */
    private List<String> dataList;

    /**
     * 定义一个ArrayAdapter适配器作为spinner的数据适配器
     */
    private ArrayAdapter<String> adapterSpinner;

    /**
     * 判断密码的显示隐藏
     */
    private boolean isEyes = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        setSpinnerData();
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.eyes,R.id.back,R.id.code_btn,R.id.register_btn})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.eyes:
                if (isEyes) {
                    // 显示密码
                    eyes.setImageDrawable(getResources().getDrawable(R.drawable.eyesy));
                    inputPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    inputPwd.setSelection(inputPwd.getText().toString().length());
                    isEyes = !isEyes;
                } else {
                    // 隐藏密码
                    eyes.setImageDrawable(getResources().getDrawable(R.drawable.eyesn));
                    inputPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    inputPwd.setSelection(inputPwd.getText().toString().length());
                    isEyes = !isEyes;
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.code_btn:
                String getPhoneText = getPhone.getText().toString().trim();
                String gettCodeText = codeBtn.getText().toString().trim();
                if (getPhoneText.length() == 0){
                    showErrorToast("请输入手机号");
                }else if(VerificationNumberUtil.isMobile(getPhoneText)){
                    if(gettCodeText.equals("获取验证码")){
                        showLoadingDialog(null);
                        String getToken = tokenMd5("get");
                        doHttp(RetrofitUtils.createApi(UserInterface.class).recode("fu","get", getToken, getPhoneText),1);
                    }
                }else{
                    showErrorToast("请输入正确的手机号");
                }
                break;
            case R.id.register_btn:
                String getPhoneTextRe = getPhone.getText().toString().trim();
                String getCodeTextRe = getCode.getText().toString().trim();
                String getInvitationTextRe = getInvitation.getText().toString().trim();
                String getPwdTextRe = inputPwd.getText().toString().trim();
                if(getPhoneTextRe.length() == 0){
                    showErrorToast("请输入手机号");
                }else if(getCodeTextRe.length() == 0){
                    showErrorToast("验证码不能为空");
                }else if(getCodeTextRe.length() < 4){
                    showErrorToast("请输入4位验证码");
                }else if(getPwdTextRe.length() == 0){
                    showErrorToast("请输入密码");
                }else{
                    Logger.v("验证码" + getCodeTextRe);
                    showLoadingDialog(null);
                    String getToken = tokenMd5("set");
                    doHttp(RetrofitUtils.createApi(UserInterface.class).register("fu","set",getToken,getPhoneTextRe,Integer.parseInt(getCodeTextRe),getPwdTextRe,getInvitationTextRe),2);
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onSuccess(result, call, response, what);
        switch (what){
            case 1:
                CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        codeBtn.setText("已发送(" + millisUntilFinished / 1000 + ")");
                    }
                    @Override
                    public void onFinish() {
                        codeBtn.setText("获取验证码");
                    }
                }.start();
                showToast("验证码发送成功请查收");
                break;
            case 2:
                showToast("注册成功，现在返回登陆");
                finish();
                break;
        }
    }

    public void setSpinnerData(){

        //为dataList赋值，将下面这些数据添加到数据源中
        dataList = new ArrayList<String>();
        dataList.add("+86");

        /*为spinner定义适配器，也就是将数据源存入adapter，这里需要三个参数
        1. 第一个是Context（当前上下文），这里就是this
        2. 第二个是spinner的布局样式，这里用android系统提供的一个样式
        3. 第三个就是spinner的数据源，这里就是dataList*/
        adapterSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataList);

        //为适配器设置下拉列表下拉时的菜单样式。
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //为spinner绑定我们定义好的数据适配器
        registerSelectNumber.setAdapter(adapterSpinner);
    }

    public String tokenMd5(String action){
        String getDate = DateTool.getformatDate("yyyy-MM-dd");
        String md5Str = "fu" + getDate + "plkj$" + action;
        String getMd5 = MD5Util.md5(md5Str);
        return getMd5;
    }

}
