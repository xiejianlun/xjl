package com.xjl.utils;


import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

/**
 * 激光推送工具类
 * Created by huanglian on 17/5/2.
 */
public class JpushUtils {
    public static Boolean debug = false;

    /**
     * 发送通知到所有设备
     * @param appKey
     * @param masterSecret
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToAll(String appKey, String masterSecret, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_alias_android_ios_alert(null,title,content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到所有android设备
     * @param appKey
     * @param masterSecret
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToAllAndroid(String appKey, String masterSecret, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_alias_android_alert(null,title,content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到所有ios设备
     * @param appKey
     * @param masterSecret
     * @param content
     * @param extras
     */
    public static void sendNotificationToAllIOS(String appKey, String masterSecret, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_alias_ios_alert(null,content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到指定别名设备
     * @param appKey
     * @param masterSecret
     * @param alias
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToAlias(String appKey, String masterSecret, List<String> alias, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_alias_android_ios_alert(alias,title, content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到指定设备
     * @param appKey
     * @param masterSecret
     * @param registrationids
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToRegistrationid(String appKey, String masterSecret, List<String> registrationids, String title, String content, Map<String,String> extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_registrationid_android_ios_alert(registrationids,title, content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自定义推送
     * @param appKey
     * @param masterSecret
     * @param registrationids
     * @param content
     * @param extras
     */
    public static void sendCustomToRegistrationid(String appKey, String masterSecret, List<String> registrationids, String content, Map<String,String> extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_ios_audienceMore_messageWithExtras(registrationids,content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到指定组
     * @param appKey
     * @param masterSecret
     * @param tag
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToTag(String appKey, String masterSecret, String tag, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_tag_android_ios_alert(tag,title, content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送通知到多个指定组交集
     * @param appKey
     * @param masterSecret
     * @param tags
     * @param title
     * @param content
     * @param extras
     */
    public static void sendNotificationToTagAnd(String appKey, String masterSecret, List<String> tags, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_tag_and_android_ios_alert(tags,title, content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendNotificationToTagAlias(String appKey, String masterSecret, String tag, List<String> alias, String title, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_tag_alias_android_ios_alert(tag,alias,title, content,extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息到指定别名设备
     * @param appKey
     * @param masterSecret
     * @param alias
     * @param extras
     */
    public static void sendMessageToAlias(String appKey, String masterSecret, List<String> alias, String content, Map extras) {
        try {
            JPushClient jPushClient = new JPushClient(masterSecret,appKey);
            PushPayload pushPayload= buildPushObject_alias_android_ios_message(alias,content, extras);
            PushResult pushResult=jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode()==200){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PushPayload buildPushObject_alias_android_ios_alert(List<String> alias, String title, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .setTitle(title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(alert)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
//                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(extras)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)

                                .build()
                        )
                        .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_registrationid_android_ios_alert(List<String> registrationids, String title, String alert, Map<String,String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(registrationids))
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setAlert(alert)
                                        .setTitle(title)
                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        .addExtras(extras)
                                        .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                                .setAlert(alert)
                                                //直接传alert
                                                //此项是指定此推送的badge自动加1
//                                .incrBadge(1)
                                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                .setSound("sound.caf")
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                                // .setContentAvailable(true)

                                                .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                            .setApnsProduction(!debug)
                            .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_tag_alias_android_ios_alert(String tag, List<String> alias, String title, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                                .addAudienceTarget(AudienceTarget.tag(tag))
                                .addAudienceTarget(AudienceTarget.alias(alias))
                                .build()
                )
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setAlert(alert)
                                        .setTitle(title)
                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        .addExtras(extras)
                                        .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                                .setAlert(alert)
                                                //直接传alert
                                                //此项是指定此推送的badge自动加1
//                                .incrBadge(1)
                                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                .setSound("sound.caf")
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                                // .setContentAvailable(true)
                                                .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_tag_android_ios_alert(String tag, String title, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                        .setAlert(alert)
                                        .setTitle(title)
                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        .addExtras(extras)
                                        .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                                .setAlert(alert)
                                                //直接传alert
                                                //此项是指定此推送的badge自动加1
//                                .incrBadge(1)
                                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                .setSound("sound.caf")
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                                // .setContentAvailable(true)

                                                .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_tag_and_android_ios_alert(List<String> alias, String title, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag_and(alias))
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .setAlert(alert)
                                                .setTitle(title)
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                                .setAlert(alert)
                                                //直接传alert
                                                //此项是指定此推送的badge自动加1
//                                .incrBadge(1)
                                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                .setSound("sound.caf")
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                                // .setContentAvailable(true)

                                                .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_alias_android_alert(List<String> alias, String title, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(alias == null ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                                .setAlert(alert)
                                                .setTitle(title)
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtras(extras)
                                                .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_alias_ios_alert(List<String> alias, String alert, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(alias == null ? Audience.all() : Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(IosNotification.newBuilder()
                                        //传一个IosAlert对象，指定apns title、title、subtitle等
                                        .setAlert(alert)
                                        //直接传alert
                                        //此项是指定此推送的badge自动加1
//                                        .incrBadge(1)
                                        //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                        // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
//                                        .setSound("sound.caf")
                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        .addExtras(extras)
                                        //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                        // .setContentAvailable(true)

                                        .build()
                                )
                                .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_alias_android_ios_message(List<String> alias, String content, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(alias == null ? Audience.all() : Audience.alias(alias))
                .setMessage(Message.newBuilder()
                            .setMsgContent(content)
                            .setContentType(content)
                            .addExtras(extras)
                            .build()
                )
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }


    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(List<String> registrationids, String content, Map<String,String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(registrationids))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtras(extras)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(!debug)
                        .build()
                )
                .build();
    }

    public static void main(String[] args) {
        sendNotificationToRegistrationid("4a99f3f803428905509f27fe","6c814f77173c224828527a9c",
                ImmutableList.of("121c83f760109e38270"),null,"岁月神偷关注了你",null);
    }
}
