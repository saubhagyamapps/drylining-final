package com.app.drylining.model;

import java.util.List;

public class MyJobModel {

    /**
     * status : success
     * count : 8
     * totalpages : 1
     * result : [{"id":"1007","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"Commerce Six Rd","postcity":"Ahmedabad","job_status":"0","description":"Need tapers for a job in Basingstoke .Interested tapers should call this number 07956 684580.\n£160 before tax","created_at":"2019-01-31 07:22:08","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"1006","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"Commerce Six Rd","postcity":"Ahmedabad","job_status":"0","description":"Need tapers for a job in Basingstoke .Interested tapers should call this number 07956 684580.£160 before tax","created_at":"2019-01-31 07:21:09","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"1005","name":"& % $# @ £ \u20ac\n","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"Commerce Six Rd","postcity":"Ahmedabad","job_status":"0","description":"& % $# @ £ \u20ac  130& % $# @ £ \u20ac\nfdsjkfhsakjfksdhfkjsa& % $# @ £ \u20ac\n\n","created_at":"2019-01-29 14:28:59","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"1004","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"Commerce Six Rd","postcity":"Ahmedabad","job_status":"0","description":"helooo","created_at":"2019-01-29 10:39:23","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"1003","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"Commerce Six Rd","postcity":"Ahmedabad","job_status":"0","description":"helooo","created_at":"2019-01-29 10:19:56","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"983","name":"tesing","job_type":"Boarding","work_type":"Day work","price":"2","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"hhbb","postcity":"Ahmedabad","job_status":"0","description":"testing's","created_at":"2019-01-26 05:59:48","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"982","name":"testing's","job_type":"Boarding","work_type":"Day work","price":"20","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"vvv","postcity":"Ahmedabad","job_status":"0","description":"hjj","created_at":"2019-01-26 05:50:49","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"981","name":"testing","job_type":"Boarding","work_type":"Day work","price":"20","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","worker":"","image_path":"","streetName":"ahme","postcity":"Ahmedabad","job_status":"0","description":"hah","created_at":"2019-01-26 04:01:19","published_at":null,"revision":"","conversation_count":"0","countInterest":"0"},{"id":"932","name":"Final phase","job_type":"Boarding","work_type":"Day work","price":"150","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"225","worker":"","image_path":"","streetName":"By Motorway ","postcity":"Chestnut ","job_status":"0","created_at":"2019-01-09 08:07:04","published_at":null,"revision":"","conversation_count":"0","countInterest":"3"},{"id":"923","name":"worker","job_type":"Boarding","work_type":"Day work","price":"500","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"223","worker":"","image_path":"","streetName":"Ahmedabad ","postcity":"Ahmedabad ","job_status":"0","created_at":"2019-01-09 07:13:22","published_at":null,"revision":"","conversation_count":"0","countInterest":"1"}]
     * msgs : [0,0,0,0,0,0,0,0,0,0,0]
     * isUnReadMsgs : [0,0,0,0,0,0,0,0,0,0,0]
     * notifications : 80
     * userInfo : {"id":"156","name":"paul waker","email":"waker@gmail.com","phone":"919898919697","password":"12341234","created_at":"2018-12-07 05:57:45","updated_at":"2018-12-07 05:57:45","type":"R","shareProfile":"1","otp_code":"","firebase_id":"-1","is_admin":"0"}
     */

    private String status;
    private int count;
    private int totalpages;
    private int notifications;
    private UserInfoBean userInfo;
    private List<ResultBean> result;
    private List<Integer> msgs;
    private List<Integer> isUnReadMsgs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public int getNotifications() {
        return notifications;
    }

    public void setNotifications(int notifications) {
        this.notifications = notifications;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public List<Integer> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Integer> msgs) {
        this.msgs = msgs;
    }

    public List<Integer> getIsUnReadMsgs() {
        return isUnReadMsgs;
    }

    public void setIsUnReadMsgs(List<Integer> isUnReadMsgs) {
        this.isUnReadMsgs = isUnReadMsgs;
    }

    public static class UserInfoBean {
        /**
         * id : 156
         * name : paul waker
         * email : waker@gmail.com
         * phone : 919898919697
         * password : 12341234
         * created_at : 2018-12-07 05:57:45
         * updated_at : 2018-12-07 05:57:45
         * type : R
         * shareProfile : 1
         * otp_code :
         * firebase_id : -1
         * is_admin : 0
         */

        private String id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String created_at;
        private String updated_at;
        private String type;
        private String shareProfile;
        private String otp_code;
        private String firebase_id;
        private String is_admin;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShareProfile() {
            return shareProfile;
        }

        public void setShareProfile(String shareProfile) {
            this.shareProfile = shareProfile;
        }

        public String getOtp_code() {
            return otp_code;
        }

        public void setOtp_code(String otp_code) {
            this.otp_code = otp_code;
        }

        public String getFirebase_id() {
            return firebase_id;
        }

        public void setFirebase_id(String firebase_id) {
            this.firebase_id = firebase_id;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }
    }

    public static class ResultBean {
        /**
         * id : 1007
         * name : new job Testing 2
         * job_type : Boarding
         * work_type : Day work
         * price : 12212
         * currency_type : Pound
         * longitude : 0
         * latitude : 0
         * user_id : 156
         * worker :
         * image_path :
         * streetName : Commerce Six Rd
         * postcity : Ahmedabad
         * job_status : 0
         * description : Need tapers for a job in Basingstoke .Interested tapers should call this number 07956 684580.
         £160 before tax
         * created_at : 2019-01-31 07:22:08
         * published_at : null
         * revision :
         * conversation_count : 0
         * countInterest : 0
         */

        private int id;
        private String name;
        private String job_type;
        private String work_type;
        private String price;
        private String currency_type;
        private String longitude;
        private String latitude;
        private String user_id;
        private String worker;
        private String image_path;
        private String streetName;
        private String postcity;
        private int job_status;
        private String description;
        private String created_at;
        private Object published_at;
        private String revision;
        private int conversation_count;
        private String countInterest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob_type() {
            return job_type;
        }

        public void setJob_type(String job_type) {
            this.job_type = job_type;
        }

        public String getWork_type() {
            return work_type;
        }

        public void setWork_type(String work_type) {
            this.work_type = work_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCurrency_type() {
            return currency_type;
        }

        public void setCurrency_type(String currency_type) {
            this.currency_type = currency_type;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWorker() {
            return worker;
        }

        public void setWorker(String worker) {
            this.worker = worker;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getPostcity() {
            return postcity;
        }

        public void setPostcity(String postcity) {
            this.postcity = postcity;
        }

        public int getJob_status() {
            return job_status;
        }

        public void setJob_status(int job_status) {
            this.job_status = job_status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Object getPublished_at() {
            return published_at;
        }

        public void setPublished_at(Object published_at) {
            this.published_at = published_at;
        }

        public String getRevision() {
            return revision;
        }

        public void setRevision(String revision) {
            this.revision = revision;
        }

        public int getConversation_count() {
            return conversation_count;
        }

        public void setConversation_count(int conversation_count) {
            this.conversation_count = conversation_count;
        }

        public String getCountInterest() {
            return countInterest;
        }

        public void setCountInterest(String countInterest) {
            this.countInterest = countInterest;
        }
    }
}
