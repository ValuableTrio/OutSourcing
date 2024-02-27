package com.sparta.outsourcing.dto.menu;

import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class MenuInfoForm {

    private Long id;
    private Long storeId;
    private String name;
    private Long price;
    private String description;

    public MenuInfoForm(Menu menu) {
        this.id = menu.getId();
        this.storeId = menu.getStore().getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
    }
}
