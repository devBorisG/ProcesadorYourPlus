package com.uco.yourplus.crosscuttingyourplus.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "yourplus.management.laboratorio.response")
@Component
public class LaboratorioPropertiesCatalogProducer {

    private String exchange;
    private QueueProperties queue = new QueueProperties();
    private RoutingKeyProperties routingKey = new RoutingKeyProperties();

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public QueueProperties getQueue() {
        return queue;
    }

    public void setQueue(QueueProperties queue) {
        this.queue = queue;
    }

    public RoutingKeyProperties getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(RoutingKeyProperties routingKey) {
        this.routingKey = routingKey;
    }

    public static class QueueProperties{
        private String save;
        private String delete;
        private String update;
        private String list;

        public String getSave() {
            return save;
        }

        public void setSave(String save) {
            this.save = save;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }

    public static class RoutingKeyProperties{
        private String save;
        private String delete;
        private String update;
        private String list;

        public String getSave() {
            return save;
        }

        public void setSave(String save) {
            this.save = save;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
