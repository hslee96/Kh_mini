import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SampleMember {
    private String name;
    private int age;
    private String address;
    private String phone;

    public SampleMember() {
    }

    public SampleMember(String name, int age, String address, String phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleMember that = (SampleMember) o;
        return getAge() == that.getAge() && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getAddress(), getPhone());
    }
}
