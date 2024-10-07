package br.inatel.dm110.api;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTO {

    private String productCode;
    private int amountStored;
    private int minimumAmount;
    private String location;
    private int enterDate;
}
