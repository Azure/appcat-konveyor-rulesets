package com.example.apps;

import java.lang.SecurityManager;



public class AWTSecurityManagement {
    public boolean checkAWTSecurityPermissions()
    {
        SecurityManager securityManager = System.getSecurityManager();

        securityManager.checkMemberAccess(AWTSecurityManagement.class, 3);

        securityManager.checkAwtEventQueueAccess();
            securityManager.checkSystemClipboardAccess();
            securityManager.checkTopLevelWindow(new Object());
        return false;
    }
}