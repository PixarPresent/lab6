package data.recources;

import server.managers.generators.IdGenerator;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Worker implements Comparable<Worker>, Serializable{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long salary; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.LocalDate startDate; //Поле не может быть null
    private java.time.ZonedDateTime endDate; //Поле может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле может быть null

    public Worker(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, Long salary, LocalDate startDate, ZonedDateTime endDate, Status status, Organization organization) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.organization = organization;
    }
    public Worker(){
        this.id = IdGenerator.generateId();
        this.creationDate = ZonedDateTime.now();
    }

    public String toCSV() {
        return id +
                ";" + name +
                ";" + coordinates.getX() +
                ";" + coordinates.getY() +
                ";" + creationDate.toLocalDate() +
                ";" + salary +
                ";" + startDate +
                ";" + LocalDate.of(endDate.getYear(), endDate.getMonth().getValue(), endDate.getDayOfMonth()) +
                ";" + status +
                ";" + organization.getAnnualTurnover() +
                ";" + organization.getType() + ";";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", organization=" + organization +
                '}';
    }

    @Override
    public int compareTo(Worker o) {
        return this.id - o.getId();
    }
}
