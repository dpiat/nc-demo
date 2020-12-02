package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserSummary {
    private Long id;
    private String username;
   /* private List<OrderSummary> orders;*/
}
