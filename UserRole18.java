public enum UserRole18 {
    ADMIN {
        @Override
        public String getPermissions() {
            return "所有权限：创建、读取、更新、删除、系统配置等";
        }
    },
    USER {
        @Override
        public String getPermissions() {
            return "普通权限：读取、创建、更新（受限），无法删除关键数据";
        }
    },
    GUEST {
        @Override
        public String getPermissions() {
            return "访客权限：仅读取公开信息，不能进行修改操作";
        }
    };
    public abstract String getPermissions();
    public static void main(String[] args) {
        for (UserRole18 role : UserRole18.values()) {
            System.out.println(role.name() + " 权限 -> " + role.getPermissions());
        }
    }
}