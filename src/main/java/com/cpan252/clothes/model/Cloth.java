package com.cpan252.clothes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table
public class Cloth {
    @Id
    private Long id;
    @NotBlank
    private String name;
    @Min(2021)
    private int yearcreated;
    @DecimalMin(value = "1000", inclusive = true)
    private BigDecimal price;
    private Brand brand;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Brand {
        DIOR("Dior"), CHANEL("Chanel"), LOUIS_VUITTON("Louis Vuitton"), GUCCI("Gucci");

        private String title;

        private Brand(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
