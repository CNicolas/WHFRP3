/*
package com.whfrp3.ihm.components.notification;


import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.whfrp3.R;
import com.whfrp3.WHFRP3;
import com.whfrp3.databinding.ToastNotificationBinding;
import com.whfrp3.tools.WHFRP3Application;

*/
/**
 * Exception thrown when the bundle given on new Activity is not well formed.
 *//*

public class ToastNotification {
    private final String mMessage;
    private final ToastType mType;

    private ToastNotification(String message, ToastType type) {
        this.mMessage = message;
        this.mType = type;
    }

    public void show() {
        show(this);
    }

    public static void info(String message) {
        show(new ToastNotification(message, ToastType.INFO));
    }

    public static void warning(String message) {
        show(new ToastNotification(message, ToastType.WARNING));
    }

    public static void error(String message) {
        show(new ToastNotification(message, ToastType.ERROR));
    }

    private static void show(LayoutInflater layoutInflater, ToastNotification toastNotification) {
        layoutInflater.inflate(R.layout.to)
        toastBinding.setToast(toastNotification);

        Toast toast = new Toast(WHFRP3Application.getAppContext());
        toast.setView(toastBinding.getRoot());
        toast.show();
    }

    public String getMessage() {
        return mMessage;
    }

    public ToastType getType() {
        return mType;
    }


}
*/
