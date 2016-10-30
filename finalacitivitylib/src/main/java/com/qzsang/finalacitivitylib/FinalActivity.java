//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qzsang.finalacitivitylib;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;

import com.qzsang.finalacitivitylib.annotation.view.EventListener;
import com.qzsang.finalacitivitylib.annotation.view.Select;
import com.qzsang.finalacitivitylib.annotation.view.ViewInject;

import java.lang.reflect.Field;


public abstract class FinalActivity extends Activity {
    public FinalActivity() {
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initInjectedView(this);
    }

    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        initInjectedView(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        initInjectedView(this);
    }

    public static void initInjectedView(Activity activity) {
        initInjectedView(activity, activity.getWindow().getDecorView());
    }

    public static void initInjectedView(Object injectedSource, View sourceView) {
        Field[] fields = injectedSource.getClass().getDeclaredFields();
        if(fields != null && fields.length > 0) {
            Field[] var6 = fields;
            int var5 = fields.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Field field = var6[var4];

                try {
                    field.setAccessible(true);
                    if(field.get(injectedSource) == null) {
                        ViewInject e = (ViewInject)field.getAnnotation(ViewInject.class);
                        if(e != null) {
                            int viewId = e.id();
                            field.set(injectedSource, sourceView.findViewById(viewId));
                            setListener(injectedSource, field, e.click(), Method.Click);
                            setListener(injectedSource, field, e.longClick(), Method.LongClick);
                            setListener(injectedSource, field, e.itemClick(), Method.ItemClick);
                            setListener(injectedSource, field, e.itemLongClick(), Method.itemLongClick);
                            Select select = e.select();
                            if(!TextUtils.isEmpty(select.selected())) {
                                setViewSelectListener(injectedSource, field, select.selected(), select.noSelected());
                            }
                        }
                    }
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }
        }

    }

    private static void setViewSelectListener(Object injectedSource, Field field, String select, String noSelect) throws Exception {
        Object obj = field.get(injectedSource);
        if(obj instanceof View) {
            ((AbsListView)obj).setOnItemSelectedListener((new EventListener(injectedSource)).select(select).noSelect(noSelect));
        }

    }
    private static void setListener(Object injectedSource, Field field, String methodName, Method method) throws Exception {
        if(methodName != null && methodName.trim().length() != 0) {
            Object obj = field.get(injectedSource);

            switch((method.ordinal() + 1)) {
            case 1:
                if(obj instanceof View) {
                    ((View)obj).setOnClickListener((new EventListener(injectedSource)).click(methodName));
                }
                break;
            case 2:
                if(obj instanceof View) {
                    ((View)obj).setOnLongClickListener((new EventListener(injectedSource)).longClick(methodName));
                }
                break;
            case 3:
                if(obj instanceof AbsListView) {
                    ((AbsListView)obj).setOnItemClickListener((new EventListener(injectedSource)).itemClick(methodName));
                }
                break;
            case 4:
                if(obj instanceof AbsListView) {
                    ((AbsListView)obj).setOnItemLongClickListener((new EventListener(injectedSource)).itemLongClick(methodName));
                }
            }

        }
    }

    public static enum Method {
        Click,
        LongClick,
        ItemClick,
        itemLongClick;

        private Method() {
        }
    }
}
