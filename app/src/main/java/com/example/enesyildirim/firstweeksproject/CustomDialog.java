package com.example.enesyildirim.firstweeksproject;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.enesyildirim.firstweeksproject.adapter.RecAdapter;

public class CustomDialog implements View.OnClickListener {//TODO Gözden geçirildi!

    Activity activity;
    Dialog dialog;
    int dialogTitle, dialogDesc;
    TextView tittleTv, descTv;
    Button yesBtn, noBtn;
    private OnItemClickListener onItemClickListener;

    public CustomDialog(Activity activity, int dialogTitle, int dialogDesc, OnItemClickListener onItemClickListener) {
        this.activity = activity;
        this.dialogTitle = dialogTitle;
        this.dialogDesc = dialogDesc;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int buttonId, CustomDialog customDialog);
    }

    public void show() {
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.customdialoglayout);

        tittleTv = dialog.findViewById(R.id.tittleTv);
        descTv = dialog.findViewById(R.id.descTv);

        yesBtn = dialog.findViewById(R.id.yes_button);
        noBtn = dialog.findViewById(R.id.no_button);

        tittleTv.setText(dialogTitle);
        descTv.setText(dialogDesc);

        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == yesBtn.getId()) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(yesBtn.getId(), CustomDialog.this);
                dialog.dismiss();
            }
        } else if (view.getId() == noBtn.getId()) {
            dismiss();
        }
    }
}
