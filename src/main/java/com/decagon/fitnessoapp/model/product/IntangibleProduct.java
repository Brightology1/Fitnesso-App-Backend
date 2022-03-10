package com.decagon.fitnessoapp.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class IntangibleProduct extends Product{

    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @Column(name = "sessions_per_week", nullable = false)
    private Integer monthlySubscription;



}
