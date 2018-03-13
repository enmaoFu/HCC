package com.hcc.app.ui.user;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  账户登录fragment
 * @date   2018/03/06
 * @author enmaoFu
 */
public class LoginAccountFgt extends BaseLazyFgt{

    @BindView(R.id.input_pwd)
    EditText inputPwd;
    @BindView(R.id.login_select_number)
    Spinner loginSelectNumber;
    @BindView(R.id.eyes)
    ImageView eyes;

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
        return R.layout.fragment_login_verification;
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

    @OnClick({R.id.eyes,R.id.btn_login})
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
            case R.id.btn_login:
                startActivity(MainActivity.class,null);
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

}
