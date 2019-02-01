package com.app.drylining.model;

import java.util.List;

public class RecentToolModel {

    /**
     * status : success
     * count : 8
     * totalpages : 1
     * result : [{"id":"986","name":"iOS","tool_type":"New tool","price":"100","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"239","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/20190116111326_tool1.jpg","streetName":"test","postcity":"Ahmedabad","phone":"","status":"","description":"test","created_at":"","published_at":"","revision":""},{"id":"987","name":"sf","tool_type":"New tool","price":"31","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"239","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/20190116111419_tool3.jpg","streetName":"12","postcity":"12","phone":"","status":"","description":"21","created_at":"","published_at":"","revision":""},{"id":"982","name":"building","tool_type":"New tool","price":"60","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"51","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/2018121322322220180831_221734.jpg","streetName":"London","postcity":"London","phone":"","status":"","description":"buildings","created_at":"","published_at":"","revision":""},{"id":"977","name":"tyop","tool_type":"New tool","price":"532","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"15","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/20180320040006IMG_20180310_143735.jpg","streetName":"giod","postcity":"Toronto","phone":"","status":"","description":"goob","created_at":"","published_at":"","revision":""},{"id":"978","name":"gold good","tool_type":"New tool","price":"123","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"15","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/20180320110206IMG_20180302_150456.jpg","streetName":"Youth","postcity":"London","phone":"","status":"","description":"Good","created_at":"","published_at":"","revision":""},{"id":"979","name":"GoodKnife","tool_type":"New tool","price":"126","currency_type":"EUR","longitude":"0","latitude":"0","user_id":"14","user_type":"R","image_path":"http://192.168.1.200/dryliningapp/images/20180320143009IMG_20180302_150456.jpg","streetName":"Youth street","postcity":"London","phone":"","status":"","description":"Good tools","created_at":"","published_at":"","revision":""}]
     * notifications : 0
     * userInfo : {"id":"239","name":"demo65","email":"demo65@gmail.com","phone":"+919898911674","password":"Keyur@123","created_at":"2019-01-14 13:43:58","updated_at":"2019-01-14 13:43:58","type":"R","shareProfile":"1","otp_code":"","firebase_id":"-1","is_admin":"0"}
     */

    private String status;
    private int count;
    private int totalpages;
    private int notifications;
    private UserInfoBean userInfo;
    private List<ResultBean> result;

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

    public static class UserInfoBean {
        /**
         * id : 239
         * name : demo65
         * email : demo65@gmail.com
         * phone : +919898911674
         * password : Keyur@123
         * created_at : 2019-01-14 13:43:58
         * updated_at : 2019-01-14 13:43:58
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
         * id : 986
         * name : iOS
         * tool_type : New tool
         * price : 100
         * currency_type : Pound
         * longitude : 0
         * latitude : 0
         * user_id : 239
         * user_type : R
         * image_path : http://192.168.1.200/dryliningapp/images/20190116111326_tool1.jpg
         * streetName : test
         * postcity : Ahmedabad
         * phone :
         * status :
         * description : test
         * created_at :
         * published_at :
         * revision :
         */

        private int id;
        private String name;
        private String tool_type;
        private String price;
        private String currency_type;
        private String longitude;
        private String latitude;
        private String user_id;
        private String user_type;
        private String image_path;
        private String streetName;
        private String postcity;
        private String phone;
        private String status;
        private String description;
        private String created_at;
        private String published_at;
        private String revision;

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

        public String getTool_type() {
            return tool_type;
        }

        public void setTool_type(String tool_type) {
            this.tool_type = tool_type;
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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getRevision() {
            return revision;
        }

        public void setRevision(String revision) {
            this.revision = revision;
        }
    }
}
