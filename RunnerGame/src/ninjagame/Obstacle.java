package ninjagame;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Group{
	private ImageView graphics = new ImageView();
	private Rectangle hitbox;
	private Image image;
	private Random rand = new Random();
	private int randomizedObject = rand.nextInt(21) + 1;

	Obstacle() {
		this.randomObstacles();
		this.graphics.setImage(image);
		this.hitbox = new Rectangle(image.getWidth(), image.getHeight());
		this.getChildren().addAll(graphics);
		this.setTranslateX(1000);
	}

	private void randomObstacles() {
		switch (randomizedObject) {
		case 1:
			this.image = new Image("textures/block1.1.jpg");
			this.setTranslateY(335);
			break;
		case 2:
			this.image = new Image("textures/block2.2.jpg");
			this.setTranslateY(335);
			break;
		case 3:
			this.image = new Image("textures/block1.7.jpg");
			this.setTranslateY(155);
			break;
		case 4:
			this.image = new Image("textures/block1.2.png");
			this.setTranslateY(350);
			break;
		case 5:
			this.image = new Image("textures/block1.3.png");
			this.setTranslateY(350);
			break;
		case 6:
			this.image = new Image("textures/block1.4.png");
			this.setTranslateY(340);
			break;
		case 7:
			this.image = new Image("textures/block2.0.png");
			this.setTranslateY(155);
			break;
		case 8:
			this.image = new Image("textures/block1.9.png");
			this.setTranslateY(155);
			break;
		case 9:
			this.image = new Image("textures/block3.2.png");
			this.setTranslateY(350);
			break;
		case 10:
			this.image = new Image("textures/block1.8.png");
			this.setTranslateY(155);
			break;
		case 11:
			this.image = new Image("textures/block2.1.png");
			this.setTranslateY(155);
			break;
		case 12:
			this.image = new Image("textures/block3.1.png");
			this.setTranslateY(350);
			break;
		case 13:
			this.image = new Image("textures/block1.5.png");
			this.setTranslateY(350);
			break;
		case 14:
			this.image = new Image("textures/block2.4.png");
			this.setTranslateY(350);
			break;
		case 15:
			this.image = new Image("textures/block2.4.png");
			this.setTranslateY(270);
			break;
		case 16:
			this.image = new Image("textures/block2.5.png");
			this.setTranslateY(210);
			break;
		case 17:
			this.image = new Image("textures/block2.7.png");
			this.setTranslateY(270);
			break;
		case 18:
			this.image = new Image("textures/block2.8.png");
			this.setTranslateY(210);
			break;
		case 19:
			this.image = new Image("textures/block1.6.jpg");
			this.setTranslateY(210);
			break;
		case 20:
			this.image = new Image("textures/block3.0.png");
			this.setTranslateY(335);
			break;
		case 21:
			this.image = new Image("textures/block2.3.png");
			this.setTranslateY(210);
			break;
		default:
			this.image = new Image("textures/block3.0.png");
			this.setTranslateY(335);
			break;
		}
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
}
