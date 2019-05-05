package com.lxj.xpopupdemo.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.lxj.xpopupdemo.R;
import com.lxj.xpopupdemo.XPopupApp;

/**
 * Description:
 * Create by dance, at 2018/12/21
 */
public class CustomPartShadowPopupView extends PartShadowPopupView {
    int size = 0;

    public CustomPartShadowPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup;
    }

    TextView text;
    TextView text2;

    @Override
    protected void onCreate() {
        super.onCreate();
        text = findViewById(R.id.text);
        text2 = findViewById(R.id.text2);

        Log.e("tag", "CustomPartShadowPopupView onCreate");
        findViewById(R.id.text).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Drawable drawable= getResources().getDrawable(R.mipmap.tab_select);
                /// 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                text.setCompoundDrawables(drawable,null,null,null);

                Drawable drawable2= getResources().getDrawable(R.mipmap.tab_select);
                /// 这一步必须要做,否则不会显示.
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                text2.setCompoundDrawables(null,null,null,null);
                Toast.makeText(XPopupApp.context, "点击了第一个", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.text2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Drawable drawable= getResources().getDrawable(R.mipmap.tab_select);
                /// 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                text.setCompoundDrawables(null,null,null,null);

                Drawable drawable2= getResources().getDrawable(R.mipmap.tab_select);
                /// 这一步必须要做,否则不会显示.
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                text2.setCompoundDrawables(drawable2,null,null,null);

                Toast.makeText(XPopupApp.context, "点击了第二个", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(int size) {
        if (size != 0) {
            this.size = size;
        }
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "CustomPartShadowPopupView onShow");

    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomPartShadowPopupView onDismiss");
    }
}
