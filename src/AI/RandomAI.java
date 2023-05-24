package AI;

import Model.CouleurPion;
import Model.Pion;
import Model.Pyramide;
import Model.Table2D;

import javax.swing.*;
import java.util.ArrayList;


public class RandomAI {


    ImageIcon BEIGE = new ImageIcon("sources/Images/BEIGE.png");
    ImageIcon BLANC = new ImageIcon("sources/Images/BLANC.png");
    ImageIcon BLEU = new ImageIcon("sources/Images/BLEU.png");
    ImageIcon JAUNE = new ImageIcon("sources/Images/JAUNE.png");
    ImageIcon NOIR = new ImageIcon("sources/Images/NOIR.png");
    ImageIcon ROUGE = new ImageIcon("sources/Images/ROUGE.png");
    ImageIcon VERT = new ImageIcon("sources/Images/VERT.png");

    ImageIcon VIDE = new ImageIcon("sources/Images/VIDE.png");


    public RandomAI(Pyramide p2Pyramide, Table2D table2D, Table2D baseK3) {
        RandomPyramid(p2Pyramide, table2D,baseK3);
        //GeneratePyramid(p2Pyramide,tabe2D, baseK3);
    }

    public void RandomPyramid(Pyramide pyramide, Table2D table2D, Table2D basek3){
        int colors[] = calculateColors(table2D);
        int index = 0;
        boolean bool;

        Pion pionSource = null;
        for(int i = 0; i<pyramide.getHight(); i++){
            for (int j=0 ; j<=i;j++){
                if(i==0&&j==0){//pour eviter les penalites au debut
                    //case 0,0 de la pyramide
                    int k=2;
                    bool=false;
                    while(k < basek3.getWidth()-2&&bool==false){
                        Pion ex=basek3.getPion(0,k);
                        for (int m = 0; m < table2D.getHeight(); m++) {
                            for (int l = 0; l < table2D.getWidth()-m; l++) {
                                Pion ex2=table2D.getCases(m,l);
                                if(ex2.getCouleur()==ex.getCouleur()){
                                    pionSource = ex;
                                    pyramide.getPion(i,j).replacePion(pionSource);
                                    switch (ex.getCouleur()){
                                        case ROUGE -> colors[0]--;
                                        case BLEU -> colors[1]--;
                                        case VERT -> colors[2]--;
                                        case JAUNE -> colors[3]--;
                                        case NOIR -> colors[4]--;
                                    }
                                    bool=true;
                                    break;
                                }
                            }
                        }
                        k++;
                    }
                } else if ((i==2&&j==1)||(i==4&&j==3)) {//2 pions beiges
                    pionSource = new Pion(CouleurPion.BEIGE, Pion.TypePion.COLORED, BEIGE, i, j,2);
                    pyramide.getPion(i,j).replacePion(pionSource);
                } else if (i==4&&j==1) {//pion blanc
                    pionSource = new Pion(CouleurPion.BLANC, Pion.TypePion.COLORED, BLANC, i, j,2);
                    pyramide.getPion(i,j).replacePion(pionSource);
                }
                else {

                    //liste pour les indices du couleur qui ne sont pas null
                    ArrayList<Integer> listdescouleurnonnul = new ArrayList<>();
                    for (int k = 0; k < colors.length; k++) {
                        if (colors[k] != 0)
                            listdescouleurnonnul.add(k);
                    }
                    index = (int) (Math.random() * listdescouleurnonnul.size());
                    colors[listdescouleurnonnul.get(index)]--;

                    switch (listdescouleurnonnul.get(index)) {
                        case 0 -> pionSource = new Pion(CouleurPion.ROUGE, Pion.TypePion.COLORED, ROUGE, i, j, 2);
                        case 1 -> pionSource = new Pion(CouleurPion.BLEU, Pion.TypePion.COLORED, BLEU, i, j, 2);
                        case 2 -> pionSource = new Pion(CouleurPion.VERT, Pion.TypePion.COLORED, VERT, i, j, 2);
                        case 3 -> pionSource = new Pion(CouleurPion.JAUNE, Pion.TypePion.COLORED, JAUNE, i, j, 2);
                        case 4 -> pionSource = new Pion(CouleurPion.NOIR, Pion.TypePion.COLORED, NOIR, i, j, 2);
                    }

                    assert pionSource != null; //if pionSource is null, the program will stop
                        pyramide.getPion(i, j).replacePion(pionSource);
                }
            }
        }
    }

