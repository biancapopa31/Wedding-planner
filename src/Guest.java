public class Guest extends Person{
    private InviteStatus inviteStatus;
    private String side; //bride or groom
    private Role role;
    private Relationship relationship;
    
    private enum InviteStatus {
        ATTENDING,
        NOT_ATTENDING,
        SENT,
        NOT_SENT
    }

    private enum Relationship {
        FAMILY, 
        FRIEND,
        OTHER
    }

    public enum Role {
        MAID_OF_HONOR,
        BEST_MAN,
        NONE
    }

    public Guest(String lastName, String firstName, String telephone, InviteStatus inviteStatus, String side, Role role, Relationship relationship) {
        super(lastName, firstName, telephone);
        this.inviteStatus = inviteStatus;
        this.side = side;
        this.role = role;
        this.relationship = relationship;
    }

    public Guest(String lastName, String firstName, InviteStatus inviteStatus, String side, Role role, Relationship relationship) {
        super(lastName, firstName);
        this.inviteStatus = inviteStatus;
        this.side = side;
        this.role = role;
        this.relationship = relationship;
    }

    public InviteStatus getInviteStatus() {
        return inviteStatus;
    }

    public String getSide() {
        return side;
    }

    public Role getRole() {
        return role;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setInviteStatus(InviteStatus inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return super.toString() + " " + inviteStatus + " " + side + " " + role + " " + relationship;
    }

}
