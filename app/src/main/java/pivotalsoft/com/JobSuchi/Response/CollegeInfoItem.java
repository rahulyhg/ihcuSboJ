package pivotalsoft.com.JobSuchi.Response;

import java.util.List;

/**
 * Created by USER on 2/22/2018.
 */

public class CollegeInfoItem {


    /**
     * alldata : [{"collegeid":"1","collegecode":"ASNW","collegename":"ASN Womens Engineering College","address":"Tenali, Burripalem Road","district":"Guntur","state":"Andhra Pradesh","latitude":"16.243198","longitude":"80.648494","gender":"Women","affiliatedto":"JNTU","region":"AU","others":"","startedyear":"1990","collegepic":"asn-eng.jpg","telephone":"08644-645464","email":"asn_tenali@gmail.com","website":"www.asnwomensengg.com"}]
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
         * collegeid : 1
         * collegecode : ASNW
         * collegename : ASN Womens Engineering College
         * address : Tenali, Burripalem Road
         * district : Guntur
         * state : Andhra Pradesh
         * latitude : 16.243198
         * longitude : 80.648494
         * gender : Women
         * affiliatedto : JNTU
         * region : AU
         * others :
         * startedyear : 1990
         * collegepic : asn-eng.jpg
         * telephone : 08644-645464
         * email : asn_tenali@gmail.com
         * website : www.asnwomensengg.com
         */

        private String collegeid;
        private String collegecode;
        private String collegename;
        private String address;
        private String district;
        private String state;
        private String latitude;
        private String longitude;
        private String gender;
        private String affiliatedto;
        private String region;
        private String others;
        private String startedyear;
        private String collegepic;
        private String telephone;
        private String email;
        private String website;

        public String getCollegeid() {
            return collegeid;
        }

        public void setCollegeid(String collegeid) {
            this.collegeid = collegeid;
        }

        public String getCollegecode() {
            return collegecode;
        }

        public void setCollegecode(String collegecode) {
            this.collegecode = collegecode;
        }

        public String getCollegename() {
            return collegename;
        }

        public void setCollegename(String collegename) {
            this.collegename = collegename;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAffiliatedto() {
            return affiliatedto;
        }

        public void setAffiliatedto(String affiliatedto) {
            this.affiliatedto = affiliatedto;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getOthers() {
            return others;
        }

        public void setOthers(String others) {
            this.others = others;
        }

        public String getStartedyear() {
            return startedyear;
        }

        public void setStartedyear(String startedyear) {
            this.startedyear = startedyear;
        }

        public String getCollegepic() {
            return collegepic;
        }

        public void setCollegepic(String collegepic) {
            this.collegepic = collegepic;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }
    }
}
