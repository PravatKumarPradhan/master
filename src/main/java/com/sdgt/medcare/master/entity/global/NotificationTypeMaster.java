package com.sdgt.medcare.master.entity.global;


import com.sdgt.medcare.master.entity.BaseMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="m_notification_type")
class NotificationTypeMaster  extends BaseMaster {

    @Column(name = "push_notification_icon")
    private String pushNotificationIcon;

    @Column(name = "push_notification_title")
    private String pushNotificationTitle;

    public String getPushNotificationIcon() {
        return pushNotificationIcon;
    }

    public void setPushNotificationIcon(String pushNotificationIcon) {
        this.pushNotificationIcon = pushNotificationIcon;
    }

    public String getPushNotificationTitle() {
        return pushNotificationTitle;
    }

    public void setPushNotificationTitle(String pushNotificationTitle) {
        this.pushNotificationTitle = pushNotificationTitle;
    }
}