    public void GeneratePyramid(Pyramide pyramide,Table2D table2D,Table2D basek3){
        boolean bool;
        for(int i = 0; i<pyramide.getHight(); i++){
            for (int j=0 ; j<=i;j++) {
                bool=false;
                int k = 2;
                if(i==0&&j==0){//pour eviter les penalites au debut
                    while(k < basek3.getWidth()-2&&bool==false){
                        Pion ex=basek3.getPion(0,k);
                        for (int m = 0; m < table2D.getHeight(); m++) {
                            for (int l = 0; l < table2D.getWidth()-m; l++) {
                                Pion ex2=table2D.getCases(m,l);
                                if(ex2.getImageIcon().toString()=="sources/Images/VIDE.png"){
                                    if(ex.getImageIcon()==ex2.getImageIcon()){
                                        pyramide.getPion(i,j).replacePion(ex2);
                                        ex2.setImageIcon(VIDE);
                                        bool=true;
                                    }
                                }
                            }
                        }
                        k++;
                    }
                }
                else if (i==1) {
                    Pion ex2;
                    for (int m = 0; m < table2D.getHeight(); m++) {
                        for (int l = 0; l < table2D.getWidth()-m; l++) {
                            ex2=table2D.getCases(m,l);
                            if(ex2.getImageIcon().toString()!=VIDE.toString()){
                                pyramide.getPion(i,j).replacePion(ex2);
                                ex2.setImageIcon(VIDE);
                            }
                        }
                    }
                }
                else if (i==2) {
                    Pion ex2;
                    for (int m = 0; m < table2D.getHeight(); m++) {
                        for (int l = 0; l < table2D.getWidth()-m; l++) {
                            ex2=table2D.getCases(m,l);
                            if((ex2.getImageIcon().toString()==BEIGE.toString()||ex2.getImageIcon().toString()==BLANC.toString())&&pyramide.getPion(i,j).getImageIcon().toString()==VIDE.toString()){
                                pyramide.getPion(i,j).replacePion(ex2);
                                System.out.println("i= "+i+" j= "+j+" couleur= "+ex2.getCouleur()+" image= "+ex2.getImageIcon().toString());
                                ex2.setImageIcon(VIDE);
                            }
                            else if(ex2.getCouleur()!=null){
                                pyramide.getPion(i,j).setCouleur(CouleurPion.NOIR);
                                ex2.setCouleur(null);
                            }
                        }
                }} else{
                    Pion ex2;
                    for (int m = 0; m < table2D.getHeight(); m++) {
                        for (int l = 0; l < table2D.getWidth()-m; l++) {
                            ex2=table2D.getCases(m,l);
                            if(ex2.getCouleur()!=null){
                                pyramide.getPion(i,j).replacePion(ex2);
                                ex2.setCouleur(null);
                            }
                        }
                    }
                }
            }
            }
    }
    public int[] calculateColors(Table2D table2D){
        //calculate the number of each color in the table
        int[] colors = new int[5];
        for (int i = 0; i < table2D.getHeight(); i++) {
            for (int j = 0; j < table2D.getWidth(); j++) {
                if (table2D.getCases(i, j) != null) {
                    switch (table2D.getCases(i, j).getCouleur()) {
                        case ROUGE -> colors[0]++;
                        case BLEU -> colors[1]++;
                        case VERT -> colors[2]++;
                        case JAUNE -> colors[3]++;
                        case NOIR -> colors[4]++;
                    }
                }
            }
        }
        return colors;
    }

}
