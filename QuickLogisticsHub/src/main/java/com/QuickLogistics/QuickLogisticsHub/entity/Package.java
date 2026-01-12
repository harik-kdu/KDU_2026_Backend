package com.QuickLogistics.QuickLogisticsHub.entity;

// import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;
// import jakarta.persistence.Id;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import lombok.NoArgsConstructor;

@Getter
@Setter
public class Package {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String destination;
    private double weight;
    private String status;
    private String deliveryType;

    public Package(String id, String destination, double weight, String status, String deliveryType) {
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.status = status;
        this.deliveryType = deliveryType;
    }
}
