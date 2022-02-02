package spring.model.entity;

public enum Role {
    GUEST,
    USER,
    ADMIN,
    MODERATOR;

    public String getAuthority(){
        return this.toString();
    }
}
