package br.ufrn.imd.appointmentscheduler.ms.user.model;


import br.ufrn.imd.appointmentscheduler.ms.user.validation.CurrentPasswordMatches;
import br.ufrn.imd.appointmentscheduler.ms.user.validation.FieldsMatches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsMatches(field = "password", matchingField = "matchingPassword")
@CurrentPasswordMatches()
public class ChangePasswordForm {


    @NotNull
    private int id;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String password;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String matchingPassword;

    private String currentPassword;

    public ChangePasswordForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
