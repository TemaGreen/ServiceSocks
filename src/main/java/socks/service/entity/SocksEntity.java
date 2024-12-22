package socks.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "socks")
public class SocksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "socks_seq")
    @SequenceGenerator(name = "socks_seq", allocationSize = 1)
    private Long id;

    @Column
    private Integer color;

    @Column
    private Integer cotton;

    @Column
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
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

    public SocksEntity(Long id, Integer color, Integer cotton, Long amount) {
        this.id = id;
        this.color = color;
        this.cotton = cotton;
        this.amount = amount;
    }

    public SocksEntity(Integer color, Integer cotton, Long amount) {
        this.color = color;
        this.cotton = cotton;
        this.amount = amount;
    }

    public SocksEntity() {
    }
}
