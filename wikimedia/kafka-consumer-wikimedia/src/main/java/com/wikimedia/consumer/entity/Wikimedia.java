package com.wikimedia.consumer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="wikimedia_recentchange")
@Getter
@Setter
public class Wikimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String wikievent;
}
