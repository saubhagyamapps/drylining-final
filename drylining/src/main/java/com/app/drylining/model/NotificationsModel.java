package com.app.drylining.model;

import java.util.List;

public class NotificationsModel {


    /**
     * status : success
     * totalpages : 6
     * count : 10
     * messages : [{"id":"1002","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1002","time":145421,"content":"Basingstoke job","message_type":"interest","isRead":"read","notification_id":"8434","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1002","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1002","time":145421,"content":"Basingstoke job","message_type":"interest","isRead":"read","notification_id":"8497","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1002","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1002","time":145421,"content":"Basingstoke job","message_type":"interest","isRead":"read","notification_id":"8505","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1001","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1001","time":173538,"content":"North Acton making good job","message_type":"interest","isRead":"read","notification_id":"8435","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1001","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1001","time":173538,"content":"North Acton making good job","message_type":"interest","isRead":"read","notification_id":"8498","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1001","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1001","time":173538,"content":"North Acton making good job","message_type":"interest","isRead":"read","notification_id":"8506","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1000","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1000","time":173636,"content":"price work at Aldershot","message_type":"interest","isRead":"read","notification_id":"8436","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1000","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1000","time":173636,"content":"price work at Aldershot","message_type":"interest","isRead":"read","notification_id":"8499","read":"0","interest_id":"0","confirm_id":"0"},{"id":"1000","sender_id":"51","sender_name":"all_client","receiver_id":"156","offer_id":"1000","time":173636,"content":"price work at Aldershot","message_type":"interest","isRead":"read","notification_id":"8507","read":"0","interest_id":"0","confirm_id":"0"},{"id":"998","sender_id":"167","sender_name":"all_client","receiver_id":"156","offer_id":"998","time":195102,"content":"new job Testing 2\u20ac$£","message_type":"interest","isRead":"read","notification_id":"8437","read":"0","interest_id":"0","confirm_id":"0"}]
     */

    private String status;
    private int totalpages;
    private int count;
    private List<MessagesBean> messages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * id : 1002
         * sender_id : 51
         * sender_name : all_client
         * receiver_id : 156
         * offer_id : 1002
         * time : 145421
         * content : Basingstoke job
         * message_type : interest
         * isRead : read
         * notification_id : 8434
         * read : 0
         * interest_id : 0
         * confirm_id : 0
         */

        private String id;
        private int sender_id;
        private String sender_name;
        private String receiver_id;
        private int offer_id;
        private long time;
        private String content;
        private String message_type;
        private String isRead;
        private String notification_id;
        private String read;
        private String interest_id;
        private String confirm_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSender_id() {
            return sender_id;
        }

        public void setSender_id(int sender_id) {
            this.sender_id = sender_id;
        }

        public String getSender_name() {
            return sender_name;
        }

        public void setSender_name(String sender_name) {
            this.sender_name = sender_name;
        }

        public String getReceiver_id() {
            return receiver_id;
        }

        public void setReceiver_id(String receiver_id) {
            this.receiver_id = receiver_id;
        }

        public int getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(int offer_id) {
            this.offer_id = offer_id;
        }

        public long getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMessage_type() {
            return message_type;
        }

        public void setMessage_type(String message_type) {
            this.message_type = message_type;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getNotification_id() {
            return notification_id;
        }

        public void setNotification_id(String notification_id) {
            this.notification_id = notification_id;
        }

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public String getInterest_id() {
            return interest_id;
        }

        public void setInterest_id(String interest_id) {
            this.interest_id = interest_id;
        }

        public String getConfirm_id() {
            return confirm_id;
        }

        public void setConfirm_id(String confirm_id) {
            this.confirm_id = confirm_id;
        }
    }
}