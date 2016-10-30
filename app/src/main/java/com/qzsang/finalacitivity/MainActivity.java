package com.qzsang.finalacitivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qzsang.finalacitivitylib.FinalActivity;
import com.qzsang.finalacitivitylib.annotation.view.ViewInject;

public class MainActivity extends Activity {

    @ViewInject(id = R.id.tv_text,click = "onClick")
    TextView textView;
    @ViewInject(id = R.id.ll_content)
    LinearLayout linearLayout;
    LinerLayoutHolderView linerLayoutHolderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Activity 中注入方式
        FinalActivity.initInjectedView(this);

        linerLayoutHolderView = new LinerLayoutHolderView(linearLayout);

        test();
    }

    private void test () {
        textView.setText("Activity 中的textView 注入成功");

        linerLayoutHolderView.tvItem1.setText("LinerLayoutHolderView 中的textView1 注入成功");
        linerLayoutHolderView.tvItem2.setText("LinerLayoutHolderView 中的textView2 注入成功");
    }


    public void onClick (View view) {
        Toast.makeText(this,"Activity 中的textView 的点击注入成功",Toast.LENGTH_LONG).show();
    }

    class LinerLayoutHolderView {
        @ViewInject(id = R.id.tv_item_1)
        TextView tvItem1;
        @ViewInject(id = R.id.tv_item_2)
        TextView tvItem2;

        LinerLayoutHolderView(View view) {
            //本地类的注入
            FinalActivity.initInjectedView(this, view);
        }
    }
}
