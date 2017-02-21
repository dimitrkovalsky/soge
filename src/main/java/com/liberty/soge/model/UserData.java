package com.liberty.soge.model;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 15.02.2017.
 */
@Data
public class UserData {
    private String userId;
    private String pictureUrl;
    private Long xp;
    private Integer level;
}
