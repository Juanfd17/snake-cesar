//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 1500;
    private final int B_HEIGHT = 800;
    private final int DOT_SIZE = 25;
    private final int ALL_DOTS = 12000;
    private final int RAND_POS = 29;
    private int DELAY = 140;
    private final int[] x = new int[12000];
    private final int[] y = new int[12000];
    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = false;
    private Timer timer;
    private Image cupula;
    private Image ball;
    private Image ball1;
    private Image ball2;
    private Image ball3;
    private Image ball4;
    private Image apple;
    private Image head;
    private Image cesar;
    private Image respetosImg;
    int respetos = 0;
    int colorTextoR = 0;
    int colorTextoG = 0;
    int colorTextoB = 0;
    int level = 0;
    int cabezas;
    int cabezasComidas;
    int contadorManzanas;
    boolean space = false;
    boolean intro = false;
    boolean inicio = false;
    boolean dificultad = false;
    int vidas = 1;
    Integer[] nums = new Integer[0];
    int color = 1;

    public Board() {
        this.initBoard();
    }

    private void initBoard() {
        this.addKeyListener(new TAdapter());
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1500, 800));
        this.loadImages();
        this.initGame();
    }

    private void loadImages() {
        ImageIcon ii = new ImageIcon("cupula.JPEG");
        this.cupula = ii.getImage();
        ImageIcon ii1 = new ImageIcon("ajj.png");
        this.respetosImg = ii1.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        this.ball = iid.getImage();
        ImageIcon iid1 = new ImageIcon("ball1.png");
        this.ball1 = iid1.getImage();
        ImageIcon iid2 = new ImageIcon("ball2.png");
        this.ball2 = iid2.getImage();
        ImageIcon iid3 = new ImageIcon("ball3.png");
        this.ball3 = iid3.getImage();
        ImageIcon iid4 = new ImageIcon("ball4.png");
        this.ball4 = iid4.getImage();
        ImageIcon iia = new ImageIcon("apple.png");
        this.apple = iia.getImage();
        ImageIcon iih = new ImageIcon("head.png");
        this.head = iih.getImage();
        ImageIcon iih1 = new ImageIcon("cesarGrande.jpg");
        this.cesar = iih1.getImage();
    }

    private void initGame() {
        this.dots = 1;

        for(int z = 0; z < this.dots; ++z) {
            this.x[z] = 50 - z * 10;
            this.y[z] = 50;
        }

        this.locateApple();
        this.delay(this.DELAY);
    }

    private void delay(int delay) {
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }

    private static Integer[] append(Integer[] arr, int element) {
        List<Integer> list = new ArrayList(Arrays.asList(arr));
        list.add(element);
        return (Integer[])list.toArray(new Integer[0]);
    }

    private void initMessage(Graphics g) {
        String msg2;
        Font small2;
        FontMetrics metr2;
        if (!this.dificultad) {
            msg2 = "Pulsa ESPACIO para inicio rápido";
            small2 = new Font("Helvetica", 1, 60);
            metr2 = this.getFontMetrics(small2);
            g.setColor(Color.white);
            g.setFont(small2);
            g.drawString(msg2, (1500 - metr2.stringWidth(msg2)) / 2, 133);
            String msg3 = "Pulsa ENTER para elegir dificultad";
            Font small3 = new Font("Helvetica", 1, 60);
            FontMetrics metr3 = this.getFontMetrics(small3);
            g.setColor(Color.white);
            g.setFont(small3);
            g.drawString(msg3, (1500 - metr3.stringWidth(msg3)) / 2, 266);
        }

        if (this.space) {
            this.space = false;
            this.inicio = true;
            this.inGame = true;
        }

        if (this.intro || this.dificultad) {
            this.dificultad = true;
            msg2 = "Utiliza las flechas para modificar las vidas y espacio para confirmar    Vidas: " + this.vidas;
            small2 = new Font("Helvetica", 1, 20);
            metr2 = this.getFontMetrics(small2);
            g.setColor(Color.white);
            g.setFont(small2);
            g.drawString(msg2, (1500 - metr2.stringWidth(msg2)) / 2, 400);
            if (this.vidas != 1 && this.downDirection) {
                --this.vidas;
            }

            this.downDirection = false;
            if (this.upDirection) {
                this.upDirection = false;
                ++this.vidas;
            }

            if (this.space) {
                this.inicio = true;
                this.inGame = true;
            }
        }

    }

    private void doDrawing(Graphics g) {
        if (!this.inicio) {
            this.initMessage(g);
        }

        if (this.inGame) {
            this.colorTextoR = (int)(Math.random() * 255.0);
            this.colorTextoG = (int)(Math.random() * 255.0);
            this.colorTextoB = (int)(Math.random() * 255.0);
            Color myWhite = new Color(this.colorTextoR, this.colorTextoG, this.colorTextoB);
            String msg = "Nivel: " + this.level;
            if (this.dificultad) {
                msg = "Nivel: " + this.level + " Vidas: " + this.vidas;
            }

            Font small = new Font("Helvetica", 1, 20);
            FontMetrics metr = this.getFontMetrics(small);
            g.setColor(myWhite);
            g.setFont(small);
            g.drawString(msg, (1500 - metr.stringWidth(msg)) / 2, 113);
            g.drawImage(this.cupula, 549, 239, this);
            if (this.cabezas <= this.cabezasComidas) {
                this.color = (int)(Math.random() * 4.0);
                this.nums = append(this.nums, this.color);
                ++this.cabezas;
            }

            if (this.dots % 3 == 0 && this.respetos < 1500) {
                this.respetos += 20;
                g.drawImage(this.respetosImg, this.respetos, this.respetos, this);
            } else {
                this.respetos = 0;
            }

            switch (this.color) {
                case 0:
                    g.drawImage(this.ball1, this.apple_x, this.apple_y, this);
                    break;
                case 1:
                    g.drawImage(this.ball2, this.apple_x, this.apple_y, this);
                    break;
                case 2:
                    g.drawImage(this.ball3, this.apple_x, this.apple_y, this);
                    break;
                case 3:
                    g.drawImage(this.ball4, this.apple_x, this.apple_y, this);
            }

            for(int z = 0; z < this.dots; ++z) {
                if (z == 0) {
                    g.drawImage(this.head, this.x[z], this.y[z], this);
                } else {
                    switch (this.nums[z]) {
                        case 0:
                            g.drawImage(this.ball1, this.x[z], this.y[z], this);
                            break;
                        case 1:
                            g.drawImage(this.ball2, this.x[z], this.y[z], this);
                            break;
                        case 2:
                            g.drawImage(this.ball3, this.x[z], this.y[z], this);
                            break;
                        case 3:
                            g.drawImage(this.ball4, this.x[z], this.y[z], this);
                    }
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else if (!this.inGame && this.inicio) {
            this.gameOver(g);
        }

    }

    private void gameOver(Graphics g) {
        String msg = "Don't Feed the Troll";
        Font small = new Font("Helvetica", 1, 20);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (1500 - metr.stringWidth(msg)) / 2, 153);
        g.drawImage(this.cesar, 540, 180, this);
    }

    private void checkApple() {
        if (this.x[0] == this.apple_x && this.y[0] == this.apple_y) {
            this.locateApple();
            ++this.dots;
        }

    }

    private void move() {
        for(int z = this.dots; z > 0; --z) {
            this.x[z] = this.x[z - 1];
            this.y[z] = this.y[z - 1];
        }

        int[] var10000;
        if (this.leftDirection) {
            var10000 = this.x;
            var10000[0] -= 25;
        }

        if (this.rightDirection) {
            var10000 = this.x;
            var10000[0] += 25;
        }

        if (this.upDirection) {
            var10000 = this.y;
            var10000[0] -= 25;
        }

        if (this.downDirection) {
            var10000 = this.y;
            var10000[0] += 25;
        }

    }

    private void checkCollision() {
        for(int z = this.dots; z > 0; --z) {
            if (z > 4 && this.x[0] == this.x[z] && this.y[0] == this.y[z]) {
                this.inGame = false;
            }
        }

        if (this.y[0] >= 800) {
            if (this.dificultad && this.vidas == 1) {
                this.inGame = false;
            } else {
                this.y[0] = 0;
                --this.vidas;
            }
        }

        if (this.y[0] < 0) {
            if (this.dificultad && this.vidas == 1) {
                this.inGame = false;
            } else {
                this.y[0] = 800;
                --this.vidas;
            }
        }

        if (this.x[0] >= 1500) {
            if (this.dificultad && this.vidas == 1) {
                this.inGame = false;
            } else {
                this.x[0] = 0;
                --this.vidas;
            }
        }

        if (this.x[0] < 0) {
            if (this.dificultad && this.vidas == 1) {
                this.inGame = false;
            } else {
                this.x[0] = 1500;
                --this.vidas;
            }
        }

        if (!this.inGame) {
            this.timer.stop();
        }

    }

    private void locateApple() {
        int r = (int)(Math.random() * 29.0);
        this.apple_x = r * 25;
        r = (int)(Math.random() * 29.0);
        this.apple_y = r * 25;
        ++this.cabezasComidas;
        ++this.contadorManzanas;
        if (this.contadorManzanas > 3) {
            this.contadorManzanas = 0;
            ++this.level;
            this.DELAY = 500;
            this.delay(this.DELAY);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (this.inGame) {
            this.checkApple();
            this.checkCollision();
            this.move();
        }

        this.repaint();
    }

    private class TAdapter extends KeyAdapter {
        private TAdapter() {
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == 37 && !Board.this.rightDirection) {
                Board.this.leftDirection = true;
                Board.this.upDirection = false;
                Board.this.downDirection = false;
            }

            if (key == 39 && !Board.this.leftDirection) {
                Board.this.rightDirection = true;
                Board.this.upDirection = false;
                Board.this.downDirection = false;
            }

            if (key == 38 && !Board.this.downDirection) {
                Board.this.upDirection = true;
                Board.this.rightDirection = false;
                Board.this.leftDirection = false;
            }

            if (key == 40 && !Board.this.upDirection) {
                Board.this.downDirection = true;
                Board.this.rightDirection = false;
                Board.this.leftDirection = false;
            }

            if (key == 32) {
                Board.this.space = true;
            } else {
                Board.this.space = false;
            }

            if (key == 10) {
                Board.this.intro = true;
            } else {
                Board.this.intro = false;
            }

        }
    }
}
