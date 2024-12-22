package socks.service.model;

public class Socks {

    private Long id;

    /**
     * The RGB color for socks in hexadecimal notation, which is written as string
     */
    private String color;

    /**
     * The percentage of cotton written as an integer from 0 to 100
     */
    private Integer cotton;

    /**
     * Amount of socks available in storage
     */
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCotton() {
        return cotton;
    }

    public void setCotton(Integer cotton) {
        this.cotton = cotton;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Socks(Long id, String color, Integer cotton, Long amount) {
        this.id = id;
        this.color = color;
        this.cotton = cotton;
        this.amount = amount;
    }

    public Socks(String color, Integer cotton, Long amount) {
        this.color = color;
        this.cotton = cotton;
        this.amount = amount;
    }

    public Socks() {
    }
}
