package Model;

import java.awt.*;

public enum CouleurPion {
    ROUGE,
    BLEU,
    VERT,
    JAUNE,
    NOIR,
    BLANC,
    BEIGE;

    public Color getColor() {
        switch (this) {
            case ROUGE:
                return Color.RED;
            case BLEU:
                return Color.BLUE;
            case VERT:
                return Color.GREEN;
            case JAUNE:
                return Color.YELLOW;
            case NOIR:
                return Color.BLACK;
            case BLANC:
                return Color.WHITE;
            case BEIGE:
                return Color.ORANGE;
            default:
                return Color.WHITE;
        }
    }
}