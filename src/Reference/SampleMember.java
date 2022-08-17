package Reference;

import java.util.Objects;

public class SampleMember {
    private String id;
    private String name;
    private String bd;
    private int mileage;

    public SampleMember() {
    }

    public SampleMember(String id, String name, String bd, int mileage) {
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.mileage = mileage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SampleMember)) return false;
        SampleMember that = (SampleMember) o;
        return getMileage() == that.getMileage() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBd(), that.getBd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBd(), getMileage());
    }
}
