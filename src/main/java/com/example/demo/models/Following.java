package com.example.demo.models;

/**
 * Created by JC on 7/22/2017.
 */
public class Following {

    private Long followingId;
    private long userid;
    private long personid;


    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public Long getFollowingId(){
        return followingId;
    }
    public void setFollowingId(long followingId){
        this.followingId=followingId;
}

}
