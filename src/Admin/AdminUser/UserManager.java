package Admin.AdminUser;

import java.util.ArrayList;

public class UserManager {
    
    private ArrayList<RegUser> users;
    private int numOfUsers;

    public UserManager() {
        this.users = new ArrayList<RegUser>();
        this.numOfUsers = 0;
    }

    public void loadUsers() {
        //TOdo load users from database
    }

    public ArrayList<RegUser> getUsers() {
        return users;
    }


}
