package pivotalsoft.com.JobSuchi.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 2/19/2018.
 */

public class RankItem {

    /**
     * alldata : [{"rankid":"1","course":"CSE","OC-Boys":"2000","OC-Girls":"3000","BCA-Boys":"4000","BCA-Girls":"5000","BCB-Boys":"6000","BCB-Girls":"7000","BCC-Boys":"8000","BCC-Girls":"9000","BCD-Boys":"10000","BCD-Girls":"11000","SC-Boys":"12000","SC-Girls":"13000","ST-Boys":"14000","ST-Girls":"15000","collegeid":"1"},{"rankid":"2","course":"ECE","OC-Boys":"1300","OC-Girls":"2200","BCA-Boys":"3300","BCA-Girls":"4400","BCB-Boys":"5560","BCB-Girls":"6690","BCC-Boys":"9000","BCC-Girls":"11000","BCD-Boys":"12900","BCD-Girls":"14900","SC-Boys":"17000","SC-Girls":"18000","ST-Boys":"19000","ST-Girls":"19800","collegeid":"1"}]
     * message : success
     */

    private String message;
    private List<AlldataBean> alldata;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AlldataBean> getAlldata() {
        return alldata;
    }

    public void setAlldata(List<AlldataBean> alldata) {
        this.alldata = alldata;
    }

    public static class AlldataBean {
        /**
         * rankid : 1
         * course : CSE
         * OC-Boys : 2000
         * OC-Girls : 3000
         * BCA-Boys : 4000
         * BCA-Girls : 5000
         * BCB-Boys : 6000
         * BCB-Girls : 7000
         * BCC-Boys : 8000
         * BCC-Girls : 9000
         * BCD-Boys : 10000
         * BCD-Girls : 11000
         * SC-Boys : 12000
         * SC-Girls : 13000
         * ST-Boys : 14000
         * ST-Girls : 15000
         * collegeid : 1
         */

        private String rankid;
        private String course;
        @SerializedName("OC-Boys")
        private String OCBoys;
        @SerializedName("OC-Girls")
        private String OCGirls;
        @SerializedName("BCA-Boys")
        private String BCABoys;
        @SerializedName("BCA-Girls")
        private String BCAGirls;
        @SerializedName("BCB-Boys")
        private String BCBBoys;
        @SerializedName("BCB-Girls")
        private String BCBGirls;
        @SerializedName("BCC-Boys")
        private String BCCBoys;
        @SerializedName("BCC-Girls")
        private String BCCGirls;
        @SerializedName("BCD-Boys")
        private String BCDBoys;
        @SerializedName("BCD-Girls")
        private String BCDGirls;
        @SerializedName("SC-Boys")
        private String SCBoys;
        @SerializedName("SC-Girls")
        private String SCGirls;
        @SerializedName("ST-Boys")
        private String STBoys;
        @SerializedName("ST-Girls")
        private String STGirls;
        private String collegeid;

        public String getRankid() {
            return rankid;
        }

        public void setRankid(String rankid) {
            this.rankid = rankid;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getOCBoys() {
            return OCBoys;
        }

        public void setOCBoys(String OCBoys) {
            this.OCBoys = OCBoys;
        }

        public String getOCGirls() {
            return OCGirls;
        }

        public void setOCGirls(String OCGirls) {
            this.OCGirls = OCGirls;
        }

        public String getBCABoys() {
            return BCABoys;
        }

        public void setBCABoys(String BCABoys) {
            this.BCABoys = BCABoys;
        }

        public String getBCAGirls() {
            return BCAGirls;
        }

        public void setBCAGirls(String BCAGirls) {
            this.BCAGirls = BCAGirls;
        }

        public String getBCBBoys() {
            return BCBBoys;
        }

        public void setBCBBoys(String BCBBoys) {
            this.BCBBoys = BCBBoys;
        }

        public String getBCBGirls() {
            return BCBGirls;
        }

        public void setBCBGirls(String BCBGirls) {
            this.BCBGirls = BCBGirls;
        }

        public String getBCCBoys() {
            return BCCBoys;
        }

        public void setBCCBoys(String BCCBoys) {
            this.BCCBoys = BCCBoys;
        }

        public String getBCCGirls() {
            return BCCGirls;
        }

        public void setBCCGirls(String BCCGirls) {
            this.BCCGirls = BCCGirls;
        }

        public String getBCDBoys() {
            return BCDBoys;
        }

        public void setBCDBoys(String BCDBoys) {
            this.BCDBoys = BCDBoys;
        }

        public String getBCDGirls() {
            return BCDGirls;
        }

        public void setBCDGirls(String BCDGirls) {
            this.BCDGirls = BCDGirls;
        }

        public String getSCBoys() {
            return SCBoys;
        }

        public void setSCBoys(String SCBoys) {
            this.SCBoys = SCBoys;
        }

        public String getSCGirls() {
            return SCGirls;
        }

        public void setSCGirls(String SCGirls) {
            this.SCGirls = SCGirls;
        }

        public String getSTBoys() {
            return STBoys;
        }

        public void setSTBoys(String STBoys) {
            this.STBoys = STBoys;
        }

        public String getSTGirls() {
            return STGirls;
        }

        public void setSTGirls(String STGirls) {
            this.STGirls = STGirls;
        }

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }
    }
}
