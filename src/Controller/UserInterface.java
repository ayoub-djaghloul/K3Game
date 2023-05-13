package Controller;

import Model.K3;

public interface UserInterface {
    void displayGameInstructions();
    void displayGameBoard(K3 k3);
    void displayErrorMessage(String message);
    void displayGameResult(Joueur gagnant);
    int[] getMoveFromUser();
}