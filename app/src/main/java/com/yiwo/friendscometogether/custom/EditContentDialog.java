package com.yiwo.friendscometogether.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

/**
 * Created by Administrator on 2018/7/20.
 */

public class EditContentDialog extends Dialog {

    private Context context;
    private OnReturnListener listener;

    private RelativeLayout rlClose;
    private EditText etContent;
    private Button btnOk;

    public void setOnReturnListener(OnReturnListener listener){
        this.listener = listener;
    }

    public EditContentDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_content, null);
        setContentView(view);
        ScreenAdapterTools.getInstance().loadView(view);

        rlClose = view.findViewById(R.id.dialog_edit_content_rl_close);
        etContent = view.findViewById(R.id.dialog_edit_content_et_content);
        btnOk = view.findViewById(R.id.dialog_edit_content_btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onReturn(etContent.getText().toString());
            }
        });
        rlClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public interface OnReturnListener{
        void onReturn(String content);
    }

}
