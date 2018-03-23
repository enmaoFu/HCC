package com.hcc.app.ui.user;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.em.baseframe.util.AppJsonUtil;
import com.em.baseframe.util.DateTool;
import com.em.baseframe.util.MD5Util;
import com.em.baseframe.util.RetrofitUtils;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.config.UserManeger;
import com.hcc.app.http.UserInterface;
import com.hcc.app.ui.MainActivity;
import com.hcc.app.util.VerificationNumberUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @title  短信验证码登录fragment
 * @date   2018/03/06
 * @author enmaoFu
 */
public class LoginCodeFgt extends BaseLazyFgt{

    @BindView(R.id.login_select_number)
    Spinner loginSelectNumber;
    @BindView(R.id.phone_ed)
    EditText phoneEd;
    @BindView(R.id.code_ed)
    EditText codeEd;
    @BindView(R.id.code_btn)
    TextView codeBtn;

    /**
     * 定义一个String类型的List数组作为数据源
     */
    private List<String> dataList;

    /**
     * 定义一个ArrayAdapter适配器作为spinner的数据适配器
     */
    private ArrayAdapter<String> adapterSpinner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_code;
    }

    @Override
    protected void initData() {
        setSpinnerData();
    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.code_btn,R.id.login_btn})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.code_btn:
                String phoneText = phoneEd.getText().toString().trim();
                String CodeBtnText = codeBtn.getText().toString().trim();
                if(phoneText.length() == 0){
                    showErrorToast("请输入手机号");
                }else if(VerificationNumberUtil.isMobile(phoneText)){
                    if(CodeBtnText.equals("获取验证码")){
                        showLoadingDialog(null);
                        String token = tokenMd5("get");
                        doHttp(RetrofitUtils.createApi(UserInterface.class).setcode("fu","get", token, phoneText),1);
                    }
                }else{
                    showErrorToast("请输入正确的手机号");
                }
                break;
            case R.id.login_btn:
                String phoneTextLogin = phoneEd.getText().toString().trim();
                String codeTextLogin = codeEd.getText().toString().trim();
                if(phoneTextLogin.length() == 0){
                    showErrorToast("请输入手机号");
                }else if(VerificationNumberUtil.isMobile(phoneTextLogin)){
                    if(codeTextLogin.length() == 0){
                        showErrorToast("验证码不能为空");
                    }else if(codeTextLogin.length() < 4){
                        showErrorToast("请输入4位验证码");
                    }else{
                        showLoadingDialog(null);
                        String token = tokenMd5("get");
                        doHttp(RetrofitUtils.createApi(UserInterface.class).login("fu","get",token,phoneTextLogin,"",
                                Integer.parseInt(codeTextLogin)),2);
                    }
                }else{
                    showErrorToast("请输入正确的手机号");
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
                showToast("登录成功");

                //设置为已登录状态
                UserManeger.setIsLogin(true);
                //保存openid（token）
                UserManeger.setOpenid(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"openid"));
                //保存用户名
                UserManeger.setUsername(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"username"));
                //保存用户头像
                UserManeger.setHead(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"avator"));
                //保存用户性别
                UserManeger.setSex(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"sex"));
                //保存用户身高
                UserManeger.setHeight(Integer.parseInt(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"high")));
                //保存用户血型
                UserManeger.setBlood(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"blood"));
                //保存用户认证状态
                UserManeger.setStatus(Integer.parseInt(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"status")));
                //保存用户出生日期
                UserManeger.setDate(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"date"));
                //保存用户体重
                UserManeger.setWeight(Integer.parseInt(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"weight")));
                //保存用户过敏源
                UserManeger.setAllergy(AppJsonUtil.getString(AppJsonUtil.getString(result, "data"),"allergy"));

                RetrofitUtils.init(UserManeger.getOpenid());

                setHasAnimiation(false);
                startActivity(MainActivity.class,null);
                getActivity().overridePendingTransition(R.anim.aty_in, R.anim.activity_alpha_out);
                break;
        }
    }

    public void setSpinnerData(){

        //为dataList赋值，将下面这些数据添加到数据源中
        dataList = new ArrayList<String>();
        dataList.add("+86");
        dataList.add("+87");

        /*为spinner定义适配器，也就是将数据源存入adapter，这里需要三个参数
        1. 第一个是Context（当前上下文），这里就是this
        2. 第二个是spinner的布局样式，这里用android系统提供的一个样式
        3. 第三个就是spinner的数据源，这里就是dataList*/
        adapterSpinner = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,dataList);

        //为适配器设置下拉列表下拉时的菜单样式。
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //为spinner绑定我们定义好的数据适配器
        loginSelectNumber.setAdapter(adapterSpinner);
    }

    public String tokenMd5(String action){
        String getDate = DateTool.getformatDate("yyyy-MM-dd");
        String md5Str = "fu" + getDate + "plkj$" + action;
        String getMd5 = MD5Util.md5(md5Str);
        return getMd5;
    }

}
