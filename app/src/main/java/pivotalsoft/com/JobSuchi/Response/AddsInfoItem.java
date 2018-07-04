package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 1/9/2018.
 */

public class AddsInfoItem {


    /**
     * adsdata : [{"adid":"1","adpic":"asn-engg.jpg","collegeid":"14"}]
     * message : success
     */

    private String message;
    private List<AdsdataBean> adsdata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AdsdataBean> getAdsdata() {
        return adsdata;
    }

    public void setAdsdata(List<AdsdataBean> adsdata) {
        this.adsdata = adsdata;
    }

    public static class AdsdataBean {
        /**
         * adid : 1
         * adpic : asn-engg.jpg
         * collegeid : 14
         */

        private String adid;
        private String adpic;
        private String collegeid;

        public String getAdid() {
            return adid;
        }

        public void setAdid(String adid) {
            this.adid = adid;
        }

        public String getAdpic() {
            return adpic;
        }

        public void setAdpic(String adpic) {
            this.adpic = adpic;
        }

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }
    }
}
