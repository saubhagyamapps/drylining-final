package com.app.drylining.model;

import java.util.List;

public class RecentlyAddedJobModel  {

    /**
     * status : success
     * count : 26
     * totalpages : 4.333333333333333
     * result : [{"id":"1007","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","image_path":"","postcity":"Ahmedabad","streetName":"Commerce Six Rd","status":"","created_at":"2019-01-31 07:22:08","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"},{"id":"1006","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","image_path":"","postcity":"Ahmedabad","streetName":"Commerce Six Rd","status":"","created_at":"2019-01-31 07:21:09","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"},{"id":"1005","name":"& % $# @ £ \u20ac\n","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","image_path":"","postcity":"Ahmedabad","streetName":"Commerce Six Rd","status":"","created_at":"2019-01-29 14:28:59","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"},{"id":"1004","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","image_path":"","postcity":"Ahmedabad","streetName":"Commerce Six Rd","status":"","created_at":"2019-01-29 10:39:23","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"},{"id":"1003","name":"new job Testing 2","job_type":"Boarding","work_type":"Day work","price":"12212","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"156","image_path":"","postcity":"Ahmedabad","streetName":"Commerce Six Rd","status":"","created_at":"2019-01-29 10:19:56","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"},{"id":"1002","name":"Basingstoke job","job_type":"Jointing","work_type":"Day work","price":"160","currency_type":"Pound","longitude":"0","latitude":"0","user_id":"51","image_path":"","postcity":"Basingstoke","streetName":"Basingstoke","status":"","created_at":"2019-01-28 20:31:15","published_at":null,"revision":"","conversation_count":"0","my_state":"progress"}]
     * notifications : 80
     */

    private String status;
    private int count;
    private int totalpages;
    private int notifications;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
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
         * image_path :
         * postcity : Ahmedabad
         * streetName : Commerce Six Rd
         * status :
         * created_at : 2019-01-31 07:22:08
         * published_at : null
         * revision :
         * conversation_count : 0
         * my_state : progress
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
        private String image_path;
        private String postcity;
        private String streetName;
        private String status;
        private String created_at;
        private Object published_at;
        private String revision;
        private int conversation_count;
        private String my_state;

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

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public String getPostcity() {
            return postcity;
        }

        public void setPostcity(String postcity) {
            this.postcity = postcity;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getMy_state() {
            return my_state;
        }

        public void setMy_state(String my_state) {
            this.my_state = my_state;
        }
    }
}
