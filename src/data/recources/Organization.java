package data.recources;

public class Organization implements Serializable{
    private double annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null

    public Organization(double annualTurnover, OrganizationType type) {
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    public Organization(){

    }

    public double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "annualTurnover=" + annualTurnover +
                ", type=" + type +
                '}';
    }
}
