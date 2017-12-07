package Utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/25.
 */

public class JsonBean {
    private String status;
    private Paramz paramz;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paramz getParamz() {
        return paramz;
    }

    public void setParamz(Paramz paramz) {
        this.paramz = paramz;
    }

    public class Paramz{
        private ArrayList<Feeds>feeds;

        public ArrayList<Feeds> getFeeds() {
            return feeds;
        }

        public void setFeeds(ArrayList<Feeds> feeds) {
            this.feeds = feeds;
        }

        public class Feeds{
            private Data data;

            public Data getData() {
                return data;
            }

            public void setData(Data data) {
                this.data = data;
            }

            public class Data{
                private String subject;
                private String summary;
                private String cover;
                private String changed;

                public String getSubject() {
                    return subject;
                }

                public void setSubject(String subject) {
                    this.subject = subject;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getChanged() {
                    return changed;
                }

                public void setChanged(String changed) {
                    this.changed = changed;
                }
            }
        }
    }
}
