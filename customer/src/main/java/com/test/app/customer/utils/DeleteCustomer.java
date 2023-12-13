package com.test.app.customer.utils;

public class DeleteCustomer {
    private boolean delteStatus;
    private String deleteReason;

    public boolean isDelteStatus() {
        return delteStatus;
    }

    public void setDelteStatus(boolean delteStatus) {
        this.delteStatus = delteStatus;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}
