/**
 * 
 * @author Patrik O 
 * 
 * JAVA15
 */

package ninjagame;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

/**
 * Background class generates a scrolling background
 */
public class Background
{
	private AnimationTimer gameLoop;	
	private float backgroundScrollSpeed;
	ImageView backgroundImageView, backgroundImageView2;
	
	Background()
	{
		backgroundScrollSpeed = 1.5f;
	}

	 public ImageView getBackgroundImageView() {
		return backgroundImageView;
	}

	public void setBackgroundImageView(ImageView backgroundImageView) {
		this.backgroundImageView = backgroundImageView;
	}

	public ImageView getBackgroundImageView2() {
		return backgroundImageView2;
	}

	public void setBackgroundImageView2(ImageView backgroundImageView2) {
		this.backgroundImageView2 = backgroundImageView2;
	}
	
	public float getBackgroundScrollSpeed() {
		return backgroundScrollSpeed;
	}
	
	public void setBackgroundScrollSpeed(float backgroundScrollSpeed) {
		this.backgroundScrollSpeed = backgroundScrollSpeed;
	}
	
	public void loadBackGround()
	{
		  backgroundImageView = new ImageView( getClass().getResource("/textures/backg2.png").toExternalForm());
		  backgroundImageView2 = new ImageView( getClass().getResource("/textures/backg2.png").toExternalForm());

		  backgroundImageView.relocate(0, 0);
		  backgroundImageView2.relocate(backgroundImageView2.getImage().getWidth(), 0);
	}
	
	public void startBackGroundLoop()
	{
		gameLoop = new AnimationTimer() {
	         
            @Override
            public void handle(long l) 
            {
            
             float x = (float) (backgroundImageView.getLayoutX() - backgroundScrollSpeed);
             float x2 = (float) (backgroundImageView2.getLayoutX() - backgroundScrollSpeed);
             
             if(backgroundImageView.getLayoutX() < -993)
             {
            	 x += backgroundImageView.getImage().getWidth() * 2;
             }
             
             if(backgroundImageView2.getLayoutX() < -993)
             {
                 x2 += backgroundImageView2.getImage().getWidth() * 2;
             }

             backgroundImageView.setLayoutX(x);  
             backgroundImageView2.setLayoutX(x2);
            }
 
        };
        
        gameLoop.start();
	}
}
