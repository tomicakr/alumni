package hr.alumni.model.details;

import java.util.List;

import org.springframework.stereotype.Component;

import hr.alumni.model.PostCategory;
import hr.alumni.model.User;

@Component
public class UserDetailsMore extends UserDetailsBasic{


    private String address;
    private String phone;
    private String graduation;
    private String birthday;
    private List<PostCategory> subscriptions;

    public UserDetailsMore(){
        super();
    }

    public UserDetailsMore(User user) {
        super(user);

        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.graduation = user.getGraduation().toString();
        this.birthday = user.getBirthday().toString();
        this.subscriptions = user.getSubscriptions();
    }
   
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<PostCategory> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<PostCategory> subscriptions) {
        this.subscriptions = subscriptions;
    }
}