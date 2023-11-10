package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.Product} entity.
 */
public class ProductDTO implements Serializable {

    @NotNull
    private Long productId;

    private String name;

    private Double price;

    private Double quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.productId == null) {
            return false;
        }
        return Objects.equals(this.productId, productDTO.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "productId=" + getProductId() +
            ", name='" + getName() + "'" +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
