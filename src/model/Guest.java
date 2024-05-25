package model;

import enums.InviteStatus;
import enums.Relationship;
import enums.Side;
import enums.Role;

  

public class Guest extends Person{
    private InviteStatus inviteStatus;
    private Side side; 
    private Role role;
    private Relationship relationship;     

    public Guest(String lastName, String firstName, String telephone, InviteStatus inviteStatus, Side side, Role role, Relationship relationship) {
        super(lastName, firstName, telephone);
        this.inviteStatus = inviteStatus;
        this.side = side;
        this.role = role;
        this.relationship = relationship;
    }

    public Guest(String lastName, String firstName, String telephone, int inviteStatus, int side, int role, int relationship) {
        super(lastName, firstName, telephone);
        this.inviteStatus = InviteStatus.values()[inviteStatus];
        this.side = Side.values()[side];
        this.role = Role.values()[role];
        this.relationship = Relationship.values()[relationship];
    }

    public Guest(Person person, int inviteStatus, int side, int role, int relationship) {
        super(person);
        this.inviteStatus = InviteStatus.values()[inviteStatus];
        this.side = Side.values()[side];
        this.role = Role.values()[role];
        this.relationship = Relationship.values()[relationship];
    }

    public Guest(Person person){
        super(person);
    }

    public Guest(){
        super();
    }

    public InviteStatus getInviteStatus() {
        return inviteStatus;
    }

    public Side getSide() {
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

    public void setInviteStatus(int inviteStatus) {
        this.inviteStatus = InviteStatus.values()[inviteStatus];
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = InviteStatus.valueOf(inviteStatus);
    }

    public void setSide(Side side) {
        this.side = side;
    }
    public void setSide(int side) {
        this.side = Side.values()[side];
    }

    public void setSide(String side) {
        this.side = Side.valueOf(side);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(int role) {
        this.role = Role.values()[role];
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = Relationship.values()[relationship];
    }

    public void setRelationship(String relationship) {
        this.relationship = Relationship.valueOf(relationship);
    }

    @Override
    public String toString() {
        return super.toString() + "\nStatus: " + inviteStatus + " Side: " + side + " Role: " + role + " Relationship: " + relationship;
    }

}
