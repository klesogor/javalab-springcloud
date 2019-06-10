package it61.springlabs.data.dto.log;

import java.util.Date;

public class LogDto {
        private String data;
        private Date created;

        public LogDto(String entity, Date created) {
            data = entity;
            this.created = created;
        }

        public LogDto(){}

        public String getData() {
            return data;
        }

        public Date getCreated() {
            return created;
        }
}
