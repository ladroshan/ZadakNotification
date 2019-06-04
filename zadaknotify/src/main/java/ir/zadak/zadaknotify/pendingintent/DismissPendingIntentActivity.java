package ir.zadak.zadaknotify.pendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import ir.zadak.zadaknotify.constants.BroadcastActions;
import ir.zadak.zadaknotify.interfaces.PendingIntentNotification;
import ir.zadak.zadaknotify.notification.PugNotification;


public class DismissPendingIntentActivity implements PendingIntentNotification {
    private final Class<?> mActivity;
    private final Bundle mBundle;
    private final int mIdentifier;

    public DismissPendingIntentActivity(Class<?> activity, Bundle bundle, int identifier) {
        this.mActivity = activity;
        this.mBundle = bundle;
        this.mIdentifier = identifier;
    }

    @Override
    public PendingIntent onSettingPendingIntent() {
        Intent dismissIntentActivity = new Intent(PugNotification.mSingleton.mContext, mActivity);
        dismissIntentActivity.setAction(BroadcastActions.ACTION_PUGNOTIFICATION_DIMISS_INTENT);
        dismissIntentActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        dismissIntentActivity.setPackage(PugNotification.mSingleton.mContext.getPackageName());
        if (mBundle != null) {
            dismissIntentActivity.putExtras(mBundle);
        }

        return PendingIntent.getActivity(PugNotification.mSingleton.mContext, mIdentifier, dismissIntentActivity,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
