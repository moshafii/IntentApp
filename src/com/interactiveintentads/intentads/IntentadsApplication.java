package com.interactiveintentads.intentads;

import android.app.Application;

import com.interactiveintentads.intentads.ui.MainActivity;
import com.interactiveintentads.intentads.utils.ParseConstants;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
import com.interactiveintentads.intentads.R;

public class IntentadsApplication extends Application {
	
	@Override
	public void onCreate() { 
		super.onCreate();
	    Parse.initialize(this, 
	    		"qVLo6MOAL6B9UfNRv9qb6uWzl948INEYszx06Wbu", 
		    	"I6HZqFN0HQBI3f4d9ZjcZsaXV3yO6zgVVzDEIsU7");
	    
	    //PushService.setDefaultPushCallback(this, MainActivity.class);
	    PushService.setDefaultPushCallback(this, MainActivity.class, 
	    		R.drawable.ic_stat_ic_launcher);
	    ParseInstallation.getCurrentInstallation().saveInBackground();
	}
	
	public static void updateParseInstallation(ParseUser user) {
		ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		installation.put(ParseConstants.KEY_USER_ID, user.getObjectId());
		installation.saveInBackground();
	}
}
