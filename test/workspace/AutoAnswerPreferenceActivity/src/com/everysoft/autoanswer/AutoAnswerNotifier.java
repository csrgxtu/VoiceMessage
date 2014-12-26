/*
 * AutoAnswer
 * Copyright (C) 2010 EverySoft
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.everysoft.autoanswer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AutoAnswerNotifier {

	private final static int NOTIFICATION_ID = 1;
	
	private Context mContext;
	private NotificationManager mNotificationManager;
	private SharedPreferences mSharedPreferences;
	
	public AutoAnswerNotifier(Context context) {
		mContext = context;
		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void updateNotification() {
		if (mSharedPreferences.getBoolean("enabled", false)) {
			this.enableNotification();
		}
		else {
			this.disableNotification();
		}		
	}
	
	private void enableNotification() {
		// Intent to call to turn off AutoAnswer
		Intent notificationIntent = new Intent(mContext, AutoAnswerPreferenceActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
		
		// Create the notification
		Notification n = new Notification(R.drawable.stat_sys_autoanswer, null, 0);
		n.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		n.setLatestEventInfo(mContext, mContext.getString(R.string.notification_title), mContext.getString(R.string.notification_text), pendingIntent);
		mNotificationManager.notify(NOTIFICATION_ID, n);
	}
	
	private void disableNotification() {
		mNotificationManager.cancel(NOTIFICATION_ID);
	}	
}
