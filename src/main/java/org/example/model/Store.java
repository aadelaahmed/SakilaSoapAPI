package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "store")
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED not null")
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaff;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @OneToMany(mappedBy = "store")
    private Set<Inventory> inventories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Staff> staff = new LinkedHashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Customer> customers = new LinkedHashSet<>();

}