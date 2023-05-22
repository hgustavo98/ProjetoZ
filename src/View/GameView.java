package src.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.Controller.Personagem;
import src.Controller.Worlds;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView {
    public static Personagem personagem = new Personagem(2, 2);
    public static Worlds worlds = new Worlds();
    public static JPanel panel = new JPanel();
    public static JFrame frame = new JFrame("Swing Example");
    public static JLabel[][] labels;
    public static int playerX, playerY;

    public static void main(String[] args) {

        displayMundo(worlds.getCurrentWorld());
    }

    public static void displayMundo (char[][] world){

        // Criando instacia JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //JPanel
        panel.setBackground(Color.BLACK); // cor de fundo do Jpanel
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        // move o personagem
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char [][] currentWorld = worlds.getCurrentWorld(); // retorna o mundo atual

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    personagem.movePlayer("w", worlds);
                    atualizarMundo(currentWorld);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A){
                    personagem.movePlayer("a", worlds);
                    atualizarMundo(currentWorld);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S){
                    personagem.movePlayer("s", worlds);
                    atualizarMundo(currentWorld);
                }
                else if (e.getKeyCode() == KeyEvent.VK_D){
                    personagem.movePlayer("d", worlds);
                    atualizarMundo(currentWorld);
                }
            }
        });

        gerarMundo(world);// cria o mundo pela primeira vez

        //Adicionando Panel ao Frame
        frame.setContentPane(panel);

        //Tamanho da janela
        frame.setPreferredSize(new Dimension(190, 300));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void gerarMundo(char[][] world ){
        String caracter;
        labels = new JLabel[world.length][world[0].length];
        

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                
                caracter = i == personagem.getPlayerY() && j == personagem.getPlayerX() ? "P" : String.valueOf(world[i][j]); //Posiciona o personagem

                labels[i][j] = new JLabel(caracter);//setando valor da label(cada caracter)

                if (caracter.equals(".")) {
                    labels[i][j].setBorder(new EmptyBorder(0, 6, 0, 10)); // o '.' ocupa menos espaco que '#', por isso e necessario aumentar a sua largura
                } else {
                    labels[i][j].setBorder(new EmptyBorder(0, 1, 0, 10));//coordenadas do caracter
                }

                labels[i][j].setForeground(Color.WHITE); //cor da letra
                panel.add(labels[i][j]);//adicionando label ao panel
            }
        }
    }

    public static void atualizarMundo(char [][] world) {
        panel.removeAll();
        // Recriar o mundo
        gerarMundo(world);

        // Atualizar o JPanel
        panel.revalidate();
        panel.repaint();
    }
}
