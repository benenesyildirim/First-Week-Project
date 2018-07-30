package com.example.enesyildirim.firstweeksproject.model;

import com.example.enesyildirim.firstweeksproject.model.Items;

import java.util.List;

public class ResponseModel//Bu ne işe yaradı şimdi?
{
    private long total_count;
    private boolean incomplete_results;
    private List<Items> items;

    //Total Count
    public Long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Long total_count) {
        this.total_count = total_count;
    }

    //Incomplete Results
    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
