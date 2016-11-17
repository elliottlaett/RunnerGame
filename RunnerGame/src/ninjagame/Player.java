package ninjagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Player {

	private ImageView graphics = new ImageView();
	private Rectangle hitbox;
	private boolean isDead;
	private boolean jumping;
	private Image[] images;
	private int imageCounter = 0;
	private int hitPoints = 3;
	private boolean slide;

	Player(Image[] images) {
		this.images = images;
		this.graphics.setImage(images[imageCounter]);
		this.hitbox = new Rectangle(images[imageCounter].getWidth(), images[imageCounter].getHeight());
		this.jumping = false;
		this.isDead = false;

	}

	public ImageView getGraphics() {
		return graphics;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isJumping() {
		// TODO Auto-generated method stub
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public boolean isSlide() {
		return slide;
	}

	public void setSlide(boolean slide) {
		this.slide = slide;
	}

	public void refreshImg() {
		if (!jumping) {
			graphics.setImage(images[imageCounter++]);
			if (imageCounter == 9)
				imageCounter = 0;

		} else
			graphics.setImage(images[10]);

		if (slide) {

			graphics.setImage(images[11]);

		}
		if (isDead) {
			graphics.setImage(images[12]);
		}

	}

}
