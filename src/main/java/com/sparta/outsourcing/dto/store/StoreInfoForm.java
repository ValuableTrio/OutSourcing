package com.sparta.outsourcing.dto.store;

import com.sparta.outsourcing.entity.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreInfoForm {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "가게 소개를 입력해주세요.")
    private String description;

    @NotBlank(message = "최소 주문 금액을 입력해주세요")
    private Long minPrice;

    public StoreInfoForm(   Store store) {
        this.name = store.getName();
        this.address = store.getAddress();
        this.phoneNumber = store.getPhone();
        this.description = store.getDescription();
        this.minPrice = store.getMinPrice();
    }
}
