package com.design.patterns.builder;

import lombok.Getter;

@Getter
public class Customer {
    private String name;
    private String company;
    private boolean hasCar;
    private boolean hasBike;

    private Customer(CustomerBuilder customerBuilder) {
        this.name = customerBuilder.name;
        this.company = customerBuilder.company;
        this.hasCar = customerBuilder.hasCar;
        this.hasBike = customerBuilder.hasBike;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", hasCar=" + hasCar +
                ", hasBike=" + hasBike +
                '}';
    }

    public static class CustomerBuilder {
        private String name;
        private String company;
        private boolean hasCar;
        private boolean hasBike;

        public CustomerBuilder(String name, String company) {
            this.name = name;
            this.company = company;
        }

        public CustomerBuilder setHasCar(boolean hasCar) {
            this.hasCar = hasCar;
            return this;
        }

        public CustomerBuilder setHasBike(boolean hasBike) {
            this.hasBike = hasBike;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
