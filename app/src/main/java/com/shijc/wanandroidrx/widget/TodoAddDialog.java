package com.shijc.wanandroidrx.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.Dialog;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.utils.TimeUtils;
import java.util.*;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.widget
 * @Description:
 * @date 2019/4/2 下午 4:38
 */
public class TodoAddDialog extends DialogFragment {


    private TextView spn_from_date;

    private CallbackResult callbackResult;

    public void setOnCallbackResult(CallbackResult callbackResult ) {
        this.callbackResult = callbackResult;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.dialog_todo_add, container, false);
        (root_view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        (root_view.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataResult();
                dismiss();
            }
        });
        spn_from_date = root_view.findViewById(R.id.spn_from_date);
        spn_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDatePickerLight();
            }
        });
        return root_view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }



    private void sendDataResult() {

        if (callbackResult != null) {
            callbackResult.sendResult("");
        }
    }


    private void dialogDatePickerLight() {


        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(requireContext(),new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                long date_ship_millis = ((Calendar) calendar).getTimeInMillis();
                spn_from_date.setText(TimeUtils.long2String(date_ship_millis,TimeUtils.FORMAT_TYPE_1));
            }

        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            );

        dialog.show();

    }


    public interface CallbackResult {
        void sendResult(Object obj);
    }
}
