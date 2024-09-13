package com.example.tennis_scoreboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Matches", indexes = {@Index(name = "player1_Index", columnList = "player1"),
                                    @Index(name = "player2_Index", columnList = "player2")})
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id", nullable = false)
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

}
