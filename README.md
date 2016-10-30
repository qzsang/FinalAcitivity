该框架是将afinal框架中的FinalActivity单独抽离出来 ，今天分享给大家
afinal 框架地址： https://github.com/yangfuhai/afinal


使用方法我也直接复制官网的了，如下：

完全注解方式就可以进行UI绑定和事件绑定
无需findViewById和setClickListener等

public class AfinalDemoActivity extends FinalActivity {

    //无需调用findViewById和setOnclickListener等
    @ViewInject(id=R.id.button,click="btnClick") Button button;
    @ViewInject(id=R.id.textView) TextView textView;

    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
    }

    public void btnClick(View v){
       textView.setText("text set form button");
    }
}

在其他侵入式框架下使用（如ActionBarShelock）

     protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(view);
        FinalActivity.initInjectedView(this);
     }

在Fragment中使用

     public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
       View viewRoot = inflater.inflate(R.layout.map_frame, container, false);
       FinalActivity.initInjectedView(this,viewRoot);
    }

