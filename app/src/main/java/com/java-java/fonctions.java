import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        int timer = 60;
        Timer timerId = new Timer();
        Rectangle rectangle1 = new Rectangle();
        Rectangle rectangle2 = new Rectangle();
        Player player = new Player();
        Enemy enemy = new Enemy();

        decreaseTimer(timer, timerId, player, enemy);
    }

    public static boolean rectangularCollision(Rectangle rectangle1, Rectangle rectangle2) {
        return (rectangle1.getAttackBox().getPosition().getX() + rectangle1.getAttackBox().getWidth() >= rectangle2.getPosition().getX()
                && rectangle1.getAttackBox().getPosition().getX() <= rectangle2.getPosition().getX() + rectangle2.getWidth()
                && rectangle1.getAttackBox().getPosition().getY() + rectangle1.getAttackBox().getHeight() >= rectangle2.getPosition().getY()
                && rectangle1.getAttackBox().getPosition().getY() <= rectangle2.getPosition().getY() + rectangle2.getHeight()
                && rectangle1.isAttacking());
    }

    public static void determineWinner(Player player, Enemy enemy, Timer timerId) {
        timerId.cancel();
        timerId.purge();
        // Assuming the displayText element is a JLabel
        JLabel displayText = new JLabel();
        displayText.setVisible(true);
        displayText.setText("<html>");
        if (player.getHealth() == enemy.getHealth()) {
            displayText.setText(displayText.getText() + "EGALITE...");
        } else if (player.getHealth() > enemy.getHealth()) {
            displayText.setText(displayText.getText() + "SHAWN WIN !");
        } else if (player.getHealth() < enemy.getHealth()) {
            displayText.setText(displayText.getText() + "IRENE WIN !");
        }
        displayText.setText(displayText.getText() + "</html>");
        isGameOver(player, enemy);
    }

    public static void isGameOver(Player player, Enemy enemy) {
        if (player.getHealth() <= 0 || enemy.getHealth() <= 0 || timer == 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1500);
        }
    }

    public static void decreaseTimer(int timer, Timer timerId, Player player, Enemy enemy) {
        if (timer > 0) {
            timerId.schedule(new TimerTask() {
                @Override
                public void run() {
                    decreaseTimer(timer - 1, timerId, player, enemy);
                }
            }, 1000);
            timer--;
            // Assuming the timer element is a JLabel
            JLabel timerLabel = new JLabel();
            timerLabel.setText(Integer.toString(timer));
        }
        if (timer == 0) {
            determineWinner(player, enemy, timerId);
        }
    }
}

class Rectangle {
    private AttackBox attackBox;
    private Position position;
    private int width;
    private int height;

    public AttackBox getAttackBox() {
        return attackBox;
    }

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAttacking() {
        // Implement this method according to your needs
        return false;
    }
}

class AttackBox {
    private Position position;
    private int width;
    private int height;

    public Position getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

class Position {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Player {
    private int health;

    public int getHealth() {
        return health;
    }
}

class Enemy {
    private int health;

    public int getHealth() {
        return health;
    }
}