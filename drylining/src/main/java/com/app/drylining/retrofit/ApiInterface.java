package com.app.drylining.retrofit;


import com.app.drylining.model.ForgotpasswordModel;
import com.app.drylining.model.LogoutModel;
import com.app.drylining.model.MSGCountModel;
import com.app.drylining.model.MyJobModel;
import com.app.drylining.model.NotificationReadModel;
import com.app.drylining.model.NotificationsModel;
import com.app.drylining.model.OTPModel;
import com.app.drylining.model.RecentToolModel;
import com.app.drylining.model.RecentlyAddedJobModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {
    //http://dryliningapp.com/admin/api/verify_otp.php
    @FormUrlEncoded
    @POST("verify_otp.php")
    Call<OTPModel> getOTPSeesionId(@Field("OTPSessionId") String OTPSessionId, @Field("OTPNumber") String OTPNumber, @Field("uid") String userID);

    //http://dryliningapp.com/admin/api/notification_read.php?notification_id=2980
    @FormUrlEncoded
    @POST("notification_read.php")
    Call<NotificationReadModel> getNotificationBit(@Field("notification_id") String notification_id,
                                                   @Field("interest_id") String interest_id,
                                                   @Field("confirm_id") String confirm_id);

    //http://dryliningapp.com/admin/api/all_notification_count.php?click=1
    @FormUrlEncoded
    @POST("all_notification_count.php")
    Call<MSGCountModel> getCountBit(@Field("click") String click, @Field("userId") String userId);

    //http://dryliningapp.com/admin/api/logout.php?user_id=156
    @FormUrlEncoded
    @POST("logout.php")
    Call<LogoutModel> logout(@Field("user_id") String userId);

    //http://dryliningapp.com/admin/api/forgot_pass.php
    @FormUrlEncoded
    @POST("forgot_pass.php")
    Call<ForgotpasswordModel> forgatePassword(@Field("email") String email);


    //http://dryliningapp.com/admin/api/forgot_pass.php
    @FormUrlEncoded
    @POST("new_get_notifications.php")
    Call<NotificationsModel> getNotifacationList(@Field("page") int page,
                                                 @Field("userType") String userType,
                                                 @Field("userId") String userId);

    @FormUrlEncoded
    @POST("new_get_last_search.php")
    Call<RecentlyAddedJobModel> getRecentryAddedJob(@Field("senderId") int senderId,
                                                    @Field("page") int page);

    @FormUrlEncoded
    @POST("new_get_added_properties.php")
    Call<MyJobModel> getMyJobList(@Field("user") int senderId,
                                  @Field("page") int page);

    @FormUrlEncoded
    @POST("new_get_recent_tools.php")
    Call<RecentToolModel> getRecentTool(@Field("senderId") int senderId,
                                        @Field("userType") String userType,
                                        @Field("page") int page);

}
