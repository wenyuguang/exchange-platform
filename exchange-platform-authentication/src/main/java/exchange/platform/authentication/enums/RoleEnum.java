package exchange.platform.authentication.enums;

/**
 * 
 * Description:
 * <p>Company: xinya </p>
 * <p>Date:2018年7月11日 上午11:56:34</p>
 * @author Tony
 * @version 1.0
 *
 */
public enum RoleEnum {
    ADMIN("ADMIN"),
    MEMBER("MEMBER");

    private String desc;

    public String desc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RoleEnum(String desc) {
        this.desc = desc;
    }
}
