package com.feerlaroc.zoho.entity;

/**
 * Created by root on 2016/12/07.
 */

public class InvoiceBean {

    private String invoice_id;
    private String customer_name;
    private String customer_id;
    private String status;
    private String invoice_number;
    private String reference_number;
    private String date;
    private String due_date;
    private String due_days;
    private String currency_id;
    private String currency_code;
    private String total;
    private String balance;
    private String created_time;
    private String is_emailed;
    private String reminders_sent;
    private String payment_expected_date;
    private String last_payment_date;

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDue_days() {
        return due_days;
    }

    public void setDue_days(String due_days) {
        this.due_days = due_days;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getIs_emailed() {
        return is_emailed;
    }

    public void setIs_emailed(String is_emailed) {
        this.is_emailed = is_emailed;
    }

    public String getReminders_sent() {
        return reminders_sent;
    }

    public void setReminders_sent(String reminders_sent) {
        this.reminders_sent = reminders_sent;
    }

    public String getPayment_expected_date() {
        return payment_expected_date;
    }

    public void setPayment_expected_date(String payment_expected_date) {
        this.payment_expected_date = payment_expected_date;
    }

    public String getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(String last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    @Override
    public String toString() {
        return "InvoiceBean{" +
                "invoice_id='" + invoice_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", status='" + status + '\'' +
                ", invoice_number='" + invoice_number + '\'' +
                ", reference_number='" + reference_number + '\'' +
                ", date='" + date + '\'' +
                ", due_date='" + due_date + '\'' +
                ", due_days='" + due_days + '\'' +
                ", currency_id='" + currency_id + '\'' +
                ", currency_code='" + currency_code + '\'' +
                ", total='" + total + '\'' +
                ", balance='" + balance + '\'' +
                ", created_time='" + created_time + '\'' +
                ", is_emailed='" + is_emailed + '\'' +
                ", reminders_sent='" + reminders_sent + '\'' +
                ", payment_expected_date='" + payment_expected_date + '\'' +
                ", last_payment_date='" + last_payment_date + '\'' +
                '}';
    }
}
