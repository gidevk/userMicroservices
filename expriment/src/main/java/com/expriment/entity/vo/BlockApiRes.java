package com.expriment.entity.vo;

import java.io.Serializable;

/*
 * @author Indradev.kuamr
 */
public class BlockApiRes implements Serializable {

    private static final long serialVersionUID = 6219698748975133430L;
    private String status;
    private String system_x_reference_number;
    private String available_limit_amount;
    private String block_status;
    private String cycle_day;
    private String message;
    private String description;
    private RequestObject request_object;

    // Constructors, getters, setters, and other methods can be added here.


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSystem_x_reference_number() {
        return system_x_reference_number;
    }

    public void setSystem_x_reference_number(String system_x_reference_number) {
        this.system_x_reference_number = system_x_reference_number;
    }

    public String getAvailable_limit_amount() {
        return available_limit_amount;
    }

    public void setAvailable_limit_amount(String available_limit_amount) {
        this.available_limit_amount = available_limit_amount;
    }

    public String getBlock_status() {
        return block_status;
    }

    public void setBlock_status(String block_status) {
        this.block_status = block_status;
    }

    public String getCycle_day() {
        return cycle_day;
    }

    public void setCycle_day(String cycle_day) {
        this.cycle_day = cycle_day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestObject getRequest_object() {
        return request_object;
    }

    public void setRequest_object(RequestObject request_object) {
        this.request_object = request_object;
    }

    public static class RequestObject {
        private String source_system;
        private String unique_identifier;
        private String customer_name;
        private String account_holder_name;
        private String customer_type;

        // Constructors, getters, setters, and other methods can be added here.

        public String getSource_system() {
            return source_system;
        }

        public void setSource_system(String source_system) {
            this.source_system = source_system;
        }

        public String getUnique_identifier() {
            return unique_identifier;
        }

        public void setUnique_identifier(String unique_identifier) {
            this.unique_identifier = unique_identifier;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getAccount_holder_name() {
            return account_holder_name;
        }

        public void setAccount_holder_name(String account_holder_name) {
            this.account_holder_name = account_holder_name;
        }

        public String getCustomer_type() {
            return customer_type;
        }

        public void setCustomer_type(String customer_type) {
            this.customer_type = customer_type;
        }
    }
}
