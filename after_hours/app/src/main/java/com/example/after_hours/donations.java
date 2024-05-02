package com.example.after_hours;

public class donations {
    String d_id,d_quantity,d_acceptdate;

    public String getDonor_id() {
        return "Donor ID: "+donor_id;
    }

    public void setDonor_id(String donor_id) {
        this.donor_id = donor_id;
    }

    public String getD_id() {
        return "Donation ID: "+d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_quantity() {
        return "Quantity :"+d_quantity;
    }

    public void setD_quantity(String d_quantity) {
        this.d_quantity = d_quantity;
    }

    public String getD_type() {
        return "Type of Food: "+d_type;
    }

    public void setD_type(String d_type) {
        this.d_type = d_type;
    }

    public String getD_deadline() {
        return "Deadline: "+d_deadline;
    }

    public void setD_deadline(String d_deadline) {
        this.d_deadline = d_deadline;
    }

    public String getD_submit() {
        return d_submit;
    }

    public void setD_submit(String d_submit) {
        this.d_submit = d_submit;
    }

    public String getD_desc() {
        return "Description: "+d_desc;
    }

    @Override
    public String toString() {
        return
                " Donation ID: " + d_id + '\n' +
                " Quantity: " + d_quantity + '\n' +
                " Donor Email: " + donor_id + '\n' +
                " Type of Donation: " + d_type + '\n' +
                " Deadline: " + d_deadline + '\n' +
                " Submission Date: " + d_submit + '\n' +
                " Description: " + d_desc + '\n'
                ;
    }
    public String tofeedbackString() {
        return
                " Donation ID: " + d_id + '\n' +
                        " Quantity: " + d_quantity + '\n' +
                        " Donor Email: " + donor_id + '\n' +
                        " Type of Donation: " + d_type + '\n' +
                        " Deadline: " + d_deadline + '\n' +
                        " Submission Date: " + d_submit + '\n' +
                        " Description: " + d_desc + '\n'+
                        " Accept Date: " + d_acceptdate + '\n'
                ;
    }
    public void setD_desc(String d_desc) {
        this.d_desc = d_desc;
    }

    String donor_id,d_type,d_deadline,d_submit,d_desc;

}
