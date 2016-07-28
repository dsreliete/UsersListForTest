package com.challenge.buscape.buscapeuserslist.usersDetail;

/**
 * Created by eliete on 7/27/16.
 */
public interface RuntimePermissionActivity {

     void requestAppPermissions(String[] requestedPermissions, final String message,
                                               int requestCode);

     void onPermissionsGranted(int requestCode);

}
