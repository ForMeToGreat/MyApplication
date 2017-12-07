package Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/20.
 */

public class StroreDetialMessageBean {
    private String status;
    private String info;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info

                = info;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private Long id;
        private Long user_id;
        private String title;
        private String description;
        private String price;
        private String contact;
        private String head;
        private String mobile;
        private String qq;
        private ArrayList<photos> list;
        private String issue_time;
        private boolean followed;
        private boolean owned;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id

                    = id;
        }

        public Long getUser_id() {
            return user_id;
        }

        public void setUser_id(Long user_id) {
            this.user_id = user_id;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public boolean isOwned() {
            return owned;
        }

        public void setOwned(boolean owned) {
            this.owned = owned;
        }

        public String getIssue_time() {
            return issue_time;
        }

        public void setIssue_time(String issue_time) {
            this.issue_time = issue_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public ArrayList<photos> getList() {
            return list;
        }

        public void setList(ArrayList<photos> list) {
            this.list = list;
        }

        public class photos{
            private String str;

            public String getStrtr() {
                return str;
            }

            public void setStr(String str) {
                this.str = str;
            }
        }
    }
}
