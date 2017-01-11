package cn.mandroid.wechatrobot.anotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LayoutId
 *
 * @author suanlafen
 * @since 2017/1/11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutId {
    @LayoutRes
    int value() default -1;
}
