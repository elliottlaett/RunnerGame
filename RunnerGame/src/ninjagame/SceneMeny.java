package ninjagame;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SceneMeny extends Application {

	private Scene meny;
	private Scene game;
	private Scene gameOver;
	private Scene howToPlay;
	private Scene highscore;
	private Stage mainStage;
	private Group gameRoot;

	private ImageView graphics = new ImageView();
	private Image hearts[] = { new Image("textures/heart.gif"), new Image("textures/heart2.gif"),
			new Image("textures/heart3.gif") };

	private Image[] images = { new Image("textures/player_textures/Run__000.png"),
			new Image("textures/player_textures/Run__001.png"), new Image("textures/player_textures/Run__002.png"),
			new Image("textures/player_textures/Run__003.png"), new Image("textures/player_textures/Run__004.png"),
			new Image("textures/player_textures/Run__005.png"), new Image("textures/player_textures/Run__006.png"),
			new Image("textures/player_textures/Run__007.png"), new Image("textures/player_textures/Run__008.png"),
			new Image("textures/player_textures/Run__009.png"), new Image("textures/player_textures/Jump__002.png"),
			new Image("textures/player_textures/Slide__001.png"), new Image("textures/player_textures/Dead__000.png") };

	private Media[] soundEffects = { new Media(getClass().getResource("/sounds/Click.mp3").toString()),
			new Media(getClass().getResource("/sounds/button.mp3").toString()),
			new Media(getClass().getResource("/sounds/jump.wav").toString()),
			new Media(getClass().getResource("/sounds/Punch.mp3").toString()),
			new Media(getClass().getResource("/sounds/game_over.mp3").toString()) };

	private Player player;
	private float counter = 1;
	private Timeline playerLoop;
	private ArrayList<Obstacle> obsList = new ArrayList<>();
	private Obstacle obs;
	private Timeline timeline;
	private Duration time = Duration.ZERO;
	private Text timerLabel = new Text();
	private IntegerProperty timeSeconds = new SimpleIntegerProperty();
	final int SCENE_WIDTH = 990;
	final int SCENE_HEIGHT = 500;
	int i = 0;

	boolean hit = false;
	int hitTimer = 0;

	private List<Highscore> highscoreList;

	public void start(Stage theStage) {

		highscoreList = Highscore.readFile();
		mainStage = theStage;
		meny = createMenyScene();
		mainStage.getIcons().add(new Image("textures/titel.icon.png"));
		mainStage.setTitle("Jump and stuff");
		mainStage.setScene(meny);
		mainStage.show();

	}

	private Scene createHowToPlayScene() {

		Background b = new Background();
		Group root = new Group();
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(0, 10, 0, 10));
		b.loadBackGround();
		b.startBackGroundLoop();

		Text rules = new Text("How to play");

		Label text = new Label("     You will encounter \n"

		+ "     obstacles that you \n " + "     have to evade.\n\n"

		+ "     CONTROLS \n\n" + "     Jump: UP-Arrow\n" + "     Slide: DOWN-Arrow\n\n" + "     Try to survive as\n"
				+ "     long as you can!");
		text.setMaxWidth(500);
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.CENTER);

		Text goBack = new Text("Return to menu");

		Font font = new Font("Arial Black", 40);
		Font font2 = new Font("Arial Black", 20);
		Font font3 = new Font("Arial Black", 35);

		hoverOver(goBack);
		menyChoice(goBack);

		text.setFont(font2);
		text.setTextFill(Color.WHITE);

		rules.setFont(font);
		rules.setFill(Color.GREEN);
		rules.setStroke(Color.BLACK);
		rules.setEffect(new Glow(10));

		goBack.setFont(font3);
		goBack.setFill(Color.WHITE);
		goBack.setStroke(Color.BLACK);
		pane.setAlignment(Pos.CENTER);
		root.getChildren().addAll(b.backgroundImageView, b.backgroundImageView2, pane);
		pane.add(rules, 33, 0);
		pane.add(text, 33, 1);
		pane.add(goBack, 33, 2);

		return scene;
	}

	private Scene createMenyScene() {

		Background b = new Background();
		Group root = new Group();
		Scene theScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(0, 10, 0, 10));

		b.loadBackGround();
		b.startBackGroundLoop();

		Font font = new Font("Arial Black", 40);

		Text title = new Text("Jump and stuff");

		title.setFont(Font.font("Arial Black", 60));
		title.setFill(Color.GREEN);
		title.setStroke(Color.BLACK);
		title.setEffect(new Glow(10));

		Text newGame = new Text("New game");
		Text rules = new Text("How to play");
		Text scores = new Text("High score");
		Text exit = new Text("Exit Game");
		newGame.setFont(font);
		newGame.setFill(Color.WHITE);
		newGame.setStroke(Color.BLACK);
		rules.setFont(font);
		rules.setFill(Color.WHITE);
		rules.setStroke(Color.BLACK);
		scores.setFont(font);
		scores.setFill(Color.WHITE);
		scores.setStroke(Color.BLACK);
		exit.setFont(font);
		exit.setFill(Color.WHITE);
		exit.setStroke(Color.BLACK);

		hoverOver(newGame);
		hoverOver(rules);
		hoverOver(scores);
		hoverOver(exit);

		menyChoice(newGame);
		menyChoice(rules);
		menyChoice(scores);
		menyChoice(exit);

		VBox meny = new VBox(20);

		meny.setAlignment(Pos.CENTER);
		meny.getChildren().addAll(newGame, rules, scores, exit);
		pane.add(title, 25, 5);
		pane.add(meny, 25, 6);

		root.getChildren().addAll(b.backgroundImageView, b.backgroundImageView2, pane);

		return theScene;

	}

	private Scene createGameScene() {

		Background b = new Background();
		Pane backgroundLayer;

		timerLabel.textProperty().bind(timeSeconds.asString());
		timerLabel.setFont(Font.font("Arial Black", 40));
		timerLabel.setFill(Color.RED);
		timerLabel.setStroke(Color.BLACK);
		timerLabel.setEffect(new Glow(5));

		HBox hbox = new HBox(5);
		Text score = new Text("Score: ");
		score.setFont(Font.font("Arial Black", 40));
		score.setFill(Color.RED);
		score.setStroke(Color.BLACK);
		score.setEffect(new Glow(10));

		Text lifeLabel = new Text("Lives : ");
		lifeLabel.setFont(Font.font("Arial Black", 40));
		lifeLabel.setFill(Color.RED);
		lifeLabel.setStroke(Color.BLACK);
		lifeLabel.setEffect(new Glow(10));

		HBox hbox2 = new HBox(5);

		graphics.setImage(hearts[2]);

		VBox vbox = new VBox();
		hbox2.getChildren().addAll(lifeLabel, graphics);

		hbox.getChildren().addAll(score, timerLabel);

		vbox.getChildren().addAll(hbox, hbox2);

		gameRoot = new Group();
		Scene scene;
		try {
			gameRoot = new Group();
			backgroundLayer = new Pane();

			gameRoot.getChildren().add(backgroundLayer);

			scene = new Scene(gameRoot, SCENE_WIDTH, SCENE_HEIGHT);
			scene.setCursor(Cursor.NONE);

			b.loadBackGround();
			b.startBackGroundLoop();

			backgroundLayer.getChildren().add(b.backgroundImageView);
			backgroundLayer.getChildren().add(b.backgroundImageView2);

			player = new Player(images);

			gameRoot.getChildren().add(player.getGraphics());

			player.getGraphics().setTranslateX(100);
			player.getGraphics().setTranslateY(370);

			startPlayerMovement();
			playerLoop.play();
			TranslateTransition jump = new TranslateTransition(Duration.millis(450), player.getGraphics());
			TranslateTransition fall = new TranslateTransition(Duration.millis(450), player.getGraphics());
			jump.setInterpolator(Interpolator.LINEAR);

			scene.setOnKeyPressed(event -> {

				if (!player.isJumping()) {

					switch (event.getCode()) {
					case UP:

						player.setJumping(true);
						// fall.stop();
						// jump.stop();
						playMedia(soundEffects[2]);
						jump.setByY(-250);
						jump.setCycleCount(1);
						jump.play();
						jump.setOnFinished(finishedEvent -> {
							jump.stop();
							fall.setByY(250);
							fall.play();
							fall.setOnFinished(finishedFalling -> {
								player.setJumping(false);

							});
						});
					case DOWN:
						if (!player.isJumping()) {
							player.setSlide(true);
							player.getGraphics().setTranslateY(400);
							scene.setOnKeyReleased(slideEvent -> {
								player.setSlide(false);
								player.getGraphics().setTranslateY(370);
							});
						}

					default:
						break;
					}
				}
			});

			obs = new Obstacle();
			obs.getGraphics().setTranslateX(1000);
			obs.getGraphics().setTranslateY(370);
			gameRoot.getChildren().add(obs.getGraphics());
			gameRoot.getChildren().addAll(vbox);
			return scene;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Scene createGameOverScene() {

		int finalScore;
		Background b = new Background();
		Group root = new Group();
		Scene gameOverScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(0, 5, 0, 5));
		b.loadBackGround();
		b.startBackGroundLoop();

		Text gameOver = new Text("GAME OVER");
		gameOver.setFont(Font.font("Arial Black", 100));
		gameOver.setFill(Color.GREEN);
		gameOver.setEffect(new Glow(500));

		finalScore = Integer.parseInt(timerLabel.getText());
		HBox nameEntry = new HBox();
		VBox scoreItems = new VBox();
		nameEntry.setAlignment(Pos.CENTER);
		Text score = new Text("Your score is: " + finalScore);
		Text enterName = new Text("Enter your name: ");
		enterName.setFont(new Font("Arial Black", 20));
		enterName.setFill(Color.RED);
		enterName.setStroke(Color.BLACK);

		score.setFont(new Font("Arial Black", 40));
		score.setFill(Color.RED);
		score.setStroke(Color.BLACK);
		TextField playerName = new TextField();
		playerName.setPrefColumnCount(10);

		Button submitButton = new Button("Submit");
		submitButton.setAlignment(Pos.CENTER);

		submitButton.setOnAction(e -> {

			Highscore gamehighscore = new Highscore(playerName.getText(), finalScore);

			highscoreList.add(gamehighscore);
			highscoreList = Highscore.sortList(highscoreList);
			mainStage.setScene(meny);

		});

		Text meny = new Text("Back to main meny");
		meny.setFont(new Font("Arial Black", 40));
		meny.setFill(Color.WHITE);
		meny.setStroke(Color.BLACK);
		hoverOver(meny);
		menyChoice(meny);
		scoreItems.getChildren().addAll(score, nameEntry, meny);
		nameEntry.getChildren().addAll(enterName, playerName, submitButton);
		scoreItems.setAlignment(Pos.CENTER);
		pane.add(gameOver, 15, 1);
		pane.add(scoreItems, 15, 2);

		root.getChildren().add(b.backgroundImageView);
		root.getChildren().add(b.backgroundImageView2);
		root.getChildren().add(pane);

		return gameOverScene;

	}

	private Scene createHighscoreScene() {

		Background b = new Background();
		Group root = new Group();
		Scene highscoreScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(0, 10, 0, 10));

		b.loadBackGround();
		b.startBackGroundLoop();

		Text header = new Text("Highscore");

		highscoreList = Highscore.sortList(highscoreList);
		String highscoreString = "";
		for (int i = 0; i <= 9; i++) {
			highscoreString += i + 1 + "." + "  " + highscoreList.get(i).toString() + "\n";
		}

		TextArea textArea = new TextArea(highscoreString);

		Text goBack = new Text("Return to menu");

		Font font = new Font("Arial Black", 40);
		Font font2 = new Font("Arial Black", 17);
		Font font3 = new Font("Arial Black", 35);

		hoverOver(goBack);
		menyChoice(goBack);

		textArea.setFont(font2);
		textArea.setEditable(false);
		textArea.setPrefSize(180, 300);

		header.setFont(font);
		header.setFill(Color.GREEN);
		header.setStroke(Color.BLACK);
		header.setEffect(new Glow(10));

		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(header, textArea, goBack);

		goBack.setFont(font3);
		goBack.setFill(Color.WHITE);
		goBack.setStroke(Color.BLACK);

		pane.add(vbox, 33, 0);
		root.getChildren().addAll(b.backgroundImageView, b.backgroundImageView2, pane);

		return highscoreScene;
	}

	private void hoverOver(Text label) {

		label.setOnMouseEntered(e -> {
			playMedia(soundEffects[0]);
			label.setEffect(new Glow(50));
			label.setFill(Color.DARKRED);

		});
		label.setOnMouseExited(e -> {
			label.setEffect(null);
			label.setFill(Color.WHITE);
		});

	}

	private void menyChoice(Text label) {

		label.setOnMouseClicked(e -> {
			switch (label.getText()) {
			case "New game":
				playMedia(soundEffects[1]);
				game = createGameScene();
				mainStage.setScene(game);
				startScoreCounter();
				break;

			case "How to play":
				playMedia(soundEffects[1]);
				howToPlay = createHowToPlayScene();
				mainStage.setScene(howToPlay);
				break;

			case "High score":
				highscore = createHighscoreScene();
				mainStage.setScene(highscore);
				playMedia(soundEffects[1]);
				System.out.println("high score");
				break;

			case "Exit Game":
				playMedia(soundEffects[1]);
				Highscore.writeFile(highscoreList);
				System.exit(0);
				break;

			case "Back to main meny":
				playMedia(soundEffects[1]);
				mainStage.setScene(meny);
				break;

			case "Return to menu":
				playMedia(soundEffects[1]);
				mainStage.setScene(meny);
				break;

			default:
				break;
			}

		});

	}

	private void playMedia(Media m) {
		if (m != null) {
			MediaPlayer mp = new MediaPlayer(m);
			mp.play();
		}
	}

	private void startPlayerMovement() {
		obsList.add(new Obstacle());
		gameRoot.getChildren().add(obsList.get(0));
		playerLoop = new Timeline(new KeyFrame(Duration.millis(1000 / 15), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//if (obsList.isEmpty())
				
				player.setDead(false);
				updatePlayer();
				updatePlayer();
				updatePlayer();
			
				if (Integer.parseInt(timerLabel.getText()) > 10) {
					updatePlayer();
				}

				if (hit == false)
					checkCollision();
					
					
				if (obsList.get(0).getTranslateX() <= -200) {
					obsList.remove(0);
					obsList.removeAll(obsList);

					Obstacle obst = new Obstacle();

					obsList.add(obst);
					obst.setTranslateX(obsList.get(obsList.size() - 1).getTranslateX() - 1);
					gameRoot.getChildren().remove(obst);
					gameRoot.getChildren().add(obst);
				}
				for (int i = 0; i < obsList.size(); i++) {
					obsList.get(i).setTranslateX(obsList.get(i).getTranslateX() - 10);
					obsList.get(i).setTranslateX(obsList.get(i).getTranslateX() - 10);
					obsList.get(i).setTranslateX(obsList.get(i).getTranslateX() - 5);

					if (time.toSeconds() > 10) {
						obsList.get(i).setTranslateX(obsList.get(i).getTranslateX() - 5);
						obsList.get(i).setTranslateX(obsList.get(i).getTranslateX() - 5);

					}
				}
				hit = false;

			}
		}));
		playerLoop.setCycleCount(-1);
	}

	private void startScoreCounter() {
		timeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Duration duration = ((KeyFrame) t.getSource()).getTime();
				time = time.add(duration);

				timeSeconds.set((int) time.toSeconds());

			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();

	}

	private void updatePlayer() {
		if (counter % 4 == 0) {
			player.refreshImg();
			counter = 1;
		}
		counter++;
	}

	private void checkCollision() {

		player.getGraphics().boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> arg0, Bounds oldValue, Bounds newValue) {
				if (obsList.get(0).getBoundsInParent().intersects(newValue)) {
					hit = true;
					if (hit && hitTimer == 0) {
						gameRoot.getChildren().remove(obsList.get(0));
						playMedia(soundEffects[3]);
						hitTimer = 1;
						player.setHitPoints(player.getHitPoints() - 1);

						if (player.getHitPoints() == 2) {
							graphics.setImage(hearts[1]);
						} else if (player.getHitPoints() == 1) {
							graphics.setImage(hearts[0]);
						}
						player.setDead(true);
					}
				}
			}
		});

		hitTimer = 0;
		if (player.getHitPoints() == 0) {

			playMedia(soundEffects[4]);
			playerLoop.stop();
			timeline.stop();
			time = Duration.ZERO;
			gameRoot.getChildren().removeAll(gameRoot.getChildren());
			gameOver = createGameOverScene();
			mainStage.setScene(gameOver);

		}

		hit = false;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
