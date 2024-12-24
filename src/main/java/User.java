public class User {
    private String name;
    private String password;
    private String email;
    private boolean sortable;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
        this.sortable = builder.sortable;
    }

    public static class UserBuilder {
        private String name;
        private String password;
        private String email;
        private boolean sortable;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setSortable(boolean sortable) {
            this.sortable = sortable;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


}
