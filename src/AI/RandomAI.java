package AI;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;

import javax.swing.*;
import java.util.ArrayList;


public class RandomAI {


    ImageIcon BEIGE = new ImageIcon("../sources/Images/BEIGE.png");
    ImageIcon BLANC = new ImageIcon("../sources/Images/BLANC.png");
    ImageIcon BLEU = new ImageIcon("../sources/Images/BLEU.png");
    ImageIcon JAUNE = new ImageIcon("../sources/Images/JAUNE.png");
    ImageIcon NOIR = new ImageIcon("../sources/Images/NOIR.png");
    ImageIcon ROUGE = new ImageIcon("../sources/Images/ROUGE.png");
    ImageIcon VERT = new ImageIcon("../sources/Images/VERT.png");

    public RandomAI(Pyramide p2Pyramide, Table2D baseK3) {
        RandomPyramid(p2Pyramide, baseK3);
    }

    public void RandomPyramid(Pyramide pyramide, Table2D table2D){
        int colors[] = calculateColors(table2D);
        int index = 0;
        Pion pionSource = null;
        for(int i = 0; i<pyramide.getHight(); i++){
            for (int j=0 ; j<=i;j++){
                //liste pour les indices du couleur qui ne sont pas null
                ArrayList<Integer> listdescouleurnonnul = new ArrayList<>();
                for (int k = 0; k < colors.length; k++) {
                    if(colors[k] != 0)
                        listdescouleurnonnul.add(k);
                }
                    index = (int) (Math.random() * listdescouleurnonnul.size());
                colors[listdescouleurnonnul.get(index)]--;

                switch (listdescouleurnonnul.get(index)){
                    case 0 -> pionSource = new Pion(CouleurPion.ROUGE, Pion.TypePion.COLORED, ROUGE, i, j);
                    case 1 -> pionSource = new Pion(CouleurPion.BLEU, Pion.TypePion.COLORED, BLEU, i, j);
                    case 2 -> pionSource = new Pion(CouleurPion.VERT, Pion.TypePion.COLORED, VERT, i, j);
                    case 3 -> pionSource = new Pion(CouleurPion.JAUNE, Pion.TypePion.COLORED, JAUNE, i, j);
                    case 4 -> pionSource = new Pion(CouleurPion.NOIR, Pion.TypePion.COLORED, NOIR, i, j);
                    case 5 -> pionSource = new Pion(CouleurPion.BLANC, Pion.TypePion.COLORED, BLANC, i, j);
                    case 6 -> pionSource = new Pion(CouleurPion.BEIGE, Pion.TypePion.COLORED, BEIGE, i, j);
                }

                assert pionSource != null; //if pionSource is null, the program will stop
                pyramide.getPion(i,j).replacePion(pionSource);
            }
        }
    }


    public int[] calculateColors(Table2D table2D){
        //calculate the number of each color in the table
        int[] colors = new int[7];
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                if (table2D.getCases(i, j) != null) {
                    switch (table2D.getCases(i, j).getCouleur()) {
                        case ROUGE -> colors[0]++;
                        case BLEU -> colors[1]++;
                        case VERT -> colors[2]++;
                        case JAUNE -> colors[3]++;
                        case NOIR -> colors[4]++;
                        case BLANC -> colors[5]++;
                        case BEIGE -> colors[6]++;
                    }
                }
            }
        }
        return colors;
    }








}
