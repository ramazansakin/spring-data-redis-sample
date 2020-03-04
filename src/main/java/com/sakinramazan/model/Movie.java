package com.sakinramazan.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Movie implements Serializable {

    private String id;
    private String name;

}
