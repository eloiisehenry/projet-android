import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {
    // controle de la musique
    private static final String bgMusic = "bg-music";
    private static final double volume = 0.5; // Définit le volume de la musique à 50%
    private static final String canvas = "canvas";
    private static final int canvasWidth = 1300;
    private static final int canvasHeight = 800;
    private static final double gravity = 1.5;
    private static final String background = "./img/bg2.png";
    private static final String shop = "./img/shop.png";
    private static final int shopX = 650;
    private static final int shopY = 420;
    private static final double shopScale = 1.6;
    private static final int shopFramesMax = 6;
    private static final String player = "./img/Shawn/Idle.png";
    private static final int playerX = 0;
    private static final int playerY = -50;
    private static final int playerVelocityX = 0;
    private static final int playerVelocityY = 0;
    private static final int playerFramesMax = 7;
    private static final double playerScale = 3;
    private static final int playerOffsetX = 215;
    private static final int playerOffsetY = 157;
    private static final String playerIdle = "./img/Shawn/Idle.png";
    private static final int playerIdleFramesMax = 7;
    private static final String playerRun = "./img/Shawn/Run.png";
    private static final int playerRunFramesMax = 8;
    private static final String playerJump = "./img/Shawn/Jump.png";
    private static final int playerJumpFramesMax = 2;
    private static final String playerFall = "./img/Shawn/Fall.png";
    private static final int playerFallFramesMax = 2;
    private static final String playerAttack = "./img/Shawn/Attack.png";
    private static final int playerAttackFramesMax = 4;
    private static final String playerTakeHit = "./img/Shawn/TakeHit.png";
    private static final int playerTakeHitFramesMax = 3;
    private static final String playerDeath = "./img/Shawn/Death.png";
    private static final int playerDeathFramesMax = 5;
    private static final int playerAttackBoxOffsetX = 150;
    private static final int playerAttackBoxOffsetY = 50;
    private static final int playerAttackBoxWidth = 80;
    private static final int playerAttackBoxHeight = 40;
    private static final String enemy = "./img/Irene/Idle.png";
    private static final int enemyX = 1130;
    private static final int enemyY = -50;
    private static final int enemyVelocityX = 0;
    private static final int enemyVelocityY = 0;
    private static final String enemyColor = "blue";
    private static final int enemyFramesMax = 9;
    private static final double enemyScale = 3;
    private static final int enemyOffsetX = 215;
    private static final int enemyOffsetY = 167;
    private static final String enemyIdle = "./img/Irene/Idle.png";
    private static final int enemyIdleFramesMax = 9;
    private static final String enemyRun = "./img/Irene/Run.png";
    private static final int enemyRunFramesMax = 8;
    private static final String enemyJump = "./img/Irene/Jump.png";
    private static final int enemyJumpFramesMax = 2;
    private static final String enemyFall = "./img/Irene/Fall.png";
    private static final int enemyFallFramesMax = 2;
    private static final String enemyAttack = "./img/Irene/Attack.png";
    private static final int enemyAttackFramesMax = 4;
    private static final String enemyTakeHit = "./img/Irene/TakeHit.png";
    private static final int enemyTakeHitFramesMax = 3;
    private static final String enemyDeath = "./img/Irene/Death.png";
    private static final int enemyDeathFramesMax = 5;
    private static final int enemyAttackBoxOffsetX = -145;
    private static final int enemyAttackBoxOffsetY = 50;
    private static final int enemyAttackBoxWidth = 90;
    private static final int enemyAttackBoxHeight = 50;
    private static final String keys = "keys";
    private static final boolean qPressed = false;
    private static final boolean dPressed = false;
    private static final boolean arrowRightPressed = false;
    private static final boolean arrowLeftPressed = false;

    public Main() {
        decreaseTimer();
        animate();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!player.dead) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_Q:
                            keys.q.pressed = true;
                            player.lastKey = 'q';
                            break;
                        case KeyEvent.VK_D:
                            keys.d.pressed = true;
                            player.lastKey = 'd';
                            break;
                        case KeyEvent.VK_Z:
                            player.velocity.y = -20;
                            break;
                        case KeyEvent.VK_S:
                            player.attack();
                            break;
                    }
                }
                if (!enemy.dead) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            keys.ArrowRight.pressed = true;
                            enemy.lastKey = 'ArrowRight';
                            break;
                        case KeyEvent.VK_LEFT:
                            keys.ArrowLeft.pressed = true;
                            enemy.lastKey = 'ArrowLeft';
                            break;
                        case KeyEvent.VK_UP:
                            enemy.velocity.y = -20;
                            break;
                        case KeyEvent.VK_DOWN:
                            enemy.attack();
                            break;
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Q:
                        keys.q.pressed = false;
                        break;
                    case KeyEvent.VK_D:
                        keys.d.pressed = false;
                        break;
                }
                // enemy keys
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        keys.ArrowRight.pressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        keys.ArrowLeft.pressed = false;
                        break;
                }
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        // Draw code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        Main game = new Main();
        frame.add(game);
        frame.setSize(canvasWidth, canvasHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        game.requestFocus();
    }

    private static void decreaseTimer() {
        // Code here
    }

    private static void animate() {
        // Code here
    }
}