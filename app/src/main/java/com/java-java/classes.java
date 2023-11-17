import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

class Sprite {
  private Position position;
  private int width;
  private int height;
  private Image image;
  private double scale;
  private int framesMax;
  private int framesCurrent;
  private int framesElapsed;
  private int framesHold;
  private Offset offset;

  public Sprite(Position position, String imageSrc, double scale, int framesMax, Offset offset) {
    this.position = position;
    this.width = 50;
    this.height = 150;
    this.image = new Image();
    this.image.src = imageSrc;
    this.scale = scale;
    this.framesMax = framesMax;
    this.framesCurrent = 0;
    this.framesElapsed = 0;
    this.framesHold = 5;
    this.offset = offset;
  }

  public void draw(Graphics c) {
    c.drawImage(
      this.image,
      this.framesCurrent * (this.image.getWidth(null) / this.framesMax),
      0,
      this.image.getWidth(null) / this.framesMax,
      this.image.getHeight(null),
      this.position.getX() - this.offset.getX(),
      this.position.getY() - this.offset.getY(),
      (this.image.getWidth(null) / this.framesMax) * this.scale,
      this.image.getHeight(null) * this.scale
    );
  }

  public void animateFrames() {
    this.framesElapsed++;
    if (this.framesElapsed % this.framesHold == 0) {
      if (this.framesCurrent < this.framesMax - 1) {
        this.framesCurrent++;
      } else {
        this.framesCurrent = 0;
      }
    }
  }

  public void update(Graphics c) {
    this.draw(c);
    this.animateFrames();
  }
}

class Fighter extends Sprite {
  private Velocity velocity;
  private String color;
  private HashMap<String, Sprite> sprites;
  private AttackBox attackBox;
  private boolean isAttacking;
  private int health;
  private boolean dead;
  private String lastKey;

  public Fighter(Position position, Velocity velocity, String color, String imageSrc, double scale, int framesMax, Offset offset, HashMap<String, Sprite> sprites, AttackBox attackBox) {
    super(position, imageSrc, scale, framesMax, offset);
    this.velocity = velocity;
    this.width = 50;
    this.height = 150;
    this.lastKey = null;
    this.attackBox = new AttackBox(position, attackBox.offset, attackBox.width, attackBox.height);
    this.color = color;
    this.isAttacking = false;
    this.health = 100;
    this.framesCurrent = 0;
    this.framesElapsed = 0;
    this.framesHold = 5;
    this.sprites = sprites;
    this.dead = false;
    for (Sprite sprite : this.sprites.values()) {
      sprite.setImage(new Image());
      sprite.getImage().src = sprite.getImageSrc();
    }
  }

  public void update(Graphics c) {
    this.draw(c);
    if (!this.dead) {
      this.animateFrames();
    }
    // attack boxes
    this.attackBox.getPosition().setX(this.position.getX() + this.attackBox.getOffset().getX());
    this.attackBox.getPosition().setY(this.position.getY() + this.attackBox.getOffset().getY());
    // draw the attack box
    // c.fillRect(
    //   this.attackBox.getPosition().getX(),
    //   this.attackBox.getPosition().getY(),
    //   this.attackBox.getWidth(),
    //   this.attackBox.getHeight()
    // )
    this.position.setX(this.position.getX() + this.velocity.getX());
    this.position.setY(this.position.getY() + this.velocity.getY());
    // gravity function
    if (this.position.getY() + this.height + this.velocity.getY() >= canvas.getHeight() - 150) {
      this.velocity.setY(0);
      this.position.setY(canvas.getHeight() - this.height - 150);
    } else {
      this.velocity.setY(this.velocity.getY() + gravity);
    }
  }

  public void attack() {
    this.switchSprite("attack");
    this.isAttacking = true;
  }

  public void takeHit() {
    this.health -= 5;
    if (this.health <= 0) {
      this.switchSprite("death");
    } else {
      this.switchSprite("takeHit");
    }
  }

  public void switchSprite(String sprite) {
    if (this.getImage() == this.sprites.get("death").getImage()) {
      if (this.framesCurrent == this.sprites.get("death").getFramesMax() - 1) {
        this.dead = true;
        return;
      }
    }
    // overriding all other animations with the attack animation
    if (
      this.getImage() == this.sprites.get("attack").getImage() &&
      this.framesCurrent < this.sprites.get("attack").getFramesMax() - 1
    ) {
      return;
    }
    // override when fighter gets hit
    if (
      this.getImage() == this.sprites.get("takeHit").getImage() &&
      this.framesCurrent < this.sprites.get("takeHit").getFramesMax() - 1
    ) {
      return;
    }
    switch (sprite) {
      case "idle":
        if (this.getImage() != this.sprites.get("idle").getImage()) {
          this.setImage(this.sprites.get("idle").getImage());
          this.setFramesMax(this.sprites.get("idle").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "run":
        if (this.getImage() != this.sprites.get("run").getImage()) {
          this.setImage(this.sprites.get("run").getImage());
          this.setFramesMax(this.sprites.get("run").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "jump":
        if (this.getImage() != this.sprites.get("jump").getImage()) {
          this.setImage(this.sprites.get("jump").getImage());
          this.setFramesMax(this.sprites.get("jump").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "fall":
        if (this.getImage() != this.sprites.get("fall").getImage()) {
          this.setImage(this.sprites.get("fall").getImage());
          this.setFramesMax(this.sprites.get("fall").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "attack":
        if (this.getImage() != this.sprites.get("attack").getImage()) {
          this.setImage(this.sprites.get("attack").getImage());
          this.setFramesMax(this.sprites.get("attack").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "takeHit":
        if (this.getImage() != this.sprites.get("takeHit").getImage()) {
          this.setImage(this.sprites.get("takeHit").getImage());
          this.setFramesMax(this.sprites.get("takeHit").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
      case "death":
        if (this.getImage() != this.sprites.get("death").getImage()) {
          this.setImage(this.sprites.get("death").getImage());
          this.setFramesMax(this.sprites.get("death").getFramesMax());
          this.framesCurrent = 0;
        }
        break;
    }
  }
}