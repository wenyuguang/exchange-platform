package exchange.platform.authentication.domain;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月9日 下午4:19:11</p>
 * @author Tony
 * @version 1.0
 *
 */
public class UserRole {

    private String roleName;


    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String authority() {
        return this.getRoleName();
    }
}
