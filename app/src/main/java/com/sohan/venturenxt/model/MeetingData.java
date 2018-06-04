package com.sohan.venturenxt.model;

import java.util.ArrayList;

public class MeetingData {
    private String title;
    private String committee;
    private String address, summary;
    private String date;
    private Integer agenda_count;


    public MeetingData() {
    }

    public MeetingData(String title, String committee, String address,
                       String summary, String date, Integer agenda_count) {
        this.title = title;
        this.committee = committee;
        this.address = address;
        this.summary = summary;
        this.date = date;
        this.agenda_count = agenda_count;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAgenda_count() {
        return agenda_count;
    }

    public void setAgenda_count(Integer agenda_count) {
        this.agenda_count = agenda_count;
    }
}
