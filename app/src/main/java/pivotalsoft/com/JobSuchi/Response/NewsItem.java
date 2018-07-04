package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/12/2018.
 */

public class NewsItem {

    /**
     * newsdata : [{"newsid":"1","title":"EAMCET 2018 Awareness Week","description":"Chaitanya Institute is conducting awareness week on EAMCET preparation, competition etc. Parents are invited to avail this opportunity.","imageurl":"chai-eamcet-18.jpg","postedon":"2018-02-16"}]
     * message : success
     */

    private String message;
    private List<NewsdataBean> newsdata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NewsdataBean> getNewsdata() {
        return newsdata;
    }

    public void setNewsdata(List<NewsdataBean> newsdata) {
        this.newsdata = newsdata;
    }

    public static class NewsdataBean {
        /**
         * newsid : 1
         * title : EAMCET 2018 Awareness Week
         * description : Chaitanya Institute is conducting awareness week on EAMCET preparation, competition etc. Parents are invited to avail this opportunity.
         * imageurl : chai-eamcet-18.jpg
         * postedon : 2018-02-16
         */

        private String newsid;
        private String title;
        private String description;
        private String imageurl;
        private String postedon;

        public String getNewsid() {
            return newsid;
        }

        public void setNewsid(String newsid) {
            this.newsid = newsid;
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

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getPostedon() {
            return postedon;
        }

        public void setPostedon(String postedon) {
            this.postedon = postedon;
        }
    }
}
