package com.lxj.xpopupdemo.fragment;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lxj.easyadapter.EasyAdapter;
import com.lxj.easyadapter.MultiItemTypeAdapter;
import com.lxj.easyadapter.ViewHolder;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.widget.VerticalRecyclerView;
import com.lxj.xpopupdemo.R;
import com.lxj.xpopupdemo.custom.CustomDrawerPopupView;
import com.lxj.xpopupdemo.custom.CustomPartShadowPopupView;

import java.util.ArrayList;

/**
 * Description: 局部阴影的示例
 * Create by dance, at 2018/12/21
 */
public class PartShadowDemo extends BaseFragment implements View.OnClickListener {
    View ll_container;
    VerticalRecyclerView recyclerView;
    private CustomPartShadowPopupView popupView;

    private CustomDrawerPopupView drawerPopupView;
    private TextView tv_price;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_part_shadow_demo;
    }

    @Override
    public void init(View view) {
        ll_container = view.findViewById(R.id.ll_container);
        recyclerView = view.findViewById(R.id.recyclerView);

        view.findViewById(R.id.tv_all).setOnClickListener(this);
        tv_price = view.findViewById(R.id.tv_price);
        tv_price.setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);

        drawerPopupView = new CustomDrawerPopupView(getContext());

        final ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }
        EasyAdapter<String> adapter = new EasyAdapter<String>(data, android.R.layout.simple_list_item_1) {
            @Override
            protected void bind(@NonNull ViewHolder holder, @NonNull String s, int position) {
                holder.setText(android.R.id.text1, "长按我试试 - " + position);
                //必须要在事件发生之前就watch
                final XPopup.Builder builder = new XPopup.Builder(getContext()).watchView(holder.itemView);
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        builder.asAttachList(new String[]{"置顶", "编辑", "删除"}, null,0,10, new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                toast(text);
                            }
                        }).show();
                        return true;
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.SimpleOnItemClickListener(){
            @Override
            public void onItemClick(@NonNull View view, @NonNull RecyclerView.ViewHolder holder, int position) {
                toast(data.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
            case R.id.tv_price:
                if(popupView==null){
                    CustomPartShadowPopupView customPartShadowPopupView = new CustomPartShadowPopupView(getContext());
                    this.popupView = (CustomPartShadowPopupView) new XPopup.Builder(getContext())
                            .atView(v)
                            .setPopupCallback(new XPopupCallback() {
                                @Override
                                public void onShow() {
                                    Drawable drawable= getResources().getDrawable(R.mipmap.icon_retract_yellow);
                                    /// 这一步必须要做,否则不会显示.
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    tv_price.setCompoundDrawables(drawable,null,null,null);
                                }
                                @Override
                                public void onDismiss() {
                                    Drawable drawable= getResources().getDrawable(R.mipmap.icon_open_yellow);
                                    /// 这一步必须要做,否则不会显示.
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    tv_price.setCompoundDrawables(drawable,null,null,null);
                                }
                            })
                            .asCustom(customPartShadowPopupView);
                }
                popupView.toggle();
                break;
            case R.id.tv_select:
                new XPopup.Builder(getContext())
                        .atView(v)
                        .asCustom(new CustomPartShadowPopupView(getContext()))
                        .show();
                break;
        }
    }
}
