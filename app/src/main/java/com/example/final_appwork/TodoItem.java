package com.example.final_appwork;

public class TodoItem {

        private int id;
        private String state;
        private String body;

        public TodoItem() {
            super();
            state = "";
            body = "";
        }

        public TodoItem(String state, String body) {
            super();
            this.state=state;
            this.body = body;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

}
