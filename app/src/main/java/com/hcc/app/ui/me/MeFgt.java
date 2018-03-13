package com.hcc.app.ui.me;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.em.baseframe.view.popupwindow.CBDialogBuilder;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  我fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MeFgt extends BaseLazyFgt {

    @BindView(R.id.me_head)
    SimpleDraweeView meHead;

    private int curSelectedItemPos = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);

        Uri uri = Uri.parse("http://wx1.sinaimg.cn/thumb150/006DLFVFgy1fop6mhqouyj30xc0xc0y0.jpg");
        meHead.setImageURI(uri);
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.me_head_re,R.id.me_blood_ype,R.id.me_allergy,R.id.me_height,R.id.me_birth_date})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.me_head_re:
                new CBDialogBuilder(getActivity())
                        .setTouchOutSideCancelable(true)
                        .showConfirmButton(false)
                        .setTitle("选择头像")
                        .setWidth(1.0f)
                        .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM)
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"相机", "相册"}, new CBDialogBuilder.onDialogItemClickListener() {
                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                curSelectedItemPos = position;
                                showToast(ItemAdapter.getItem(position).toString());
                                //TODO 保存选中设置
                                dialog.dismiss();
                            }
                        }, curSelectedItemPos)
                        .create().show();
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
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"A型血", "B型血", "O型血", "AB型血"}, new CBDialogBuilder.onDialogItemClickListener() {
                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                curSelectedItemPos = position;
                                //TODO 保存选中设置
                                dialog.dismiss();
                            }
                        }, curSelectedItemPos)
                        .create().show();
                break;
            case R.id.me_allergy:
                new CBDialogBuilder(getActivity())
                        .setTouchOutSideCancelable(true)
                        .showConfirmButton(false)
                        .setTitle("选择过敏源")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setItems(new String[]{"过敏1", "过敏2", "过敏3", "过敏4", "过敏5", "过敏6", "过敏7", "过敏8", "过敏8", "过敏8", "过敏8", "过敏8", "过敏8"}
                        , new CBDialogBuilder.onDialogItemClickListener() {
                            @Override
                            public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter ItemAdapter,
                                                          Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
                                                          int position) {
                                curSelectedItemPos = position;
                                //TODO 保存选中设置
                                dialog.dismiss();
                            }
                        }, curSelectedItemPos)
                        .create().show();
                break;
            case R.id.me_height:
                new CBDialogBuilder(getActivity(), CBDialogBuilder.DIALOG_STYLE_NORMAL)
                        .showIcon(false)
                        .setTouchOutSideCancelable(true)
                        .showCancelButton(false)
                        .setTitle("填写身高")
                        .setView(R.layout.dialog_me_height)
                        .setConfirmButtonText("确定")
                        .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                        .setButtonClickListener(false, new CBDialogBuilder.onDialogbtnClickListener() {
                            @Override
                            public void onDialogbtnClick(Context context, Dialog dialog, int whichBtn) {
                                EditText inputHeight = (EditText)dialog.findViewById(R.id.input_height);
                                String getInputHeight = inputHeight.getText().toString().trim();
                                if(getInputHeight.length() == 0){
                                    showErrorToast("请输入身高");
                                }else{
                                    showToast(getInputHeight);
                                    dialog.dismiss();
                                }
                            }
                        })
                        .create().show();
                break;
        }
    }
}
